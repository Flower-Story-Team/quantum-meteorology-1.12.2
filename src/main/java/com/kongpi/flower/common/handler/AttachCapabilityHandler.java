package com.kongpi.flower.common.handler;

import com.kongpi.flower.Flower;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AttachCapabilityHandler {
	@SubscribeEvent
	public void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getObject();
			ResourceLocation loc = new ResourceLocation(Flower.MODID, "temperature");
			if(!event.getCapabilities().containsKey(loc)) {
				event.addCapability(loc, new ICapabilityProvider() {

					@Override
					public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
						// TODO Auto-generated method stub
						return null;
					}
					
				});
			}
		}
	}

	@SubscribeEvent
	public void onAttachItemCapabilities(AttachCapabilitiesEvent<Item> event) {

	}

	@SubscribeEvent
	public void onAttachTileCapabilities(AttachCapabilitiesEvent<TileEntity> event) {

	}

}
