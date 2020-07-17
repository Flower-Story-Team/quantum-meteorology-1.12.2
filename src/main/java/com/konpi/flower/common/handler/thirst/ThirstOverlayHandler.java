package com.konpi.flower.common.handler.thirst;

import java.util.Random;

import com.konpi.flower.api.Capabilities;
import com.konpi.flower.common.thirst.ThirstHandler;
import com.konpi.flower.common.util.RenderUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class ThirstOverlayHandler {
	public static final ResourceLocation OVERLAY = new ResourceLocation("flower:textures/gui/overlay.png");

	private final Random random = new Random();
	private final Minecraft minecraft = Minecraft.getMinecraft();

	private int updateCounter;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == Phase.END && !minecraft.isGamePaused()) {
			updateCounter++;
		}
	}

	@SubscribeEvent
	public void onPreRenderOverlay(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == ElementType.AIR) {
			ScaledResolution resolution = event.getResolution();
			int width = resolution.getScaledWidth();
			int height = resolution.getScaledHeight();
			EntityPlayerSP player = Minecraft.getMinecraft().player;

			ThirstHandler thirstStats = (ThirstHandler) player.getCapability(Capabilities.THIRST, null);
			int thirstLevel = thirstStats.getThirst();
			float thirstHydrationLevel = thirstStats.getHydration();

			// When the update counter isn't incrementing, ensure the same numbers are
			// produced (freezes moving gui elements)
			random.setSeed((long) (updateCounter * 312871));

			if (minecraft.playerController.gameIsSurvivalOrAdventure()) {
				minecraft.getTextureManager().bindTexture(OVERLAY);
				drawThirst(width, height, thirstLevel, thirstHydrationLevel);
				GuiIngameForge.right_height += 10;
				minecraft.getTextureManager().bindTexture(Gui.ICONS);
			}
		}
	}

	private void drawThirst(int width, int height, int thirstLevel, float thirstHydrationLevel) {
		int left = width / 2 + 91;
		int top = height - GuiIngameForge.right_height;

		for (int i = 0; i < 10; i++) {
			int dropletHalf = i * 2 + 1;
			int iconIndex = 0;
			int backgroundOffset = 0;
			int startX = left - i * 8 - 9;
			int startY = top;

			// if (minecraft.player.isPotionActive(TANPotions.thirst))
			// {
			// iconIndex += 4;
			// backgroundOffset += 117;
			// }

			if (thirstHydrationLevel <= 0.0F && updateCounter % (thirstLevel * 3 + 1) == 0) {
				startY = top + (random.nextInt(3) - 1);
			}

			RenderUtils.drawTexturedModalRect(startX, startY, backgroundOffset, 16, 9, 9);

			if (thirstLevel > dropletHalf) {
				RenderUtils.drawTexturedModalRect(startX, startY, (iconIndex + 4) * 9, 16, 9, 9);
			}
			if (thirstLevel == dropletHalf) {
				RenderUtils.drawTexturedModalRect(startX, startY, (iconIndex + 5) * 9, 16, 9, 9);
			}
		}
	}
}
