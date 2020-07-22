package com.konpi.quantummeteorology.api.capabilities;

import com.konpi.quantummeteorology.api.capabilities.thirst.ThirstHandler;
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

public class Drawhandler {

	public static final ResourceLocation loc = new ResourceLocation("quantummeteorology:textures/gui/guis.png");

	private final Minecraft minecraft = Minecraft.getMinecraft();

	public static boolean b = true;

	@SubscribeEvent
	public void onPreRenderOverlay(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == ElementType.AIR) {
			ScaledResolution resolution = event.getResolution();
			int width = resolution.getScaledWidth();
			int height = resolution.getScaledHeight();
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			int temp = ylllutil.GetTemperature(player.world, player.getPosition());
			if (b) {
				// 这个是为了解决进游戏不显示的bug，在没有更好的解决办法之前最好不动它
				player.getCapability(Capabilities.THIRST, null).setThirst(70);
				b = false;
			}
			ThirstHandler th = (ThirstHandler) player.getCapability(Capabilities.THIRST, null);
			int thirst = th.getThirst();
			if (minecraft.playerController.gameIsSurvivalOrAdventure()) {
				minecraft.getTextureManager().bindTexture(loc);
				drawSuTemp(temp);
				drawPlayerThirst(width, height, thirst);
				GuiIngameForge.right_height += 10;
				minecraft.getTextureManager().bindTexture(Gui.ICONS);
			}
		}
	}

	private void drawSuTemp(int temp) {
		// 79是0°
		ylllutil.drawTexturedModalRect(3, 3, 0, 0, 8, 126, 0.6F);
		ylllutil.drawTexturedModalRect(5, 79 - temp, 8, 79 - temp, 10, 117, 0.6F);
	}

	private void drawPlayerThirst(int width, int height, int thirst) {
		for (int i = 0; i < 10; i++) {
			int half = i * 10 + 5;
			int x = width + 97 - i * 10;
			if (thirst > half) {
				ylllutil.drawTexturedModalRect(x, 2, 13, 0, 10, 16, 0.8F);
			} else if (thirst > half - 5) {
				ylllutil.drawTexturedModalRect(x, 2, 23, 0, 10, 16, 0.8F);
			} else {
				ylllutil.drawTexturedModalRect(x, 2, 33, 0, 10, 16, 0.8F);
			}
		}
	}
}
