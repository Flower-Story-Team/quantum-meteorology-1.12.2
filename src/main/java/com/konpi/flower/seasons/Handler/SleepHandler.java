package com.konpi.flower.seasons.Handler;

import com.konpi.flower.seasons.savedata.SeasonSaveData;

import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

/**
 * according to sereneseason
 *
 */
public class SleepHandler {
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		if (event.phase == Phase.START && event.side == Side.SERVER) {
			WorldServer world = (WorldServer) event.world;

			// Called before all players are awoken for the next day
			if (world.areAllPlayersAsleep()) {
				SeasonSaveData seasonData = SeasonHandler.getSeasonSavedData(world);
				long timeDiff = 48000L - ((world.getWorldInfo().getWorldTime() + 48000L) % 48000L);
				seasonData.seasonCycleTicks += timeDiff;
				seasonData.markDirty();
			}
		}
	}
}
