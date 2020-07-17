package com.konpi.flower.common.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nullable;

public class ModGasBase extends Fluid {

    public ModGasBase(String fluidName, ResourceLocation still, ResourceLocation flowing, int temperature) {
        super(fluidName, still, flowing);
        this.setTemperature(temperature);
        this.setDensity(-1000);
        this.setLuminosity(0);
        this.setViscosity(4800);
    }

}
