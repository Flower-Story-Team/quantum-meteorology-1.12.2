package com.konpi.flower.common.network;

import com.konpi.flower.api.state.IPlayerState;
import com.konpi.flower.api.state.StateHandlerBase;
import com.konpi.flower.api.state.capability.PlayerStateRegistry;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateStateMessage implements IMessage, IMessageHandler<UpdateStateMessage, IMessage> {
	public String identifier;
	public NBTTagCompound data;

	public UpdateStateMessage() {
	}

	public UpdateStateMessage(Capability<?> capability, NBTTagCompound data) {
		if (data == null)
			throw new IllegalArgumentException("Data cannot be null!");

		this.identifier = capability.getName();
		this.data = data;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.identifier = ByteBufUtils.readUTF8String(buf);
		this.data = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, this.identifier);
		ByteBufUtils.writeTag(buf, this.data);
	}

	@Override
	public IMessage onMessage(UpdateStateMessage message, MessageContext ctx) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;

		if (player != null) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				Capability<IPlayerState> capability = (Capability<IPlayerState>) PlayerStateRegistry
						.getCapability(message.identifier);
				StateHandlerBase stat = (StateHandlerBase) player.getCapability(capability, null);

				capability.getStorage().readNBT(capability, stat, null, message.data);
			});
		}

		return null;
	}
}
