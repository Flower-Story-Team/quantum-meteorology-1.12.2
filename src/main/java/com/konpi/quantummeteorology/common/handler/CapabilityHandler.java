package com.konpi.quantummeteorology.common.handler;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.api.capabilities.Capabilities;
import com.konpi.quantummeteorology.api.capabilities.CapabilityProvider;
import com.konpi.quantummeteorology.api.capabilities.Drawhandler;
import com.konpi.quantummeteorology.api.capabilities.thirst.IThirst;
import com.konpi.quantummeteorology.api.data.Drinks;
import com.konpi.quantummeteorology.api.data.IPlayerState;
import com.konpi.quantummeteorology.api.data.PlayerStatRegistry;
import com.konpi.quantummeteorology.common.util.ylllutil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

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
				PlayerStatRegistry.registerCapability(cap);
			}
			// temperature
			cap = Capabilities.TEMPERATURE;
			loc = new ResourceLocation(QuantumMeteorology.MODID, cap.getName());
			if (!event.getCapabilities().containsKey(loc)) {
				event.addCapability(loc, new CapabilityProvider(cap));
				PlayerStatRegistry.registerCapability(cap);
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
	public void onPlyaerRebirth(PlayerRespawnEvent event) {
		EntityPlayer player = event.player;
		World world = player.world;
		// Drawhandler.b = true;
		if (!world.isRemote) {
			// thirst
			player.getCapability(Capabilities.THIRST, null).setThirst(70);
			QuantumMeteorology.NetIns.sendTo(
					((IPlayerState) player.getCapability(Capabilities.THIRST, null)).createUpdateMessage(),
					(EntityPlayerMP) player);
			// temperature
			player.getCapability(Capabilities.TEMPERATURE, null).setTemperature(20);
			QuantumMeteorology.NetIns.sendTo(
					((IPlayerState) player.getCapability(Capabilities.TEMPERATURE, null)).createUpdateMessage(),
					(EntityPlayerMP) player);
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		World world = player.world;
		Drawhandler.b = true;
		if (!world.isRemote) {
			// thirst
			player.getCapability(Capabilities.THIRST, null).setThirst(70);
			QuantumMeteorology.NetIns.sendTo(
					((IPlayerState) player.getCapability(Capabilities.THIRST, null)).createUpdateMessage(),
					(EntityPlayerMP) player);
			// temperature
			player.getCapability(Capabilities.TEMPERATURE, null).setTemperature(20);
			QuantumMeteorology.NetIns.sendTo(
					((IPlayerState) player.getCapability(Capabilities.TEMPERATURE, null)).createUpdateMessage(),
					(EntityPlayerMP) player);
		}
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		World world = player.world;
		if (!world.isRemote) {
			for (Capability capability : PlayerStatRegistry.getCapabilityMap().values()) {
				IPlayerState stat = (IPlayerState) player.getCapability(capability, null);
				stat.update(player, world, event.phase);
				if (event.phase == Phase.START) {
					if (stat.hasChanged()) {
						player.getEntityData().setTag(capability.getName(),
								capability.getStorage().writeNBT(capability, stat, null));
						stat.onSendClientUpdate();
						QuantumMeteorology.NetIns.sendTo(stat.createUpdateMessage(), (EntityPlayerMP) player);
					}
				}
			}
			// if (world.getWorldTime() % 40 == 0)
			// ylllutil.info(world, player);
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

	@SubscribeEvent
	public void onPlayerDrink(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			ItemStack stack = event.getItem();
			IThirst t = player.getCapability(Capabilities.THIRST, null);
			if (t.getThirst() != 100) {
				// For some reason the stack size can be zero for water bottles, which breaks
				// everything.
				// As a workaround, we temporarily set it to 1
				boolean zeroStack = false;

				if (stack.getCount() <= 0) {
					stack.setCount(1);
					zeroStack = true;
				}

				if (stack.getItem().equals(Items.POTIONITEM)) {
					t.add(5);
				} else {
					Drinks d = Drinks.getDrink(stack.getItem().getRegistryName().toString());
					if (d != null) {
						t.add(d.getThirst());
					}
				}

				if (zeroStack)
					stack.setCount(0);
			}
		}
	}
}
