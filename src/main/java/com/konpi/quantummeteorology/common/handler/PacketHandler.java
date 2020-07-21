package com.konpi.quantummeteorology.common.handler;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.network.ConfigMessage;
import com.konpi.quantummeteorology.common.network.MessageDrinkWaterInWorld;
import com.konpi.quantummeteorology.common.network.MessageTemperatureClient;
import com.konpi.quantummeteorology.common.network.MessageToggleUI;
import com.konpi.quantummeteorology.common.network.SeasonCycleMessage;
import com.konpi.quantummeteorology.common.network.UpdateStateMessage;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(QuantumMeteorology.MODID);

	public static void init() {
		instance.registerMessage(SeasonCycleMessage.class, SeasonCycleMessage.class, 3, Side.CLIENT);
		instance.registerMessage(ConfigMessage.class, ConfigMessage.class, 4, Side.CLIENT);
		instance.registerMessage(UpdateStateMessage.class, UpdateStateMessage.class, 0, Side.CLIENT);
		instance.registerMessage(MessageTemperatureClient.class, MessageTemperatureClient.class, 1, Side.CLIENT);
		instance.registerMessage(MessageToggleUI.class, MessageToggleUI.class, 2, Side.CLIENT);
		instance.registerMessage(MessageDrinkWaterInWorld.class, MessageDrinkWaterInWorld.class, 5, Side.SERVER);

	}
}
