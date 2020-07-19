package com.kongpi.flower.common.handler;

import com.kongpi.flower.api.PlantData;
import com.kongpi.flower.common.FlowerUtil;

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
		Block plant = event.getState().getBlock();
		World world = event.getWorld();
		int temperature = FlowerUtil.BiomeTemperature(world.getBiome(event.getPos()))
				+ FlowerUtil.DayTemperature(world.getWorldTime()) + FlowerUtil.HeightTemperature(event.getPos().getY());
		boolean canGrow = PlantData.canGrow(plant.getRegistryName().toString(), temperature, 0);

		if (!canGrow) {
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
		World world = event.getWorld();
		int temperature = FlowerUtil.BiomeTemperature(world.getBiome(event.getPos()))
				+ FlowerUtil.DayTemperature(world.getWorldTime()) + FlowerUtil.HeightTemperature(event.getPos().getY());
		boolean canGrow = PlantData.canGrow(plant.getRegistryName().toString(), temperature, 0);
		if (!canGrow) {
			event.setCanceled(true);
		}
	}

}
