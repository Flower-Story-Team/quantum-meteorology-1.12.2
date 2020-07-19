package com.kongpi.flower.common.config;

import java.io.File;

import com.kongpi.flower.Flower;
import com.kongpi.flower.api.PlantData;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlantConfig {

	private static Configuration config;
	private static String plantname;

	private static final String TempMax = "最高生长温度 | Temperature Max";
	private static final String Temp = "最适温度 | Proference Temperature";
	private static final String TempMin = "最低生长温度 | Temperature Min";
	private static final String HumMax = "最高生长湿度 | Humidity Max";
	private static final String Hum = "最适湿度 | Proference Humidity";
	private static final String HumMin = "最低生长湿度 | Humidity Min";
	private static final String NAME = "植物注册名 | The Registry name of plant";

	private static void loadConfig() {
		try {
			for (PlantData data : PlantData.values()) {
				plantname = data.toString();
				data.setPlant_name(config.get(plantname, NAME, data.getPlantName()).getString());

				data.setTemperature_min((float) config.get(plantname, TempMin, data.getTemperature_min()).getDouble());
				data.setTemperature_proference(
						(float) config.get(plantname, Temp, data.getTemperature_proference()).getDouble());
				data.setTemperature_max((float) config.get(plantname, TempMax, data.getTemperature_max()).getDouble());

				data.setAbs_humidity_min((float) config.get(plantname, HumMin, data.getHumidity_min()).getDouble());
				data.setAbs_humidity_profenerce(
						(float) config.get(plantname, Hum, data.getHumidity_profenerce()).getDouble());
				data.setAbs_humidity_max((float) config.get(plantname, HumMax, data.getHumidity_max()).getDouble());
			}
		} catch (Exception e) {
			Flower.logger.error("what??");
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

	public PlantConfig(File configfile) {
		config = new Configuration(configfile);
		loadConfig();
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(Flower.MODID)) {
			loadConfig();
		}
	}

}
