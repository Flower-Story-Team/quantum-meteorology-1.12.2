package com.konpi.quantummeteorology.api.capabilities;

import com.konpi.quantummeteorology.api.capabilities.temperature.ITemperature;
import com.konpi.quantummeteorology.api.capabilities.thirst.IThirst;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class Capabilities {

	@CapabilityInject(IThirst.class)
	public static final Capability<IThirst> THIRST = null;

	@CapabilityInject(ITemperature.class)
	public static final Capability<ITemperature> TEMPERATURE = null;

}
