package com.kongpi.flower.common.handler;

import com.kongpi.flower.Flower;
import com.kongpi.flower.api.Month;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SeasonHandler {

	private Month month = null;

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onClientTick(TickEvent.ClientTickEvent event) {
		// Only do this when in the world
		if (Minecraft.getMinecraft().player == null)
			return;
		int dimension = Minecraft.getMinecraft().player.dimension;
		if (event.phase == TickEvent.Phase.END && dimension == 0) {
			long t = Minecraft.getMinecraft().player.world.getWorldTime();
			if (this.month == null) {
				// 进世界初始
				month = Month.getmonth(t);
			} else if (!Month.getmonth(t).equals(month)) {
				// 更新渲染
				Minecraft.getMinecraft().renderGlobal.loadRenderers();
				month = Month.getmonth(t);
			}
		}
	}

}
