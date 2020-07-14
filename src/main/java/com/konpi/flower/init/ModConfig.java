package com.konpi.flower.init;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.reflect.TypeToken;
import com.konpi.flower.config.ConfigHandler;
import com.konpi.flower.config.FlowerConfig;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModConfig {

	public static List<ConfigHandler> configHandlers = Lists.newArrayList();

	public static FlowerConfig flowrconfig;

	public static void preInit(File configDir) {
		flowrconfig = new FlowerConfig(new File(configDir, "flower.cfg"));
	}

}
