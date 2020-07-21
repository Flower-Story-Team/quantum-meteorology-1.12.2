package com.konpi.quantummeteorology.common.handler;

import com.konpi.quantummeteorology.api.data.PlantData;
import com.konpi.quantummeteorology.common.util.ylllutil;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockReed;
import net.minecraft.block.state.IBlockState;
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
		IBlockState state = event.getState();
		World world = event.getWorld();
		int temperature = ylllutil.BiomeTemperature(world.getBiome(event.getPos()))
				+ ylllutil.DayTemperature(world.getWorldTime()) + ylllutil.HeightTemperature(event.getPos().getY());
		boolean canGrow = PlantData.canGrow(state.toString(), temperature, 0);

		if (!canGrow) {
			if (!(state.getBlock() instanceof BlockGrass) && !(state.getBlock() instanceof BlockReed)) {
				event.getWorld().destroyBlock(event.getPos(), true);
			} else {
				event.setResult(Event.Result.DENY);
			}
		}
	}

	@SubscribeEvent
	public void onApplyBonemeal(BonemealEvent event) {
		IBlockState state = event.getBlock();
		World world = event.getWorld();
		int temperature = ylllutil.BiomeTemperature(world.getBiome(event.getPos()))
				+ ylllutil.DayTemperature(world.getWorldTime()) + ylllutil.HeightTemperature(event.getPos().getY());
		boolean canGrow = PlantData.canGrow(state.toString(), temperature, 0);
		if (!canGrow) {
			event.setCanceled(true);
		}
	}

}
