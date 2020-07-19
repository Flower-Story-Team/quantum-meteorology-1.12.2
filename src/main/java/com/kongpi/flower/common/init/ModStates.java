package com.kongpi.flower.common.init;

import com.kongpi.flower.Flower;
import com.kongpi.flower.api.capabilities.Capabilities;
import com.kongpi.flower.api.capabilities.thirst.IThirst;
import com.kongpi.flower.api.capabilities.thirst.ThirstHandler;
import com.kongpi.flower.api.capabilities.thirst.ThirstStorage;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModStates {

	public static void init() {
		CapabilityManager.INSTANCE.register(IThirst.class, new ThirstStorage(), () -> {
			return new ThirstHandler();
		});

	}
}
