package com.konpi.quantummeteorology.common.handler;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.api.capabilities.Capabilities;
import com.konpi.quantummeteorology.api.capabilities.CapabilityProvider;
import com.konpi.quantummeteorology.api.data.IPlayerState;
import com.konpi.quantummeteorology.common.util.ylllutil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class CapabilityHandler {
	@SubscribeEvent
	public void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getObject();
			// thirst
			Capability cap = Capabilities.THIRST;
			ResourceLocation loc = new ResourceLocation(QuantumMeteorology.MODID, cap.getName());
			if (!event.getCapabilities().containsKey(loc)) {
				event.addCapability(loc, new CapabilityProvider(cap));
			}
			// temperature
			cap = Capabilities.TEMPERATURE;
			loc = new ResourceLocation(QuantumMeteorology.MODID, cap.getName());
			if (!event.getCapabilities().containsKey(loc)) {
				event.addCapability(loc, new CapabilityProvider(cap));
			}
		}
	}

	@SubscribeEvent
	public void onAttachItemCapabilities(AttachCapabilitiesEvent<Item> event) {

	}

	@SubscribeEvent
	public void onAttachTileCapabilities(AttachCapabilitiesEvent<TileEntity> event) {

	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		World world = player.world;
		if (!world.isRemote) {
			// thirst
			Capability cap1 = Capabilities.THIRST;
			IPlayerState state = (IPlayerState) player.getCapability(cap1, null);
			cap1.getStorage().readNBT(cap1, state, null, player.getEntityData().getCompoundTag(cap1.getName()));
			state.onSendClientUpdate();
			// temperature
			Capability cap2 = Capabilities.TEMPERATURE;
			state = (IPlayerState) player.getCapability(cap2, null);
			cap2.getStorage().readNBT(cap2, state, null, player.getEntityData().getCompoundTag(cap2.getName()));
			state.onSendClientUpdate();
		}
	}

	@SubscribeEvent
	public void onPlayerRebirth(PlayerRespawnEvent event) {
		EntityPlayer player = event.player;
		World world = player.world;
		if (!world.isRemote) {
			// thirst
			Capability cap = Capabilities.THIRST;
			IPlayerState state = (IPlayerState) player.getCapability(cap, null);
			player.getCapability(Capabilities.THIRST, null).setThirst(70);
			state.onSendClientUpdate();
			// temperature
			cap = Capabilities.TEMPERATURE;
			state = (IPlayerState) player.getCapability(cap, null);
			player.getCapability(Capabilities.TEMPERATURE, null).setTemperature(20);
			state.onSendClientUpdate();
		}
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		World world = player.world;

		if (!world.isRemote) {
			// thirst
			Capability cap = Capabilities.THIRST;
			IPlayerState state = (IPlayerState) player.getCapability(cap, null);
			state.update(player, world, event.phase);
			if (event.phase == Phase.START) {
				if (state.hasChanged()) {
					player.getEntityData().setTag(cap.getName(), cap.getStorage().writeNBT(cap, state, null));
					state.onSendClientUpdate();
				}
			}
			// temperature
			cap = Capabilities.TEMPERATURE;
			state = (IPlayerState) player.getCapability(cap, null);
			state.update(player, world, event.phase);
			if (event.phase == Phase.START) {
				if (state.hasChanged()) {
					player.getEntityData().setTag(cap.getName(), cap.getStorage().writeNBT(cap, state, null));
					state.onSendClientUpdate();
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event) {
		World world = event.getWorld();
		EntityPlayer player = event.getPlayer();
		BlockPos pos = event.getPos();
		IBlockState blockstate = event.getState();
		if (!world.isRemote && !player.isCreative()) {
			boolean canHarvestBlock = blockstate.getBlock().canHarvestBlock(world, pos, player);
			if (canHarvestBlock) {
				// thirst
				Capability cap = Capabilities.THIRST;
				IPlayerState state = (IPlayerState) player.getCapability(cap, null);
				state.BreakBlock(player, world);
				// temperature
				cap = Capabilities.TEMPERATURE;
				state = (IPlayerState) player.getCapability(cap, null);
				state.BreakBlock(player, world);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerJump(LivingJumpEvent event) {
		World world = event.getEntity().world;
		if (!world.isRemote && event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if (!player.isCreative()) {
				// thirst
				Capability cap = Capabilities.THIRST;
				IPlayerState state = (IPlayerState) player.getCapability(cap, null);
				state.onjump();
				// temperature
				cap = Capabilities.TEMPERATURE;
				state = (IPlayerState) player.getCapability(cap, null);
				state.onjump();
			}
		}
	}
}
