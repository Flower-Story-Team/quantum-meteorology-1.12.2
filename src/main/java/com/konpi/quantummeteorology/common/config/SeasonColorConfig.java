package com.konpi.quantummeteorology.common.config;

import java.io.File;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.api.data.Month;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SeasonColorConfig {

	private static Configuration config;
	private static String monthname;

	private static void loadConfig() {
		try {
			for (Month month : Month.values()) {
				monthname = month.toString();
				month.setTemperature(config.get(monthname, "温度 | Temperature", month.getTemperature()).getInt());
				month.setFoliageColor1(
						config.get(monthname, "树叶颜色1 | Foliage Color 1", month.getFoliageColor1()).getInt());
				month.setRand1(config.get(monthname, "树叶颜色1比重 | Rand for foliage color 1", month.getRand1()).getInt());
				month.setFoliageColor2(
						config.get(monthname, "树叶颜色2 | Foliage Color 2", month.getFoliageColor2()).getInt());
				month.setRand2(config.get(monthname, "树叶颜色2比重 | Rand for foliage color 2", month.getRand2()).getInt());
				month.setGrassColor(config.get(monthname, "草地颜色 | Grass Color", month.getGrassColor()).getInt());
			}
		} catch (Exception e) {
			QuantumMeteorology.logger.error("what??");
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

	public SeasonColorConfig(File configfile) {
		config = new Configuration(configfile);
		loadConfig();
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(QuantumMeteorology.MODID)) {
			loadConfig();
		}
	}

}
