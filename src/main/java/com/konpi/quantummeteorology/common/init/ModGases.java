package com.konpi.quantummeteorology.common.init;

import java.util.Locale;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.api.gas.Gas;
import com.konpi.quantummeteorology.api.gas.GasRegistry;
import com.konpi.quantummeteorology.api.gas.OreGas;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Resource;

@Mod.EventBusSubscriber
public class ModGases {

    public static final Gas NaturalGas = new Gas("NaturalGas", 0xB0C4DE); //
    public static final Gas Oxygen = new Gas("oxygen", 0x6CE2FF);
/*
    public static final Fluid HeavyWater = new Fluid("heavywater", new ResourceLocation(QuantumMeteorology.MODID, "blocks/liquid/LiquidHeavyWater"),
            new ResourceLocation(QuantumMeteorology.MODID, "blocks/liquid/LiquidHeavyWater"));
*/

    //Internal gases
    public static final Gas LiquidOsmium = new Gas("liquidosmium", 0x52bdca);

//实例化

    public static void register() {
        GasRegistry.register(NaturalGas).registerFluid("naturalgas"); //

        GasRegistry.register(LiquidOsmium).setVisible(false);
/*
        FluidRegistry.registerFluid(HeavyWater);
*/
/*
        for (Resource resource : Resource.values()) {
            String name = resource.getName();
            String nameLower = name.toLowerCase(Locale.ROOT);
            //Clean
            OreGas clean = new OreGas("clean" + name, "oregas." + nameLower, resource.tint);
            GasRegistry.register(clean);
            //Dirty
            GasRegistry.register(new OreGas(nameLower, "oregas." + nameLower, resource.tint, clean));
        }
*/
//        FluidRegistry.enableUniversalBucket();
/*
        FluidRegistry.addBucketForFluid(HeavyWater);
*/
    }
}