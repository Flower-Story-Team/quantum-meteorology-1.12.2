package com.kongpi.flower.common;

import net.minecraft.util.text.TextFormatting;

public class FlowerUtil {

	public static void out(String s) {
		System.out.println("\n" + s);
	}

	public static TextFormatting getTemperatureColor(float temperature) {
		if (temperature < -15) {
			return TextFormatting.DARK_BLUE;
		} else if (temperature < 0) {
			return TextFormatting.BLUE;
		} else if (temperature < 20) {
			return TextFormatting.GREEN;
		} else if (temperature < 35) {
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
}
