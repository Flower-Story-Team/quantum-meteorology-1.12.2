package com.konpi.flower.common.network;

import com.konpi.flower.common.handler.season.SeasonHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class SeasonCycleMessage implements IMessage, IMessageHandler<SeasonCycleMessage, IMessage> {

	public int dimension;
	public int seasonCycleTicks;

	public SeasonCycleMessage() {
	}

	public SeasonCycleMessage(int dimension, int seasonCycleTicks) {
		this.dimension = dimension;
		this.seasonCycleTicks = seasonCycleTicks;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.dimension = buf.readInt();
		this.seasonCycleTicks = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.dimension);
		buf.writeInt(this.seasonCycleTicks);
	}

	@Override
	public IMessage onMessage(SeasonCycleMessage message, MessageContext ctx) {
		if (ctx.side == Side.CLIENT) {
			if (Minecraft.getMinecraft().player == null)
				return null;
			int playerDimension = Minecraft.getMinecraft().player.dimension;

			if (playerDimension == message.dimension)
				SeasonHandler.clientSeasonCycleTicks.replace(playerDimension, message.seasonCycleTicks);
		}

		return null;
	}
}
