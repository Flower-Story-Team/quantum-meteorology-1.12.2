package com.konpi.flower.api.temperature;

import net.minecraft.util.math.BlockPos;

public interface ITemperatureRegulator {

	public Temperature getRegulatedTemperature();

	public boolean isPosRegulated(BlockPos pos);
}
