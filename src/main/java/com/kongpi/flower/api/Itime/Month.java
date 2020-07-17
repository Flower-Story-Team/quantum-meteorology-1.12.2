package com.kongpi.flower.api.Itime;

import java.util.Random;

import com.google.common.base.Preconditions;
import com.kongpi.flower.common.config.MiscConfig;

/**
 * 月份
 */
public enum Month {

	MARCH(Season.SPRING, -3, 0x66CC33, 3, 0x66CC66, 1, 0x66CC33), // 3
	APRIL(Season.SPRING, -1, 0x66CC66, 3, 0x66CC33, 1, 0x66CC66), // 4
	MAY(Season.SPRING, 1, 0x339933, 1, 0x009933, 3, 0x339933), // 5

	JUNE(Season.SUMMER, 3, 0x009933, 3, 0x006600, 1, 0x009933), // 6
	JULY(Season.SUMMER, 5, 0x006600, 2, 0x6633, 1, 0x006600), // 7
	AUGUST(Season.SUMMER, 7, 0x669900, 1, 0x006600, 4, 0x669900), // 8

	SEPTEMBER(Season.AUTUMN, 4, 0x666633, 1, 0x669900, 2, 0x666633), // 9
	OCTOBER(Season.AUTUMN, 1, 0xCCC000, 3, 0x991100, 1, 0x666600), // 10
	NOVEMBER(Season.AUTUMN, -2, 0xCCC000, 2, 0xCCC099, 1, 0x663300), // 11

	DECEMBER(Season.WINTER, -4, 0xCCCC99, 1, 0, 0, 0xCCCC99), // 12
	JANUARY(Season.WINTER, -7, 0xCCCC99, 1, 0, 0, 0xCCCC99), // 1
	FEBRUARY(Season.WINTER, -5, 0x669966, 1, 0, 0, 0x669966);// 2

	public static final Month[] VALUES = Month.values();

	public static final int getDayDuration() {
		return 24000;
	}

	public static final int getMonthDuration() {
		return 24000 * MiscConfig.general_config.month_duration;
	}

	public static final void preinit() {
		int i = MiscConfig.general_config.staring_month;
		starting_time = (i == 0 ? new Random().nextInt(12) * 24000 : i * 24000);
	}

	public static final long getStartingTime() {
		Preconditions.checkArgument(starting_time > -1, "Month didn't init!");
		return starting_time;
	}

	public static final Month getmonth(long worldtime) {
		// long i = worldtime / getMonthDuration();
		long i = worldtime / 800;
		while (i > 11) {
			i -= 12;
		}
		return VALUES[(int) i];
	}

	private Season season;
	private int temperature;
	private int foliageColor1;
	private int rand1;
	private int foliageColor2;
	private int rand2;
	private int grassColor;

	private static long starting_time = -1;

	/**
	 * @param season
	 *            该月所属季节
	 * @param temperatur
	 *            该月温度
	 * @param foliageColor1
	 *            第一种树叶颜色
	 * @param rand1
	 *            第一种颜色的比重
	 * @param foliageColor2
	 *            第二种树叶颜色
	 * @param rand2
	 *            第二种颜色的比重 这两个颜色比重是两者相加后计算
	 * @param grassColor
	 *            草地颜色
	 */
	Month(Season season, int temperature, int foliageColor1, int rand1, int foliageColor2, int rand2, int grassColor) {
		this.season = season;
		this.temperature = temperature;
		this.foliageColor1 = foliageColor1;
		this.rand1 = rand1;
		this.foliageColor2 = foliageColor2;
		this.rand2 = rand2;
		this.grassColor = grassColor;
	}

	public Season getSeason() {
		return this.season;
	}

	public int getTemperature() {
		return this.temperature;
	}

	public int getFoliageColor() {
		int i = new Random().nextInt(this.rand1 + this.rand2);
		if (i < this.rand1)
			return this.foliageColor1;
		else
			return this.foliageColor2;
	}

	public int getGrassColor() {
		return this.grassColor;
	}

	/**
	 * 季节
	 */
	public enum Season {
		SPRING, SUMMER, AUTUMN, WINTER;
	}

}
