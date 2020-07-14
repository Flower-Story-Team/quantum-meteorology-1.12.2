package com.konpi.flower.config;

import java.io.File;

import com.konpi.flower.Flower;
import com.konpi.flower.api.config.FlowerOption;

public class FlowerConfig extends ConfigHandler {

	public static final String TIME_SETTINGS = "Time Settings";

	public FlowerConfig(File configFile) {
		super(configFile, "Flower Settings");
	}

	@Override
	protected void loadConfiguration() {
		try {
			addSyncedValue(FlowerOption.DAY_DURATION, 48000, TIME_SETTINGS, "The duration of a Minecraft day in ticks",
					20, Integer.MAX_VALUE);
			addSyncedValue(FlowerOption.SEASON_STATE_DURATION, 7, TIME_SETTINGS, "The duration of a sub season in days",
					1, Integer.MAX_VALUE);
			addSyncedValue(FlowerOption.STARTING_SEASON_STATE, 5, TIME_SETTINGS,
					"The starting sub season for new worlds.  0 = Random, 1 - 3 = Early/Mid/Late Spring, 4 - 6 = Early/Mid/Late Summer, 7 - 9 = Early/Mid/Late Autumn, 10 - 12 = Early/Mid/Late Winter",
					0, 12);
		} catch (Exception e) {
			Flower.logger.error("Serene Seasons has encountered a problem loading seasons.cfg", e);
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

}
