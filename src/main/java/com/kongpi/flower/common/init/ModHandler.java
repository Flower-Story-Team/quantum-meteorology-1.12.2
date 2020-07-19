package com.kongpi.flower.common.init;

import com.kongpi.flower.common.handler.CapabilityHandler;
import com.kongpi.flower.client.handler.ColorHandler;
import com.kongpi.flower.client.handler.SeasonChangeHandler;
import com.kongpi.flower.common.handler.SeasonalGrowthHandler;

import net.minecraftforge.common.MinecraftForge;

public class ModHandler {

	public static void preinit() {

	}

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new SeasonalGrowthHandler());
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
	}

	public static void postinit() {
		MinecraftForge.EVENT_BUS.register(new SeasonChangeHandler());
		ColorHandler.init();
	}

}
