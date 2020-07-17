package com.kongpi.flower.common.handler;

import javax.annotation.Nullable;

import com.kongpi.flower.api.Itime.Month;

import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;

public class ColorHandler {

	public static void init() {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
			@Override
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos,
					int tintIndex) {
				int FoliageColor = ColorizerFoliage.getFoliageColorBasic();
				if (worldIn != null && pos != null && Minecraft.getMinecraft().player.dimension == 0) {
					// Biome biome = worldIn.getBiome(pos);
					long t = Minecraft.getMinecraft().player.world.getWorldTime();
					FoliageColor = Month.getmonth(t).getFoliageColor();
					return FoliageColor;
				} else {
					return FoliageColor;
				}
			}
		}, Blocks.LEAVES);

		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
			@Override
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos,
					int tintIndex) {
				int FoliageColor = ColorizerFoliage.getFoliageColorBasic();
				if (worldIn != null && pos != null && Minecraft.getMinecraft().player.dimension == 0) {
					// Biome biome = worldIn.getBiome(pos);
					long t = Minecraft.getMinecraft().player.world.getWorldTime();
					FoliageColor = Month.getmonth(t).getFoliageColor();
					return FoliageColor;
				} else {
					return FoliageColor;
				}
			}
		}, Blocks.LEAVES2);

		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
			@Override
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos,
					int tintIndex) {
				int grassColor = ColorizerGrass.getGrassColor(0.5, 0.5);
				if (worldIn != null && pos != null && Minecraft.getMinecraft().player.dimension == 0) {
					// Biome biome = worldIn.getBiome(pos);
					long t = Minecraft.getMinecraft().player.world.getWorldTime();
					grassColor = Month.getmonth(t).getGrassColor();
				}
				return grassColor;
			}

		}, Blocks.GRASS, Blocks.TALLGRASS, Blocks.DOUBLE_PLANT);
	}
}
