package com.konpi.quantummeteorology.api.capabilities;

import org.lwjgl.opengl.GL11;

import com.konpi.quantummeteorology.api.capabilities.thirst.ThirstHandler;
import com.konpi.quantummeteorology.common.util.miscutil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Drawhandler extends Gui
{

	public static final ResourceLocation loc = new ResourceLocation("quantummeteorology:textures/gui/guis.png");

	private final Minecraft minecraft = Minecraft.getMinecraft();

	public static boolean b = true;

	@SubscribeEvent
	public void onPreRenderOverlay(RenderGameOverlayEvent.Pre event)
	{
		if (event.getType() == ElementType.AIR)
		{
			ScaledResolution scaledresolution = new ScaledResolution(this.minecraft);
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			
			int temp = miscutil.GetTemperature(player.world, player.getPosition());
			
			if (b)
			{
				player.getCapability(Capabilities.THIRST, null).setThirst(70);
				b = false;
			}

			if (minecraft.playerController.gameIsSurvivalOrAdventure())
			{
				minecraft.getTextureManager().bindTexture(loc);
				drawSuTemp(temp);
				drawPlayerThirst(scaledresolution);
				GuiIngameForge.right_height += 10;
				minecraft.getTextureManager().bindTexture(Gui.ICONS);
			}
		}
	}
	
	
	
	private void drawSuTemp(int temp)
	{
		drawTexturedModalRect(3, 3, 0, 0, 7, 126);
		drawTexturedModalRect(3, 3 + 79 - temp, 7, 79 - temp, 7, 126);
	}

	private void drawPlayerThirst(ScaledResolution scaledRes)
	{
		int y0 = scaledRes.getScaledHeight() - 39 - 10;
		int x0 = scaledRes.getScaledWidth() / 2 + 91;

		EntityPlayerSP player = Minecraft.getMinecraft().player;
		ThirstHandler th = (ThirstHandler) player.getCapability(Capabilities.THIRST, null);
		int thirst = th.getThirst();

		// ----------------------------------------------------------------
		EntityPlayer entityplayer = (EntityPlayer) this.minecraft.getRenderViewEntity();
		Entity entity = entityplayer.getRidingEntity();
		// ----------------------------------------------------------------

		if (entity == null || !(entity instanceof EntityLivingBase)) {
			// this.minecraft.profiler.endStartSection("amods");
			for (int i6 = 0; i6 < 10; i6++) {
				// int half = i * 10 + 5;
				// int x = width + 97 - i * 10;

				int half = i6 * 10 + 5;

				int y1 = y0;
				int x1 = x0 - i6 * 8 - 9;

				if (thirst > half) {
					this.drawTexturedModalRect(x1, y1, 14, 0, 9, 9);
				} else if (thirst > half - 5) {
					this.drawTexturedModalRect(x1, y1, 23, 0, 9, 9);
				} else {
					this.drawTexturedModalRect(x1, y1, 32, 0, 9, 9);
				}
			}
		}
	}
}
