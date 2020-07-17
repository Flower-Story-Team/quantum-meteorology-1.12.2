package com.kongpi.flower.common.init;

import com.google.common.collect.Lists;
import com.kongpi.flower.common.config.ConfigHandler;
import com.kongpi.flower.common.config.PlantConfig;

import java.io.File;
import java.util.List;

public class ModConfig {

	public static List<ConfigHandler> configHandlers = Lists.newArrayList();

	public static PlantConfig plant;

	public static void preInit(File configDir) {
		plant = new PlantConfig(new File(configDir, "plant.cfg"));
	}

}
