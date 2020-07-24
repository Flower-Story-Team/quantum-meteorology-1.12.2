package com.konpi.quantummeteorology.common.init;

import com.konpi.quantummeteorology.api.capabilities.energy.IWork;
import com.konpi.quantummeteorology.api.capabilities.energy.Worker;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class ModCapabilities {

    @CapabilityInject(IWork.class)
    public static final Capability<IWork> CABILITY_WORKER = null;

    public static void registerCapabilities(){
        CapabilityManager.INSTANCE.register(IWork.class, new CapabilityWorker(), Worker.class);

    }
    public static class CapabilityWorker implements Capability.IStorage<IWork> {


        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IWork> capability, IWork iWork, EnumFacing enumFacing) {
            return null;
        }

        @Override
        public void readNBT(Capability<IWork> capability, IWork iWork, EnumFacing enumFacing, NBTBase nbtBase) {

        }
    }
}
