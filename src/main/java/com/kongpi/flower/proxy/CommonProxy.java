package com.kongpi.flower.proxy;

import java.io.File;

import com.kongpi.flower.Flower;
import com.kongpi.flower.common.config.PlantConfig;
import com.kongpi.flower.common.config.SeasonColorConfig;
import com.kongpi.flower.common.init.ModHandler;
import com.kongpi.flower.common.init.ModStates;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		new PlantConfig(new File(new File(event.getModConfigurationDirectory(), Flower.NAME), "plant.cfg"));
		new SeasonColorConfig(new File(new File(event.getModConfigurationDirectory(), Flower.NAME), "seasoncolor.cfg"));
	}

	public void init(FMLInitializationEvent event) {
		ModStates.init();
		ModHandler.init();
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

}