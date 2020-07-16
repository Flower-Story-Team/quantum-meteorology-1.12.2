package com.konpi.flower.common.init;

import com.konpi.flower.common.seasons.Handler.ColorHandler;
import com.konpi.flower.common.seasons.Handler.PacketHandler;
import com.konpi.flower.common.seasons.Handler.RandomUpdateHandler;
import com.konpi.flower.common.seasons.Handler.SeasonHandler;
import com.konpi.flower.common.seasons.Handler.SeasonalCropGrawthHandler;
import com.konpi.flower.common.seasons.Handler.SleepHandler;
import com.konpi.flower.common.seasons.intefaces.SeasonHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class ModHandlers {

	private static final SeasonHandler SEASON_HANDLER = new SeasonHandler();

	public static void init() {
		PacketHandler.init();

		MinecraftForge.EVENT_BUS.register(SEASON_HANDLER);
		MinecraftForge.TERRAIN_GEN_BUS.register(SEASON_HANDLER);
		SeasonHelper.dataProvider = SEASON_HANDLER;

		MinecraftForge.EVENT_BUS.register(new RandomUpdateHandler());

		MinecraftForge.EVENT_BUS.register(new SleepHandler());

		MinecraftForge.EVENT_BUS.register(new SeasonalCropGrawthHandler());

	}

	public static void postInit() {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			ColorHandler.init();
		}
	}
}
