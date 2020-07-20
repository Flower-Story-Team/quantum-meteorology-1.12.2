package com.konpi.quantummeteorology.common.init;

import com.konpi.quantummeteorology.api.Capabilities;
import com.konpi.quantummeteorology.api.state.capability.ITemperature;
import com.konpi.quantummeteorology.api.state.capability.IThirst;
import com.konpi.quantummeteorology.api.state.capability.PlayerStateRegistry;
import com.konpi.quantummeteorology.common.temperature.TemperatureHandler;
import com.konpi.quantummeteorology.common.temperature.TemperatureStorage;
import com.konpi.quantummeteorology.common.thirst.ThirstHandler;
import com.konpi.quantummeteorology.common.thirst.ThirstStorage;

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
