package com.konpi.quantummeteorology.common.fluid;

import com.konpi.quantummeteorology.QuantumMeteorology;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nullable;
import java.awt.*;

/**
 * 流体的基础类
 */
public class FluidBase extends Fluid {

    /**
     * 多材质流体
     */
    public FluidBase(String name) {
        this(name, Color.WHITE);
    }

    /**
     * 多材质流体
     */
    public FluidBase(String name, Color color) {
        this(name, false, color);
    }

    /**
     * 多材质流体
     */
    public FluidBase(String name, boolean hasOverlay, Color color) {
        this(name, new ResourceLocation(QuantumMeteorology.MODID, "fluid/" + name + "_still"),
                new ResourceLocation(QuantumMeteorology.MODID, "fluid/" + name + "_flow"),
                hasOverlay ? new ResourceLocation(QuantumMeteorology.MODID, "fluid/" + name + "_overlay") : null, color);
    }

    public FluidBase(String fluidName, ResourceLocation still,
                       ResourceLocation flowing, @Nullable ResourceLocation overlay, Color color) {
        super(fluidName, still, flowing, overlay, color);
        this.setUnlocalizedName(QuantumMeteorology.MODID + "." + fluidName + ".name");
    }

    /**
     * 单材质流体
     */
    public static FluidBase singleTexture(String name) {
        return singleTexture(name, Color.WHITE);
    }

    /**
     * 单材质流体
     */
    public static FluidBase singleTexture(String name, Color color) {
        final ResourceLocation theOneTex = new ResourceLocation(QuantumMeteorology.MODID, "fluid/" + name);
        return new FluidBase(name, theOneTex, theOneTex, null, color);
    }

}
