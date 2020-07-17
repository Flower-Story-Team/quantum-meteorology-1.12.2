package com.konpi.flower.common.fluids;

import com.konpi.flower.api.gas.Gas;
import com.konpi.flower.api.gas.GasRegistry;
import com.konpi.flower.api.gas.OreGas;
import net.minecraftforge.fluids.FluidRegistry;

import javax.annotation.Resource;
import java.util.Locale;


public class FluidsBase {
    //气体id 颜色 S代表id T代表tint颜色
    public static final Gas NaturalGas = new Gas("natural_gas",0xE6E6FA);



    public static void register(){
        //
        GasRegistry.register(NaturalGas).registerFluid("liquidnaturalgas");
        /*
        for (Resource resource : Resource.values()) {
            String name = resource.name();
            String nameLower = name.toLowerCase(Locale.ROOT);

            OreGas clean = new OreGas("clean" + name, "oregas." + nameLower, resource.tint);
        }

         */
        FluidRegistry.enableUniversalBucket();

        FluidRegistry.addBucketForFluid(NaturalGas.getFluid());
    }
}
