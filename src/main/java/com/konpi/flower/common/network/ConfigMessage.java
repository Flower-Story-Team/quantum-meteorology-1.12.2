package com.konpi.flower.common.network;

import com.konpi.flower.Flower;
import com.konpi.flower.api.config.SyncedConfig;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ConfigMessage implements IMessage, IMessageHandler<ConfigMessage, IMessage> {

	public NBTTagCompound nbtOptions;

	public ConfigMessage() {
	}

	public ConfigMessage(NBTTagCompound nbtOptions) {
		this.nbtOptions = nbtOptions;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.nbtOptions = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbtOptions);
	}

	@Override
	public IMessage onMessage(ConfigMessage message, MessageContext ctx) {
		if (ctx.side == Side.CLIENT) {
			for (String key : message.nbtOptions.getKeySet()) {
				SyncedConfig.SyncedConfigEntry entry = SyncedConfig.optionsToSync.get(key);

				if (entry == null)
					Flower.logger.error("Option " + key + " does not exist locally!");

				entry.value = message.nbtOptions.getString(key);
				Flower.logger.info("Flower configuration synchronized with the server");
			}
		}

		return null;
	}
}
