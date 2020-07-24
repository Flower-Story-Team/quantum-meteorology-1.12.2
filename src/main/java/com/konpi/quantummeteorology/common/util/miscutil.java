package com.konpi.quantummeteorology.common.util;

import com.konpi.quantummeteorology.common.config.CommonConfig;

import org.lwjgl.opengl.GL11;

import com.konpi.quantummeteorology.api.capabilities.Capabilities;
import com.konpi.quantummeteorology.api.data.BlockTemperature;
import com.konpi.quantummeteorology.api.data.Month;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class miscutil {

	public static void info(World world, EntityPlayer player) {
		BlockPos pos = player.getPosition();
		Biome biome = world.getBiome(pos);
		long worldtime = world.getWorldTime();
		System.out.println("\n\nday:" + DayTemperature(world)//
				+ " height:" + HeightTemperature(pos.getY())//
				+ " biome:" + BiomeTemperature(biome)//
				+ " block:" + BlockTemperature(world, pos)//
				+ " month:" + Month.getmonth(worldtime).getTemperature() + "\n"//
				+ " surround:" + miscutil.GetTemperature(world, player.getPosition()) //
				+ " temp:" + player.getCapability(Capabilities.TEMPERATURE, null).getTemperature()//
				+ " thirst:" + player.getCapability(Capabilities.THIRST, null).getThirst());
	}

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
		int dimension = world.provider.getDimension();
		Biome biome = world.getBiome(pos);
		long worldtime = world.getWorldTime();
		if (CommonConfig.isWhiteListDimension(dimension)) {
			return 20 + DayTemperature(world) + HeightTemperature(pos.getY()) + BiomeTemperature(biome)
					+ BlockTemperature(world, pos) + Month.getmonth(worldtime).getTemperature();
		} else if (dimension == 1) {
			return 20 + DayTemperature(world) + HeightTemperature(pos.getY()) + BiomeTemperature(biome)
					+ BlockTemperature(world, pos) + Month.JANUARY.getTemperature();
		} else if (dimension == -1) {
			return 20 + DayTemperature(world) + HeightTemperature(pos.getY()) + BiomeTemperature(biome)
					+ BlockTemperature(world, pos) + Month.JULY.getTemperature();
		} else {
			return 20;
		}
	}

	public static int DayTemperature(World world) {
		if (!CommonConfig.temperature_dependence.temperature_day)
			return 0;
		if (world.isDaytime())
			return 3;
		else
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
						if (world.getBlockState(pos.add(x, y, z)).getBlock().toString().equals(block.getname())) {
							temp += block.getTemperatureperblock() * Math.sqrt(x * x + y * y + z * z);
						}
					}
				}
			}
		}
		return temp;
	}

}
