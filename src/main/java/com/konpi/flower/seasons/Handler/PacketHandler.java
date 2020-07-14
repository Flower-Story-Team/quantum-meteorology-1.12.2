package com.konpi.flower.seasons.Handler;

import com.konpi.flower.Flower;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * according to sereneseason
 *
 */
public class PacketHandler {
	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(Flower.MODID);

	//本来这里还有一个message的东西，以后再加吧
}
