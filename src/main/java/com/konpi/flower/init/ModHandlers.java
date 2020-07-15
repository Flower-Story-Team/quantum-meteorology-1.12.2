package com.konpi.flower.init;

import com.konpi.flower.seasons.Handler.ColorHandler;
import com.konpi.flower.seasons.Handler.PacketHandler;
import com.konpi.flower.seasons.Handler.RandomUpdateHandler;
import com.konpi.flower.seasons.Handler.SeasonHandler;
import com.konpi.flower.seasons.Handler.SeasonalCropGrawthHandler;
import com.konpi.flower.seasons.Handler.SleepHandler;
import com.konpi.flower.seasons.intefaces.SeasonHelper;

import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
