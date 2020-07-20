package com.konpi.quantummeteorology.common.handler.season;

import com.konpi.quantummeteorology.api.config.FlowerOption;
import com.konpi.quantummeteorology.api.config.SyncedConfig;
import com.konpi.quantummeteorology.common.config.flower;
import com.konpi.quantummeteorology.common.savedata.season.SeasonSaveData;

import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

public class SleepHandler {

	long t = flower.general_config.day_duration;

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		if (event.phase == Phase.START && event.side == Side.SERVER) {
			WorldServer world = (WorldServer) event.world;

			// Called before all players are awoken for the next day
			if (world.areAllPlayersAsleep()) {
				SeasonSaveData seasonData = SeasonHandler.getSeasonSavedData(world);
				long timeDiff = t - ((world.getWorldInfo().getWorldTime() + t) % t);
				seasonData.seasonCycleTicks += timeDiff;
				seasonData.markDirty();
				SeasonHandler.sendSeasonUpdate(world);
			}
		}
	}
}
