package com.konpi.quantummeteorology.common.config;

import java.io.File;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.api.data.PlantData;

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
			}
		} catch (Exception e) {
			QuantumMeteorology.logger.error("what??");
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
		if (event.getModID().equals(QuantumMeteorology.MODID)) {
			loadConfig();
		}
	}

}
