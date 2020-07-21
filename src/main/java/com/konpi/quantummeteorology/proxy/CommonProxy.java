package com.konpi.quantummeteorology.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.config.PlantConfig;
import com.konpi.quantummeteorology.common.config.SeasonColorConfig;
import com.konpi.quantummeteorology.common.init.ModHandler;
import com.konpi.quantummeteorology.common.init.ModStates;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		new PlantConfig(new File(new File(event.getModConfigurationDirectory(), QuantumMeteorology.NAME), "plant.cfg"));
		new SeasonColorConfig(
				new File(new File(event.getModConfigurationDirectory(), QuantumMeteorology.NAME), "seasoncolor.cfg"));
	}

	public void init(FMLInitializationEvent event) {
		ModStates.init();
		ModHandler.init();
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

}