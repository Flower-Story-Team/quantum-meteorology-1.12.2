package com.konpi.quantummeteorology.common.handler.season;

import com.konpi.quantummeteorology.common.init.ModFertility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockReed;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SeasonalCropGrawthHandler {
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onItemTooltipAdded(ItemTooltipEvent event) {
		ModFertility.setupTooltips(event);
	}

	@SubscribeEvent
	public void onCropGrowth(BlockEvent.CropGrowEvent event) {
		Block plant = event.getState().getBlock();
		boolean isFertile = ModFertility.isCropFertile(plant.getRegistryName().toString(), event.getWorld(),
				event.getPos());

		if (!isFertile) {
			if (!(plant instanceof BlockGrass) && !(plant instanceof BlockReed)) {
				event.getWorld().destroyBlock(event.getPos(), true);
			} else {
				event.setResult(Event.Result.DENY);
			}
		}
	}

	@SubscribeEvent
	public void onApplyBonemeal(BonemealEvent event) {
		Block plant = event.getBlock().getBlock();
		boolean isFertile = ModFertility.isCropFertile(plant.getRegistryName().toString(), event.getWorld(),
				event.getPos());

		if (!isFertile) {
			if (!(plant instanceof BlockGrass) && !(plant instanceof BlockReed)) {
				event.getWorld().destroyBlock(event.getPos(), true);
			}

			event.setCanceled(true);
		}
	}

}
