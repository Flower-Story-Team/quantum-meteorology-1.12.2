package com.konpi.flower.seasons.savedata;

import javax.annotation.Nullable;

import com.konpi.flower.seasons.Season;
import com.konpi.flower.seasons.Season.SeasonState;
import com.konpi.flower.seasons.intefaces.ISeasonState;
import com.konpi.flower.seasons.intefaces.SeasonHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class Hooks {
	///////////////////
	// World methods //
	///////////////////

	// Legacy
	public static boolean canSnowAtInSeason(World world, BlockPos pos, boolean checkLight,
			@Nullable ISeasonState seasonState) {
		return canSnowAtInSeason(world, pos, checkLight, seasonState, false);
	}

	public static boolean canSnowAtInSeason(World world, BlockPos pos, boolean checkLight,
			@Nullable ISeasonState seasonState, boolean useUnmodifiedTemperature) {
		Biome biome = world.getBiome(pos);
		float temperature = biome.getTemperature(pos);

		if (!useUnmodifiedTemperature && world.provider.getDimension() == 0) {
			if (!biome.canRain()) {
				return false;
			}

			temperature = getFloatTemperature(world, biome, pos);
		}

		if (temperature >= 0.15F) {
			return false;
		} else if (checkLight) {
			if (pos.getY() >= 0 && pos.getY() < 256 && world.getLightFor(EnumSkyBlock.BLOCK, pos) < 10) {
				IBlockState state = world.getBlockState(pos);

				if (state.getBlock().isAir(state, world, pos) && Blocks.SNOW_LAYER.canPlaceBlockAt(world, pos)) {
					return true;
				}
			}

			return false;
		}

		return true;
	}

	public static boolean canBlockFreezeInSeason(World world, BlockPos pos, boolean noWaterAdj,
			@Nullable ISeasonState seasonState) {
		return canBlockFreezeInSeason(world, pos, noWaterAdj, seasonState, false);
	}

	public static boolean canBlockFreezeInSeason(World world, BlockPos pos, boolean noWaterAdj,
			@Nullable ISeasonState seasonState, boolean useUnmodifiedTemperature) {
		Biome biome = world.getBiome(pos);
		float temperature = biome.getTemperature(pos);

		if (!useUnmodifiedTemperature && world.provider.getDimension() == 0) {
			if (!biome.canRain()) {
				return false;
			}

			temperature = getFloatTemperature(world, biome, pos);
		}

		if (temperature >= 0.15F) {
			return false;
		} else {
			if (pos.getY() >= 0 && pos.getY() < 256 && world.getLightFor(EnumSkyBlock.BLOCK, pos) < 10) {
				IBlockState iblockstate = world.getBlockState(pos);
				Block block = iblockstate.getBlock();

				if ((block == Blocks.WATER || block == Blocks.FLOWING_WATER)
						&& ((Integer) iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0) {
					if (!noWaterAdj) {
						return true;
					}

					boolean flag = world.getBlockState(pos.west()).getBlock() == Blocks.WATER
							&& world.getBlockState(pos.east()).getBlock() == Blocks.WATER
							&& world.getBlockState(pos.south()).getBlock() == Blocks.WATER
							&& world.getBlockState(pos.north()).getBlock() == Blocks.WATER;

					if (!flag) {
						return true;
					}
				}
			}

			return false;
		}
	}

	public static boolean isRainingAtInSeason(World world, BlockPos pos, ISeasonState seasonState) {
		Biome biome = world.getBiome(pos);

		if (biome.getEnableSnow() || (world.canSnowAt(pos, false))) {
			return false;
		}

		return biome.canRain();
	}

	///////////////////
	// Biome methods //
	///////////////////

	public static float getFloatTemperature(World world, Biome biome, BlockPos pos) {
		if (world.provider.getDimension() != 0) {
			return biome.getTemperature(pos);
		}

		return getFloatTemperature(
				new SeasonTime(SeasonHelper.getSeasonState(world).getSeasonCycleTicks()).getSeasonState(), biome, pos);
	}

	public static float getFloatTemperature(SeasonState SeasonState, Biome biome, BlockPos pos) {
		boolean tropicalBiome = !biome.canRain();
		float biomeTemp = biome.getTemperature(pos);

		if (!tropicalBiome && biome.getDefaultTemperature() <= 0.8F) {
			switch (SeasonState) {
			default:
				break;

			case LATE_SPRING:
			case EARLY_AUTUMN:
				biomeTemp = MathHelper.clamp(biomeTemp - 0.1F, -0.5F, 2.0F);
				break;

			case MID_SPRING:
			case MID_AUTUMN:
				biomeTemp = MathHelper.clamp(biomeTemp - 0.2F, -0.5F, 2.0F);
				break;

			case EARLY_SPRING:
			case LATE_AUTUMN:
				biomeTemp = MathHelper.clamp(biomeTemp - 0.4F, -0.5F, 2.0F);
				break;

			case EARLY_WINTER:
			case MID_WINTER:
			case LATE_WINTER:
				biomeTemp = MathHelper.clamp(biomeTemp - 0.8F, -0.5F, 2.0F);
				break;
			}
		}

		return biomeTemp;
	}

	////////////////////////////
	// EntityRenderer methods //
	////////////////////////////

	public static boolean shouldRenderRainSnow(World world, Biome biome) {
		return biome.canRain() || biome.getEnableSnow();
	}

	public static boolean shouldAddRainParticles(World world, Biome biome) {
		return biome.canRain();
	}
}
