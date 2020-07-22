package com.konpi.quantummeteorology.common.init;

import com.konpi.quantummeteorology.client.handler.ColorHandler;
import com.konpi.quantummeteorology.client.handler.SeasonChangeHandler;
import com.konpi.quantummeteorology.common.handler.CapabilityHandler;
import com.konpi.quantummeteorology.common.handler.SeasonalGrowthHandler;

import net.minecraftforge.common.MinecraftForge;

public class ModHandler {

	public static void preInit() {

	}

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new SeasonalGrowthHandler());
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
	}

	public static void postInit() {
		MinecraftForge.EVENT_BUS.register(new SeasonChangeHandler());
		ColorHandler.init();
	}

}
