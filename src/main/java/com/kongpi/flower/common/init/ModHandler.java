package com.kongpi.flower.common.init;

import com.kongpi.flower.common.handler.SeasonHandler;

import net.minecraftforge.common.MinecraftForge;

public class ModHandler {

	public static void preinit() {

	}

	public static void init() {
		MinecraftForge.EVENT_BUS.register(new SeasonHandler());
	}

	public static void posrinit() {

	}

}
