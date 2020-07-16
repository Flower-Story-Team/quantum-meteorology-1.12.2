package com.konpi.flower.seasons.Handler;

import java.util.HashMap;

import com.konpi.flower.api.config.FlowerOption;
import com.konpi.flower.api.config.SyncedConfig;
import com.konpi.flower.common.proxy.network.SeasonCycleMessage;
import com.konpi.flower.seasons.Season.SeasonState;
import com.konpi.flower.seasons.intefaces.ISeasonState;
import com.konpi.flower.seasons.intefaces.SeasonHelper;
import com.konpi.flower.seasons.intefaces.SeasonHelper.ISeasonDataProvider;
import com.konpi.flower.seasons.savedata.Hooks;
import com.konpi.flower.seasons.savedata.SeasonSaveData;
import com.konpi.flower.seasons.savedata.SeasonTime;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * according to sereneseason
 *
 */
public class SeasonHandler implements ISeasonDataProvider {
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		World world = event.world;

		if (event.phase == TickEvent.Phase.END && !world.isRemote) {

			SeasonSaveData savedData = getSeasonSavedData(world);

			if (savedData.seasonCycleTicks++ > SeasonTime.ZERO.getCycleDuration()) {
				savedData.seasonCycleTicks = 0;
			}

			savedData.markDirty();
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		World world = player.world;
        sendSeasonUpdate(world);
	}

	private SeasonState lastSeason = null;
	public static final HashMap<Integer, Integer> clientSeasonCycleTicks = new HashMap<>();

	public static SeasonTime getClientSeasonTime() {
		Integer i = clientSeasonCycleTicks.get(0);
		return new SeasonTime(i == null ? 0 : i);
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		// Only do this when in the world
		if (Minecraft.getMinecraft().player == null)
			return;

		int dimension = Minecraft.getMinecraft().player.dimension;

		if (event.phase == TickEvent.Phase.END && dimension == 0) {
			clientSeasonCycleTicks.compute(dimension, (k, v) -> v == null ? 0 : v + 1);

			// Keep ticking as we're synchronized with the server only every second
			if (clientSeasonCycleTicks.get(dimension) > SeasonTime.ZERO.getCycleDuration()) {
				clientSeasonCycleTicks.put(dimension, 0);
			}

			SeasonTime calendar = new SeasonTime(clientSeasonCycleTicks.get(dimension));

			if (calendar.getSeasonState() != lastSeason) {
				Minecraft.getMinecraft().renderGlobal.loadRenderers();
				lastSeason = calendar.getSeasonState();
			}
		}
	}

	@SubscribeEvent
	public void onPopulateChunk(PopulateChunkEvent.Populate event) {
		if (!event.getWorld().isRemote && event.getType() != PopulateChunkEvent.Populate.EventType.ICE
				|| !(event.getWorld().provider.getDimension() == 0))
			return;

		event.setResult(Event.Result.DENY);
		BlockPos blockpos = new BlockPos(event.getChunkX() * 16, 0, event.getChunkZ() * 16).add(8, 0, 8);

		for (int k2 = 0; k2 < 16; ++k2) {
			for (int j3 = 0; j3 < 16; ++j3) {
				BlockPos blockpos1 = event.getWorld().getPrecipitationHeight(blockpos.add(k2, 0, j3));
				BlockPos blockpos2 = blockpos1.down();

				if (Hooks.canBlockFreezeInSeason(event.getWorld(), blockpos2, false,
						SeasonHelper.getSeasonState(event.getWorld()), true)) {
					event.getWorld().setBlockState(blockpos2, Blocks.ICE.getDefaultState(), 2);
				}

				if (Hooks.canSnowAtInSeason(event.getWorld(), blockpos1, true,
						SeasonHelper.getSeasonState(event.getWorld()), true)) {
					event.getWorld().setBlockState(blockpos1, Blocks.SNOW_LAYER.getDefaultState(), 2);
				}
			}
		}
	}
	
	public static void sendSeasonUpdate(World world)
    {
        if (!world.isRemote)
        {
            SeasonSaveData savedData = getSeasonSavedData(world);
            PacketHandler.instance.sendToAll(new SeasonCycleMessage(world.provider.getDimension(), savedData.seasonCycleTicks));
        }
    }

	public static SeasonSaveData getSeasonSavedData(World world) {
		MapStorage mapStorage = world.getPerWorldStorage();
		SeasonSaveData savedData = (SeasonSaveData) mapStorage.getOrLoadData(SeasonSaveData.class,
				SeasonSaveData.DATA_IDENTIFIER);

		// If the saved data file hasn't been created before, create it
		if (savedData == null) {
			savedData = new SeasonSaveData(SeasonSaveData.DATA_IDENTIFIER);

			int startingSeason = SyncedConfig.getIntValue(FlowerOption.STARTING_SEASON_STATE);

			if (startingSeason == 0) {
				savedData.seasonCycleTicks = (world.rand.nextInt(12)) * SeasonTime.ZERO.getSeasonStateDuration();
			}
			if (startingSeason > 0) {
				savedData.seasonCycleTicks = (startingSeason - 1) * SeasonTime.ZERO.getSeasonStateDuration();
			}

			mapStorage.setData(SeasonSaveData.DATA_IDENTIFIER, savedData);
			savedData.markDirty(); // Mark for saving
		}

		return savedData;
	}

	//
	// Used to implement getSeasonState in the API
	//

	public ISeasonState getServerSeasonState(World world) {
		SeasonSaveData savedData = getSeasonSavedData(world);
		return new SeasonTime(savedData.seasonCycleTicks);
	}

	public ISeasonState getClientSeasonState() {
		Integer i = clientSeasonCycleTicks.get(0);
		return new SeasonTime(i == null ? 0 : i);
	}
	
}
