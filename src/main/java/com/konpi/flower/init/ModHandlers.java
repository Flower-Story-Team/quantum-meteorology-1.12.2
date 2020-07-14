package com.konpi.flower.init;

import com.konpi.flower.seasons.Handler.BirchColorHandler;
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
		// PacketHandler.init();

		// Handlers for functionality related to seasons
		MinecraftForge.EVENT_BUS.register(SEASON_HANDLER);
		MinecraftForge.TERRAIN_GEN_BUS.register(SEASON_HANDLER);
		SeasonHelper.dataProvider = SEASON_HANDLER;

		MinecraftForge.EVENT_BUS.register(new RandomUpdateHandler());

		MinecraftForge.EVENT_BUS.register(new SleepHandler());

		MinecraftForge.EVENT_BUS.register(new SeasonalCropGrawthHandler());

		// if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
		// registerSeasonColourHandlers();
		// }
	}
	// TODO:用方块染色重写

	// @SideOnly(Side.CLIENT)
	// private static BiomeColorHelper.ColorResolver originalGrassColorResolver;
	// @SideOnly(Side.CLIENT) // .ColorResolver
	// private static BiomeColorHelper originalFoliageColorResolver;
	//
	// @SideOnly(Side.CLIENT)
	// private static void registerSeasonColourHandlers() {
	// originalGrassColorResolver = BiomeColorHelper.GRASS_COLOR;
	// originalFoliageColorResolver = BiomeColorHelper.FOLIAGE_COLOR;
	//
	// BiomeColorHelper.GRASS_COLOR = (biome, blockPosition) -> {
	// SeasonTime calendar = SeasonHandler.getClientSeasonTime();
	// ISeasonColorProvider colorProvider = BiomeConfig.usesTropicalSeasons(biome) ?
	// calendar.getTropicalSeason()
	// : calendar.getSubSeason();
	// return SeasonColourUtil.applySeasonalGrassColouring(colorProvider, biome,
	// originalGrassColorResolver.getColorAtPos(biome, blockPosition));
	// };
	//
	// BiomeColorHelper.FOLIAGE_COLOR = (biome, blockPosition) -> {
	// SeasonTime calendar = SeasonHandler.getClientSeasonTime();
	// ISeasonColorProvider colorProvider = BiomeConfig.usesTropicalSeasons(biome) ?
	// calendar.getTropicalSeason()
	// : calendar.getSubSeason();
	// return SeasonColourUtil.applySeasonalFoliageColouring(colorProvider, biome,
	// originalFoliageColorResolver.getColorAtPos(biome, blockPosition));
	// };
	// }

	public static void postInit() {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			BirchColorHandler.init();
		}
	}
}
