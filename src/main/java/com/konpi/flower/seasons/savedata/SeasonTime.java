package com.konpi.flower.seasons.savedata;

import com.google.common.base.Preconditions;
import com.konpi.flower.api.config.FlowerOption;
import com.konpi.flower.api.config.SyncedConfig;
import com.konpi.flower.seasons.Season;
import com.konpi.flower.seasons.Season.SeasonState;
import com.konpi.flower.seasons.Season.TropicalSeasonState;
import com.konpi.flower.seasons.intefaces.ISeasonState;

/**
 * according to sereneseason
 *
 */
public final class SeasonTime implements ISeasonState {
	public static final SeasonTime ZERO = new SeasonTime(0);
	public final int time;

	public SeasonTime(int time) {
		Preconditions.checkArgument(time >= 0, "Time cannot be negative!");
		this.time = time;
	}

	@Override
	public int getDayDuration() {
		return SyncedConfig.getIntValue(FlowerOption.DAY_DURATION);
	}

	@Override
	public int getSeasonStateDuration() {
		return this.getDayDuration() * SyncedConfig.getIntValue(FlowerOption.SEASON_STATE_DURATION);
	}

	@Override
	public int getSeasonDuration() {
		return this.getSeasonStateDuration() * 3;
	}

	@Override
	public int getCycleDuration() {
		return this.getSeasonStateDuration() * Season.SeasonState.VALUES.length;
	}

	@Override
	public int getSeasonCycleTicks() {
		return this.time;
	}

	@Override
	public int getDay() {
		return this.time / this.getDayDuration();
	}

	@Override
	public SeasonState getSeasonState() {
		int index = (this.time / this.getSeasonStateDuration()) % Season.SeasonState.VALUES.length;
		return Season.SeasonState.VALUES[index];
	}

	@Override
	public Season getSeason() {
		return this.getSeasonState().getSeason();
	}

	@Override
	public TropicalSeasonState getTropicalSeasonState() {
		int index = ((((this.time / this.getSeasonStateDuration()) + 11) / 2) + 5)
				% Season.TropicalSeasonState.VALUES.length;
		return Season.TropicalSeasonState.VALUES[index];
	}

}
