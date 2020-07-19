package com.kongpi.flower.api.capabilities;

import com.kongpi.flower.api.capabilities.thirst.IThirst;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class Capabilities {

	@CapabilityInject(IThirst.class)
	public static final Capability<IThirst> THIRST = null;

}
