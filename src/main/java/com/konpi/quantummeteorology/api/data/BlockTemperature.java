package com.konpi.quantummeteorology.api.data;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public enum BlockTemperature {
	FIRE(Blocks.FIRE.getDefaultState(), 3, 3);

	private String state;
	private int range;
	private int temperatureperblock;

	private BlockTemperature(IBlockState state, int range, int temperatureperblock) {
		this.state = state.toString();
		this.range = range;
		this.temperatureperblock = temperatureperblock;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getRange() {
		return range;
	}

	public int getTemperatureperblock() {
		return temperatureperblock;
	}
/*
* @return范围
*/
	public void setRange(int range) {
		this.range = range;
	}

	public void setTemperatureperblock(int temperatureperblock) {
		this.temperatureperblock = temperatureperblock;
	}
}
