package com.konpi.flower.common.seasons.intefaces;

import com.konpi.flower.common.seasons.Season;
import net.minecraft.util.math.BlockPos;

/**
 * according to sereneseason
 *
 */
public interface ISeasonedWorld {
	boolean canSnowAtInSeason(BlockPos pos, boolean checkLight, Season season);
	boolean canBlockFreezeInSeason(BlockPos pos, boolean noWaterAdj, Season season);
}
