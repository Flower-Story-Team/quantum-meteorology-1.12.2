package com.kongpi.flower.proxy;

import java.io.File;

import com.kongpi.flower.Flower;
import com.kongpi.flower.api.Itime.Month;
import com.kongpi.flower.common.init.ModConfig;
import com.kongpi.flower.common.init.ModHandler;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public static File configDirectory;

	public void preInit(FMLPreInitializationEvent event) {
		configDirectory = new File(event.getModConfigurationDirectory(), Flower.NAME);
		Month.preinit();
		ModConfig.preInit(configDirectory);
	}

	public void init(FMLInitializationEvent event) {
		ModHandler.init();
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

}