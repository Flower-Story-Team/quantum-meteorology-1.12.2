package com.kongpi.flower.common.init;

import com.kongpi.flower.common.handler.SeasonHandler;
import com.kongpi.flower.common.handler.SeasonalCropGrawthHandler;

import net.minecraftforge.common.MinecraftForge;

public class ModHandler {

	public static void preinit() {

	}

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new SeasonHandler());
		MinecraftForge.EVENT_BUS.register(new SeasonalCropGrawthHandler());
	}

	public static void postinit() {

	}

}
