package com.konpi.flower.config;

import com.konpi.flower.Flower;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Flower.MODID, name = Flower.NAME + "/plant", category = "")
@Mod.EventBusSubscriber
public class PlantConfig {

	public static SeasonalPlants seasonal_plants = new SeasonalPlants();

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(Flower.MODID)) {
			ConfigManager.sync(event.getModID(), Config.Type.INSTANCE); // resync config
		}
	}

	public static class SeasonalPlants {
		@Config.Comment({
				"Crops growable in Spring (List either the seed item for the crop, or the crop block itself)" })
		public static String[] spring_crops = new String[] { "minecraft:potato", "minecraft:carrot",
				"minecraft:sapling", "minecraft:nether_wart", "minecraft:tallgrass", "minecraft:grass",
				"minecraft:red_mushroom", "minecraft:brown_mushroom", "flower:tomato", "flower:corn",
				"flower:chinese_cabbage", "flower:chili", "flower:pepper"};

		@Config.Comment({
				"Crops growable in Summer (List either the seed item for the crop, or the crop block itself)" })
		public static String[] summer_crops = new String[] { "minecraft:melon_seeds", "minecraft:wheat_seeds",
				"minecraft:reeds", "minecraft:cocoa", "minecraft:cactus", "minecraft:sapling", "minecraft:nether_wart",
				"minecraft:tallgrass", "minecraft:grass", "minecraft:red_mushroom", "minecraft:brown_mushroom",
				"simplecorn:kernels", "flower:tomato", "flower:eggplant","flower:chili", "flower:pepper" };

		@Config.Comment({
				"Crops growable in Autumn (List either the seed item for the crop, or the crop block itself)" })
		public static String[] autumn_crops = new String[] { "minecraft:carrot", "minecraft:pumpkin_seeds",
				"minecraft:wheat_seeds", "minecraft:beetroot_seeds", "minecraft:sapling", "minecraft:nether_wart",
				"minecraft:grass", "minecraft:red_mushroom", "minecraft:brown_mushroom", "flower:corn" };

		@Config.Comment({
				"Crops growable in Winter (List either the seed item for the crop, or the crop block itself)" })
		public static String[] winter_crops = new String[] { "minecraft:sapling", "minecraft:nether_wart",
				"minecraft:red_mushroom", "minecraft:brown_mushroom" };
	}
}
