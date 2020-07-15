package com.konpi.flower.seasons.Handler;

import com.konpi.flower.Flower;
import com.konpi.flower.proxy.network.ConfigMessage;
import com.konpi.flower.proxy.network.SeasonCycleMessage;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * according to sereneseason
 *
 */
public class PacketHandler {
	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(Flower.MODID);

	public static void init() {
		instance.registerMessage(SeasonCycleMessage.class, SeasonCycleMessage.class, 3, Side.CLIENT);
		instance.registerMessage(ConfigMessage.class, ConfigMessage.class, 4, Side.CLIENT);
	}
}
