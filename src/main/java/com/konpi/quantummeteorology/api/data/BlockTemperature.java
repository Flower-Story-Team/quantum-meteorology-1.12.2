package com.konpi.quantummeteorology.api.data;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum BlockTemperature {
	FIRE(Blocks.FIRE, 3, 3), //
	WATER(Blocks.WATER, 1, -1),//
	LAVA(Blocks.LAVA, 5,5),
	FURNACE(Blocks.LIT_FURNACE, 3, 2), //
	ICE(Blocks.ICE, 3, -2);

	private String name;
	private int range;
	private int temperatureperblock;

	private BlockTemperature(Block block, int range, int temperatureperblock) {
		this.name = block.toString();
		this.range = range;
		this.temperatureperblock = temperatureperblock;
	}

	public String getname() {
		return name;
	}

	// 获取范围
	public int getRange() {
		return range;
	}

	// 获取方块的温度
	public int getTemperatureperblock() {
		return temperatureperblock;
	}

	/*
	 * 设置范围
	 */
	public void setRange(int range) {
		this.range = range;
	}

	// 设置方块温度
	public void setTemperatureperblock(int temperatureperblock) {
		this.temperatureperblock = temperatureperblock;
	}
}
