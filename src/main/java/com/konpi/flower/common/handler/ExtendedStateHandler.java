package com.konpi.flower.common.handler;

import com.konpi.flower.Flower;
import com.konpi.flower.api.state.IPlayerState;
import com.konpi.flower.api.state.StateHandlerBase;
import com.konpi.flower.api.state.capability.PlayerStateRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ExtendedStateHandler {
	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getObject();

			for (String identifier : PlayerStateRegistry.getCapabilityMap().keySet()) {
				ResourceLocation loc = new ResourceLocation(Flower.MODID, identifier);

				// Each player should have their own instance for each stat, as associated
				// values may vary
				if (!event.getCapabilities().containsKey(loc))
					event.addCapability(loc, PlayerStateRegistry.createCapabilityProvider(identifier));
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		World world = player.world;

		if (!world.isRemote) {
			for (Capability capability : PlayerStateRegistry.getCapabilityMap().values()) {
				StateHandlerBase stat = (StateHandlerBase) player.getCapability(capability, null);

				capability.getStorage().readNBT(capability, stat, null,
						player.getEntityData().getCompoundTag(capability.getName()));
				stat.onSendClientUpdate();
				PacketHandler.instance.sendTo(stat.createUpdateMessage(), (EntityPlayerMP) player);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		World world = player.world;

		if (!world.isRemote) {
			for (Capability capability : PlayerStateRegistry.getCapabilityMap().values()) {
				IPlayerState stat = (IPlayerState) player.getCapability(capability, null);

				stat.update(player, world, event.phase);

				if (event.phase == Phase.START) {
					if (stat.hasChanged()) {
						player.getEntityData().setTag(capability.getName(),
								capability.getStorage().writeNBT(capability, stat, null));
						stat.onSendClientUpdate();
						PacketHandler.instance.sendTo(stat.createUpdateMessage(), (EntityPlayerMP) player);
					}
				}
			}
		}
	}
}
