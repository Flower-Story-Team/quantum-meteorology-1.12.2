package com.konpi.quantummeteorology.api.capabilities.temperature;

import com.konpi.quantummeteorology.common.util.ylllutil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DrawSuTemHandler {

	public static final ResourceLocation loc = new ResourceLocation("quantummeteorology:textures/gui/sutem.png");

	private final Minecraft minecraft = Minecraft.getMinecraft();

	@SubscribeEvent
	public void onPreRenderOverlay(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == ElementType.AIR) {
			ScaledResolution resolution = event.getResolution();
			int width = resolution.getScaledWidth();
			int height = resolution.getScaledHeight();
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			int temp = ylllutil.GetTemperature(player.world, player.getPosition());
			if (minecraft.playerController.gameIsSurvivalOrAdventure()) {
				minecraft.getTextureManager().bindTexture(loc);
				drawSuTemp(width, height, temp);
				GuiIngameForge.right_height += 10;
				minecraft.getTextureManager().bindTexture(Gui.ICONS);
			}
		}
	}

	private void drawSuTemp(int width, int height, int temp) {
		// 79是0°
		ylllutil.drawTexturedModalRect(3, 3, 0, 0, 8, 126, 0.6F);
		ylllutil.drawTexturedModalRect(16, 79 - temp, 8, 79 - temp, 10, 117, 0.6F);
	}
}
