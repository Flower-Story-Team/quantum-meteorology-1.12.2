package com.konpi.flower.common.seasons;

import java.util.Random;

import com.konpi.flower.common.seasons.intefaces.ISeasonColor;

public enum Season {
	SPRING, SUMMER, AUTUMN, WINTER;

	public enum SeasonState implements ISeasonColor {

		EARLY_SPRING(SPRING, 0x66CC33, 3, 0x66CC66, 1, 0x66CC33), //
		MID_SPRING(SPRING, 0x66CC66, 3, 0x66CC33, 1, 0x66CC66), //
		LATE_SPRING(SPRING, 0x339933, 1, 0x009933, 3, 0x339933), //

		EARLY_SUMMER(SUMMER, 0x009933, 3, 0x006600, 1, 0x009933), //
		MID_SUMMER(SUMMER, 0x006600, 2, 0x6633, 1, 0x006600), //
		LATE_SUMMER(SUMMER, 0x669900, 1, 0x006600, 4, 0x669900), //

		EARLY_AUTUMN(AUTUMN, 0x666633, 1, 0x669900, 2, 0x666633), //
		MID_AUTUMN(AUTUMN, 0xCCC000, 3, 0x991100, 1, 0x666600), //
		LATE_AUTUMN(AUTUMN, 0xCCC000, 2, 0xCCC099, 1, 0x663300), //

		EARLY_WINTER(WINTER, 0xCCCC99, 1, 0, 0, 0xCCCC99), //
		MID_WINTER(WINTER, 0xCCCC99, 1, 0, 0, 0xCCCC99), //
		LATE_WINTER(WINTER, 0x669966, 1, 0, 0, 0x669966);//

		public static final SeasonState[] VALUES = SeasonState.values();

		private Season season;
		private int foliageColor1;
		private int foliageColor2;
		private int grassColor;
		private int rand1;
		private int rand2;

		/**
		 * @param season
		 * 
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
		SeasonState(Season season, int foliageColor1, int rand1, int foliageColor2, int rand2, int grassColor) {
			this.season = season;
			this.foliageColor1 = foliageColor1;
			this.rand1 = rand1;
			this.foliageColor2 = foliageColor2;
			this.rand2 = rand2;
			this.grassColor = grassColor;
		}

		public Season getSeason() {
			return this.season;
		}

		@Override
		public int getFoliageColor() {
			int i = new Random().nextInt(this.rand1 + this.rand2);
			if (i < this.rand1)
				return this.foliageColor1;
			else
				return this.foliageColor2;
		}

		@Override
		public int getGrassColor() {
			return this.grassColor;
		}

	}
}
