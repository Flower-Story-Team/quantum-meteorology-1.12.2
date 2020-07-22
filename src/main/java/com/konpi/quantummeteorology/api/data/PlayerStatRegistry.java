package com.konpi.quantummeteorology.api.data;

import java.util.HashMap;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.konpi.quantummeteorology.api.capabilities.CapabilityProvider;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class PlayerStatRegistry {

	private static HashMap<String, Capability<? extends IPlayerState>> statCapabilities = Maps.newHashMap();

	public static void registerCapability(Capability<? extends IPlayerState> capability) {
		statCapabilities.put(capability.getName(), capability);
	}

	public static Capability<?> getCapability(String identifier) {
		return statCapabilities.get(identifier);
	}

	public static ImmutableMap<String, Capability<? extends IPlayerState>> getCapabilityMap() {
		return ImmutableMap.copyOf(statCapabilities);
	}
}
