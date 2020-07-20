package com.konpi.quantummeteorology.api.season;

import com.konpi.quantummeteorology.common.seasons.Season;
import com.konpi.quantummeteorology.common.seasons.Season.SeasonState;

public interface ISeasonState {
	/**
	 * 一天的tick，默认48000
	 *
	 * @return The duration in ticks
	 */
	int getDayDuration();

	/**
	 * 一个季节状态的长度
	 *
	 * @return The duration in ticks
	 */
	int getSeasonStateDuration();

	/**
	 * 一个季节的长度
	 *
	 * @return The duration in ticks
	 */
	int getSeasonDuration();

	/**
	 * 一年的长度
	 *
	 * @return The duration in ticks
	 */
	int getCycleDuration();

	/**
	 * 当前总体周期所经过的时间(以计时为单位)。一个周期相当于一年，由夏、秋、冬、春组成。
	 *
	 * @return The time in ticks
	 */
	int getSeasonCycleTicks();

	/**
	 * 获取经过的天数。
	 *
	 * @return The current day
	 */
	int getDay();

	/**
	 * 获得当前季节状态
	 *
	 * @return The current sub season
	 */
	SeasonState getSeasonState();

	/**
	 * 得到当前的季节。
	 *
	 * @return The current season
	 */
	Season getSeason();
}
