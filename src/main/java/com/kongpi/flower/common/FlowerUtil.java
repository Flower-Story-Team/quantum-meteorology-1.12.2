package com.kongpi.flower.common;

import com.kongpi.flower.common.config.CommonConfig;

import net.minecraft.init.Biomes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;

public class FlowerUtil {

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

	public static TextFormatting getHumidityColor(float humidity) {
		if (humidity < 20) {
			return TextFormatting.DARK_RED;
		} else if (humidity < 40) {
			return TextFormatting.RED;
		} else if (humidity < 60) {
			return TextFormatting.GOLD;
		} else if (humidity < 80) {
			return TextFormatting.AQUA;
		}
		return TextFormatting.BLUE;
	}

	public static int DayTemperature(long daytime) {
		if (!CommonConfig.temperature_dependence.temperature_day)
			return 0;
		int t = (int) (daytime % 24000);
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
		if (y < 10) {
			return 30;
		} else if (y < 20) {
			return 20;
		} else if (y < 30) {
			return 15;
		} else if (y < 40) {
			return 10;
		} else if (y < 55) {
			return 5;
		} else if (y < 64) {
			return 0;
		} else if (y < 80) {
			return -5;
		} else if (y < 120) {
			return -10;
		} else if (y < 200) {
			return -20;
		}
		return -30;
	}

	public static int BiomeTemperature(Biome biome) {
		if (CommonConfig.temperature_dependence.temperature_biome)
			return (int) biome.getDefaultTemperature() * 10 - 5;
		return 0;
	}
}
