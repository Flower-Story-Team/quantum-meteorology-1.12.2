package com.kongpi.flower.proxy;

import java.io.File;

import com.kongpi.flower.Flower;
import com.kongpi.flower.common.config.CommonConfig;
import com.kongpi.flower.common.config.PlantConfig;
import com.kongpi.flower.common.init.ModHandler;

import net.minecraft.world.biome.Biome.FlowerEntry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		PlantConfig.preinit(new File(new File(event.getModConfigurationDirectory(), Flower.NAME), "plant.cfg"));
		CommonConfig.preinit(new File(new File(event.getModConfigurationDirectory(), Flower.NAME), "common.cfg"));
	}

	public void init(FMLInitializationEvent event) {
		ModHandler.init();
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

}