package com.konpi.quantummeteorology.client.handler;

import com.konpi.quantummeteorology.api.data.Month;
import com.konpi.quantummeteorology.common.config.CommonConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SeasonChangeHandler {

	private Month month = null;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		// Only do this when in the world
		if (Minecraft.getMinecraft().player == null || !CommonConfig.general_config.seasonal_color_change)
			return;
		int dimension = Minecraft.getMinecraft().player.dimension;
		if (event.phase == TickEvent.Phase.END && CommonConfig.isWhiteListDimension(dimension)) {
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
