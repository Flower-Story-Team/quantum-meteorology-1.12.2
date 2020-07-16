package com.konpi.flower.common.savedata.season;

import com.google.common.base.Preconditions;
import com.konpi.flower.api.config.FlowerOption;
import com.konpi.flower.api.config.SyncedConfig;
import com.konpi.flower.api.season.ISeasonState;
import com.konpi.flower.common.config.flower;
import com.konpi.flower.common.seasons.Season;
import com.konpi.flower.common.seasons.Season.SeasonState;

//
public final class SeasonTime implements ISeasonState {
	public static final SeasonTime ZERO = new SeasonTime(0);
	public final int time;

	public SeasonTime(int time) {
		Preconditions.checkArgument(time >= 0, "Time cannot be negative!");
		this.time = time;
	}

	@Override
	public int getDayDuration() {
		return flower.general_config.day_duration;
	}

	@Override
	public int getSeasonStateDuration() {
		return this.getDayDuration() * flower.general_config.season_state_duration;
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

}
