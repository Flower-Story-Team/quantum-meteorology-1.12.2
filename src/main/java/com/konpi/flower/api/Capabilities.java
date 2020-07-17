package com.konpi.flower.api;

import com.konpi.flower.api.state.capability.ITemperature;
import com.konpi.flower.api.state.capability.IThirst;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class Capabilities {

	@CapabilityInject(ITemperature.class)
	public static final Capability<ITemperature> TEMPERATURE = null;
	@CapabilityInject(IThirst.class)
	public static final Capability<IThirst> THIRST = null;
}
