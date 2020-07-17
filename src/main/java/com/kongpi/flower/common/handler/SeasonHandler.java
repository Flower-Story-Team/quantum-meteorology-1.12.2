package com.kongpi.flower.common.handler;

import com.kongpi.flower.Flower;
import com.kongpi.flower.api.Itime.Month;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class SeasonHandler {

	private Month month = null;
	private boolean refresh = false;

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		World world = event.world;
		if (event.phase == TickEvent.Phase.END && !world.isRemote) {
			long t = world.getWorldTime();
			if (this.month == null) {
				// 进世界初始
				month = Month.getmonth(t);
			} else if (!Month.getmonth(t).equals(month)) {
				// 标记月份变化
				this.refresh = true;
				month = Month.getmonth(t);
			}
		}
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		// Only do this when in the world
		if (Minecraft.getMinecraft().player == null)
			return;
		int dimension = Minecraft.getMinecraft().player.dimension;
		if (event.phase == TickEvent.Phase.END && this.refresh && dimension == 0) {
			// 更新渲染
			Minecraft.getMinecraft().renderGlobal.loadRenderers();
			this.refresh = false;
		}
	}

}
