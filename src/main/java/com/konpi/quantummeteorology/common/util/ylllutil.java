package com.konpi.quantummeteorology.common.util;

import com.konpi.quantummeteorology.common.config.CommonConfig;
import com.konpi.quantummeteorology.api.data.BlockTemperature;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class ylllutil {

	public static TextFormatting getTemperatureColor(float temperature) {
		if (temperature < -10) {
			return TextFormatting.DARK_BLUE;
		} else if (temperature < 10) {
			return TextFormatting.BLUE;
		} else if (temperature < 30) {
			return TextFormatting.GREEN;
		} else if (temperature < 50) {
			return TextFormatting.GOLD;
		}
		return TextFormatting.RED;
	}

	public static int GetTemperature(World world, BlockPos pos) {
		Biome biome = world.getBiome(pos);
		long worldtime = world.getWorldTime();
		return 20 + DayTemperature(worldtime) + HeightTemperature(pos.getY()) + BiomeTemperature(biome)
				+ BlockTemperature(world, pos);
	}

	public static int DayTemperature(long worldtime) {
		if (!CommonConfig.temperature_dependence.temperature_day)
			return 0;
		int t = (int) (worldtime % 24000);
		if (t < 4000) {
			return -2;
		} else if (t < 8000) {
			return -1;
		} else if (t < 12000) {
			return 1;
		} else if (t < 16000) {
			return 2;
		} else if (t < 20000) {
			return 1;
		}
		return 0;
	}

	public static int HeightTemperature(int y) {
		if (!CommonConfig.temperature_dependence.temperature_height)
			return 0;
		if (y < 16) {
			return 20;
		} else if (y < 36) {
			return 15;
		} else if (y < 56) {
			return 10;
		} else if (y < 65) {
			return 5;
		} else if (y < 71) {
			return 0;
		} else if (y < 101) {
			return -5;
		} else if (y < 161) {
			return -10;
		}
		return -15;
	}

	public static int BiomeTemperature(Biome biome) {
		if (CommonConfig.temperature_dependence.temperature_biome)
			return (int) biome.getDefaultTemperature() * 10 - 5;
		return 0;
	}

	public static int BlockTemperature(World world, BlockPos pos) {
		int temp = 0;
		for (BlockTemperature block : BlockTemperature.values()) {
			int i = block.getRange();
			for (int x = -i; x < i; x++) {
				for (int y = -i; y < i; y++) {
					for (int z = -i; z < i; z++) {
						if (world.getBlockState(pos.add(x, y, z)).toString().equals(block.getState())
								&& Math.sqrt(x * x + y * y + z * z) <= i) {
							temp += block.getTemperatureperblock() * Math.sqrt(x * x + y * y + z * z);
						}
					}
				}
			}
		}
		return temp;
	}
}
