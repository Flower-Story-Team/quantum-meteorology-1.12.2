package com.konpi.quantummeteorology.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.konpi.quantummeteorology.common.init.ModGuiLoader;
import com.konpi.quantummeteorology.common.init.ModHandler;
import com.konpi.quantummeteorology.common.init.ModStates;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event)
	{
		new ModGuiLoader();
	}

	public void init(FMLInitializationEvent event) {
		ModStates.init();
		ModHandler.init();
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

}