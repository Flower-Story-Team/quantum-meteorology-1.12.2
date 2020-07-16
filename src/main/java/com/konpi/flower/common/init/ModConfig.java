package com.konpi.flower.common.init;

import com.google.common.collect.Lists;
import com.konpi.flower.common.config.ConfigHandler;
import com.konpi.flower.common.config.FlowerConfig;

import java.io.File;
import java.util.List;

public class ModConfig {

	public static List<ConfigHandler> configHandlers = Lists.newArrayList();

	public static FlowerConfig flowrconfig;

	public static void preInit(File configDir) {
		flowrconfig = new FlowerConfig(new File(configDir, "flower.cfg"));
	}

}
