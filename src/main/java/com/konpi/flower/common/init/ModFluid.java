package com.konpi.flower.common.init;

import com.konpi.flower.Flower;
import com.konpi.flower.common.fluid.FluidBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class ModFluid {

    public static final ArrayList<Fluid> fluidList = new ArrayList<>();

    static
    {
        // 允许万能桶
        FluidRegistry.enableUniversalBucket();
        fluidList.add(FluidBase.singleTexture("sludge"));
    }

    @SubscribeEvent
    public static void registerFluids(RegistryEvent.Register<Block> event)
    {
        Flower.logger.info("registering fluids");
        fluidList.forEach(fluid ->
        {
            FluidRegistry.registerFluid(fluid);
            FluidRegistry.addBucketForFluid(fluid);
        });

        Flower.logger.info("registering fluid blocks");
        ModFluid.fluidList.forEach(fluid ->
        {
            Block blockFluid = fluid.getBlock();
            if (blockFluid == null) {
                // 默认的流体方块
                blockFluid = new BlockFluidClassic(fluid, Material.WATER).setRegistryName(Flower.MODID, fluid.getName());
            }
            event.getRegistry().register(blockFluid);
        });
    }
}
