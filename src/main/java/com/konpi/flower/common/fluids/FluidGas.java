package com.konpi.flower.common.fluids;

import com.konpi.flower.api.gas.Gas;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Random;

public class FluidGas extends Gas {


    public FluidGas(String s, ResourceLocation icon) {
        super("gas", "icon");
    }

    @Override
    public Gas setVisible(boolean v) {
        return super.setVisible(true);
    }

    @Override
    public Gas registerFluid(String name) {
        return super.registerFluid("gas");
    }

    public FluidGas(String s, int t) {
        super("gas", 0xa);
    }

    @Override
    public Gas setTranslationKey(String s) {
        return super.setTranslationKey("gas");
    }
}

