package com.konpi.flower.common.block.tileenty;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class BlockTankFluid extends TileEntity {

    // 我们直接复用 Forge 自带的 FluidTank 实现，它可以满足 90% 的需求。
    private IFluidHandler tank = new FluidTank(10000);

    public boolean hasCapability(Capability<?> capability, EnumFacing direction) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
                || super.hasCapability(capability, direction);
    }

    public <T> T getCapability(Capability<T> capability, EnumFacing direction) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.tank);
        } else {
            return super.getCapability(capability, direction);
        }
    }
}