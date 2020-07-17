package com.konpi.flower.common.init;

import com.konpi.flower.api.Capabilities;
import com.konpi.flower.api.state.capability.ITemperature;
import com.konpi.flower.api.state.capability.IThirst;
import com.konpi.flower.api.state.capability.PlayerStateRegistry;
import com.konpi.flower.common.temperature.TemperatureHandler;
import com.konpi.flower.common.temperature.TemperatureStorage;
import com.konpi.flower.common.thirst.ThirstHandler;
import com.konpi.flower.common.thirst.ThirstStorage;

public class ModStates {

	public static void init() {
		PlayerStateRegistry.addState(ITemperature.class, new TemperatureStorage(), TemperatureHandler.class);
		PlayerStateRegistry.addState(IThirst.class, new ThirstStorage(), ThirstHandler.class);

		// These MUST be registered after stats are added, as only then will ours
		// capabilities be non-null
		PlayerStateRegistry.registerCapability(Capabilities.TEMPERATURE);
		PlayerStateRegistry.registerCapability(Capabilities.THIRST);
	}
}
