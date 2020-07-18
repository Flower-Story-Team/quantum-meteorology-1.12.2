package com.kongpi.flower.common.config;

import java.io.File;

import com.kongpi.flower.Flower;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.Int;

public class CommonConfig {

	private static Configuration config;
	public static int Month_Duration;

	public static void loadConfig() {
		try {
			Month_Duration = config
					.get("time settings", "Month Duration", 8, "一个月的天数 | Days of a month", 1, Int.MaxValue()).getInt();
		} catch (Exception e) {
			Flower.logger.error("??");
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

	public static void preinit(File configfile) {
		config = new Configuration(configfile);
		loadConfig();
		MinecraftForge.EVENT_BUS.register(new CommonConfig());
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(Flower.MODID)) {
			loadConfig();
		}
	}
}
