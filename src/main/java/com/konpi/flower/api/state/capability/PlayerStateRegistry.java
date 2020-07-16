package com.konpi.flower.api.state.capability;

import java.util.HashMap;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.konpi.flower.api.state.IPlayerState;
import com.konpi.flower.api.state.StateHandlerBase;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class PlayerStateRegistry {

	private static HashMap<String, Class<? extends StateHandlerBase>> playerStats = Maps.newHashMap();
	private static HashMap<String, Capability<? extends IPlayerState>> statCapabilities = Maps.newHashMap();

	public static <T> void addState(Class<T> capabilityClass, IStorage<T> storage,
			Class<? extends StateHandlerBase> implementationClass) {
		String identifier = capabilityClass.getName().intern();

		if (identifier == null) {
			throw new RuntimeException("Stat identifier cannot be null!");
		} else if (playerStats.containsKey(identifier)) {
			throw new RuntimeException("Stat with identifier " + identifier + " already exists!");
		}

		try {
			CapabilityManager.INSTANCE.register(capabilityClass, storage, (Class<? extends T>) implementationClass);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Player stat must implement capability class!");
		}

		playerStats.put(identifier, implementationClass);
	}

	public static void registerCapability(Capability<? extends IPlayerState> capability) {
		statCapabilities.put(capability.getName(), capability);
	}

	public static CapabilityProvider<?> createCapabilityProvider(String identifier) {
		return new CapabilityProvider(statCapabilities.get(identifier));
	}

	public static Capability<?> getCapability(String identifier) {
		return statCapabilities.get(identifier);
	}

	public static ImmutableMap<String, Capability<? extends IPlayerState>> getCapabilityMap() {
		return ImmutableMap.copyOf(statCapabilities);
	}
}
