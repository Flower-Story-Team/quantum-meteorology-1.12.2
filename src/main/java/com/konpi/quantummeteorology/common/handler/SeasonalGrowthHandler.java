package com.konpi.quantummeteorology.common.handler;

import com.konpi.quantummeteorology.api.data.PlantData;
import com.konpi.quantummeteorology.common.util.miscutil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockReed;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SeasonalGrowthHandler {
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onItemTooltipAdded(ItemTooltipEvent event) {
		PlantData.setupTooltips(event);
	}

	@SubscribeEvent
	public void onCropGrowth(BlockEvent.CropGrowEvent event) {
		Block block = event.getState().getBlock();
		World world = event.getWorld();
		int temperature = miscutil.GetTemperature(world, event.getPos());
		boolean canGrow = PlantData.canGrow(block.toString(), temperature, 0);

		if (!canGrow) {
			if (!(block instanceof BlockGrass) && !(block instanceof BlockReed)) {
				event.getWorld().destroyBlock(event.getPos(), true);
			} else {
				event.setResult(Event.Result.DENY);
			}
		}
	}

	@SubscribeEvent
	public void onApplyBonemeal(BonemealEvent event) {
		Block block = event.getBlock().getBlock();
		World world = event.getWorld();
		int temperature = miscutil.GetTemperature(world, event.getPos());
		boolean canGrow = PlantData.canGrow(block.toString(), temperature, 0);
		if (!canGrow) {
			event.setCanceled(true);
		}
	}

}
