package com.konpi.quantummeteorology.common.init;

import com.konpi.quantummeteorology.api.season.SeasonHelper;
import com.konpi.quantummeteorology.common.handler.ExtendedStateHandler;
import com.konpi.quantummeteorology.common.handler.PacketHandler;
import com.konpi.quantummeteorology.common.handler.season.ColorHandler;
import com.konpi.quantummeteorology.common.handler.season.RandomUpdateHandler;
import com.konpi.quantummeteorology.common.handler.season.SeasonHandler;
import com.konpi.quantummeteorology.common.handler.season.SeasonalCropGrawthHandler;
import com.konpi.quantummeteorology.common.handler.temperature.TemperatureOverlayHandler;
import com.konpi.quantummeteorology.common.handler.temperature.TemperatureStatTableHandler;
import com.konpi.quantummeteorology.common.handler.thirst.DrinkHandler;
import com.konpi.quantummeteorology.common.handler.thirst.ThirstOverlayHandler;
import com.konpi.quantummeteorology.common.handler.thirst.ThirstStatHandler;

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
		MinecraftForge.EVENT_BUS.register(new ExtendedStateHandler());
		MinecraftForge.EVENT_BUS.register(new SeasonalCropGrawthHandler());
		MinecraftForge.EVENT_BUS.register(new ThirstStatHandler());
		MinecraftForge.EVENT_BUS.register(new DrinkHandler());

		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			MinecraftForge.EVENT_BUS.register(new TemperatureOverlayHandler());
			MinecraftForge.EVENT_BUS.register(new TemperatureStatTableHandler());
			MinecraftForge.EVENT_BUS.register(new ThirstOverlayHandler());
		}
	}

	public static void postInit() {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			ColorHandler.init();
		}
	}
}
