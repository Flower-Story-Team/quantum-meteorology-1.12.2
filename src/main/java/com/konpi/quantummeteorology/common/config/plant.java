package com.konpi.quantummeteorology.common.config;

import java.io.File;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.api.config.FlowerOption;

/**
 * 默认的季节生长配置文件
 *
 */
public class plant extends ConfigHandler {

	public static final String PLANT_SETTINGS = "Plant Seetings";

	public String[] spring_crops;
	public String[] summer_crops;
	public String[] autumn_crops;
	public String[] winter_crops;

	public plant(File configFile) {
		super(configFile, "Flower Settings");
	}

	@Override
	protected void loadConfiguration() {
		try {
			this.spring_crops = config.getStringList("spring crops", this.PLANT_SETTINGS,
					new String[] { "minecraft:potato", "minecraft:carrot", "minecraft:sapling", "minecraft:nether_wart",
							"minecraft:tallgrass", "minecraft:grass", "minecraft:red_mushroom",
							"minecraft:brown_mushroom", "flower:tomato", "flower:corn", "flower:chinese_cabbage",
							"flower:chili", "flower:pepper", "flower:scallion" },
					"Crops growable in Spring (List either the seed item for the crop, or the crop block itself)");

			this.summer_crops = config.getStringList("summer crops", this.PLANT_SETTINGS,
					new String[] { "minecraft:melon_seeds", "minecraft:wheat_seeds", "minecraft:reeds",
							"minecraft:cocoa", "minecraft:cactus", "minecraft:sapling", "minecraft:nether_wart",
							"minecraft:tallgrass", "minecraft:grass", "minecraft:red_mushroom",
							"minecraft:brown_mushroom", "simplecorn:kernels", "flower:tomato", "flower:eggplant",
							"flower:chili", "flower:pepper" },
					"Crops growable in Summer (List either the seed item for the crop, or the crop block itself)");

			this.autumn_crops = config.getStringList("autumn crops", this.PLANT_SETTINGS,
					new String[] { "minecraft:carrot", "minecraft:pumpkin_seeds", "minecraft:wheat_seeds",
							"minecraft:beetroot_seeds", "minecraft:sapling", "minecraft:nether_wart", "minecraft:grass",
							"minecraft:red_mushroom", "minecraft:brown_mushroom", "flower:corn" },
					"Crops growable in Autumn (List either the seed item for the crop, or the crop block itself)");

			this.winter_crops = config.getStringList("winter crops", this.PLANT_SETTINGS,
					new String[] { "minecraft:sapling", "minecraft:nether_wart", "minecraft:red_mushroom",
							"minecraft:brown_mushroom" },
					"Crops growable in Winter (List either the seed item for the crop, or the crop block itself)");
		} catch (Exception e) {
			QuantumMeteorology.logger.error("Serene Seasons has encountered a problem loading seasons.cfg", e);
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}
}
