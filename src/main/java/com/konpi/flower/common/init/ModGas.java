package com.konpi.flower.common.init;

import com.konpi.flower.common.fluids.ModGasBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModGas {
    public static final List<Fluid> Gases =new ArrayList<Fluid>();

    public static final Fluid GAS_NATURAL =new ModGasBase("natural_gas",new ResourceLocation("flower:blocks/fluids/" + "gas_natural" + "_still"),new ResourceLocation("flower:blocks/fluids/" + "gas_natural" + "_flow"),0);



    public static void registerFluids(Fluid fluid) {
        registerFluid(fluid);
    }

    public static void registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);

    }


}
