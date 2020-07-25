package com.konpi.quantummeteorology.common.gui;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.inventory.QMContainer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class QMGuiContainer extends GuiContainer
{
	 public static final ResourceLocation TEXTURES = new ResourceLocation(QuantumMeteorology.MODID,"textures/gui/container/demo.png");
	 
	    public QMGuiContainer(Container inventorySlotsIn)
	    {
	        super(inventorySlotsIn);
	        this.xSize = 176;
	        this.ySize = 166;	//172
	    }
	    
	    @Override
	    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	    {
	        GlStateManager.color(1.0F, 1.0F, 1.0F);
	        int offSetX = (this.width-this.xSize)/2;
	        int offSetY = (this.height-this.ySize)/2;
	        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURES);
	        this.drawTexturedModalRect(offSetX, offSetY, 0, 0, this.xSize,this.ySize);
	        QMContainer container = (QMContainer)this.inventorySlots;
	        int cookTime = container.cookTime;
	        if(cookTime>0)
	        {
	            int width = 34-(int)((float)cookTime/ QMContainer.MAX_TIME*34);
	            
	            width = width==0?1:width;
	            
	            this.drawTexturedModalRect(offSetX+71,offSetY+32,0,166,width,12);	// this.drawTexturedModalRect(offSetX+71,offSetY+32,0,172,width,12);
	        }
	    }
	    
	    @Override
	    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	    {
	        String tile =  I18n.format("gui.desc.demo");
	        this.fontRenderer.drawString(tile,(this.xSize-this.fontRenderer.getStringWidth(tile))/2,8,0x404040);
	    }
	    
	    @Override
	    public void drawScreen(int mouseX, int mouseY, float partialTicks)
	    {
	        this.drawDefaultBackground();
	        super.drawScreen(mouseX, mouseY, partialTicks);
	        this.renderHoveredToolTip(mouseX, mouseY);
	    }
	
}
