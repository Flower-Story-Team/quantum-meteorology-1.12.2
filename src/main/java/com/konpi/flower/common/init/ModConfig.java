package com.konpi.flower.common.init;

import com.google.common.collect.Lists;
import com.konpi.flower.common.config.ConfigHandler;
import com.konpi.flower.common.config.plant;

import java.io.File;
import java.util.List;

public class ModConfig {

	public static List<ConfigHandler> configHandlers = Lists.newArrayList();

	public static plant plant;

	public static void preInit(File configDir) {
		plant = new plant(new File(configDir, "plants.cfg"));
	}

}
