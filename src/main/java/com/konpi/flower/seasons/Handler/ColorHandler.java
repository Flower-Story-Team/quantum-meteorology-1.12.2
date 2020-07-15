package com.konpi.flower.seasons.Handler;

import javax.annotation.Nullable;

import com.konpi.flower.seasons.intefaces.ISeasonColor;
import com.konpi.flower.seasons.savedata.SeasonTime;

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
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeColorHelper;

/**
 * according to sereneseason
 *
 */
public class ColorHandler {

	public static void init() {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
			@Override
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos,
					int tintIndex) {
				BlockPlanks.EnumType plankstype = (BlockPlanks.EnumType) state.getValue(BlockOldLeaf.VARIANT);
				int FoliageColor = ColorizerFoliage.getFoliageColorBasic();
				if (worldIn != null && pos != null && Minecraft.getMinecraft().player.dimension == 0) {
					if (plankstype == BlockPlanks.EnumType.SPRUCE) {
						return ColorizerFoliage.getFoliageColorPine();
					} else {
						Biome biome = worldIn.getBiome(pos);
						SeasonTime calendar = SeasonHandler.getClientSeasonTime();
						ISeasonColor colorProvider = biome.canRain() ? calendar.getSeasonState()
								: calendar.getTropicalSeasonState();
						FoliageColor = colorProvider.getFoliageColor();
						return FoliageColor;
					}
				} else {
					return FoliageColor;
				}
			}
		}, Blocks.LEAVES);

		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
			@Override
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos,
					int tintIndex) {
				BlockPlanks.EnumType plankstype = (BlockPlanks.EnumType) state.getValue(BlockNewLeaf.VARIANT);
				int FoliageColor = ColorizerFoliage.getFoliageColorBasic();
				if (worldIn != null && pos != null && Minecraft.getMinecraft().player.dimension == 0) {
					if (plankstype == BlockPlanks.EnumType.SPRUCE) {
						return ColorizerFoliage.getFoliageColorPine();
					} else {
						Biome biome = worldIn.getBiome(pos);
						SeasonTime calendar = SeasonHandler.getClientSeasonTime();
						ISeasonColor colorProvider = biome.canRain() ? calendar.getSeasonState()
								: calendar.getTropicalSeasonState();
						FoliageColor = colorProvider.getFoliageColor();
						return FoliageColor;
					}
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
					Biome biome = worldIn.getBiome(pos);
					grassColor = ColorizerGrass.getGrassColor(biome.getTemperature(pos),
							(double) MathHelper.clamp(biome.getRainfall(), 0.0F, 1.0F));
					if (Minecraft.getMinecraft().player.dimension == 0) {
						SeasonTime calendar = SeasonHandler.getClientSeasonTime();
						ISeasonColor colorProvider = biome.canRain() ? calendar.getSeasonState()
								: calendar.getTropicalSeasonState();
						grassColor = colorProvider.getGrassColor();
					}
					return grassColor;
				}
				return grassColor;
			}

		}, Blocks.GRASS);
	}
}
