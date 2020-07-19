package com.kongpi.flower.api;

import java.util.Random;

import com.google.common.base.Preconditions;
import com.kongpi.flower.Flower;
import com.kongpi.flower.common.config.CommonConfig;
import com.kongpi.flower.common.config.CommonConfig;

import net.minecraft.client.resources.I18n;

/**
 * 月份
 */
public enum Month {

	JANUARY(Season.WINTER, -15, 0xCCCC99, 1, 0, 0, 0xCCCC99), // 1
	FEBRUARY(Season.WINTER, -4, 0x669966, 1, 0, 0, 0x669966), // 2
	MARCH(Season.SPRING, 4, 0x66CC33, 3, 0x66CC66, 1, 0x66CC33), // 3
	APRIL(Season.SPRING, 12, 0x66CC66, 3, 0x66CC33, 1, 0x66CC66), // 4
	MAY(Season.SPRING, 20, 0x339933, 1, 0x009933, 3, 0x339933), // 5
	JUNE(Season.SUMMER, 27, 0x009933, 3, 0x006600, 1, 0x009933), // 6
	JULY(Season.SUMMER, 35, 0x006600, 2, 0x6633, 1, 0x006600), // 7
	AUGUST(Season.SUMMER, 27, 0x669900, 1, 0x006600, 4, 0x669900), // 8
	SEPTEMBER(Season.AUTUMN, 20, 0x666633, 1, 0x669900, 2, 0x666633), // 9
	OCTOBER(Season.AUTUMN, 12, 0xCCC000, 3, 0x991100, 1, 0x666600), // 10
	NOVEMBER(Season.AUTUMN, 4, 0xCCC000, 2, 0xCCC099, 1, 0x663300), // 11
	DECEMBER(Season.WINTER, -4, 0xCCCC99, 1, 0, 0, 0xCCCC99); // 12

	public static final int getDayDuration() {
		return 24000;
	}

	public static final int getMonthDuration() {
		return 24000 * CommonConfig.general_config.month_duration;
	}

	public static final int getYearDuration() {
		return getMonthDuration() * 12;
	}

	public static final Month getmonth(long worldtime) {
		// 返回季节时间的对应季节
		// 季节时间 = 补时 + 世界时间
		int i = ((int) worldtime + CommonConfig.general_config.starting_time) / getMonthDuration();
		// 区间限定
		while (i > 11) {
			i -= 12;
		}
		while (i < 0) {
			i += 12;
		}
		return values()[i];
	}

	private Season season;
	private int temperature;
	private int foliageColor1;
	private int rand1;
	private int foliageColor2;
	private int rand2;
	private int grassColor;

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

	public int getFoliageColor1() {
		return foliageColor1;
	}

	public int getFoliageColor2() {
		return foliageColor2;
	}

	public int getRand1() {
		return rand1;
	}

	public int getRand2() {
		return rand2;
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

	public void setFoliageColor1(int foliageColor1) {
		this.foliageColor1 = foliageColor1;
	}

	public void setFoliageColor2(int foliageColor2) {
		this.foliageColor2 = foliageColor2;
	}

	public void setGrassColor(int grassColor) {
		this.grassColor = grassColor;
	}

	public void setRand1(int rand1) {
		this.rand1 = rand1;
	}

	public void setRand2(int rand2) {
		this.rand2 = rand2;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	/**
	 * 季节
	 */
	public enum Season {
		SPRING, SUMMER, AUTUMN, WINTER;
	}

}
