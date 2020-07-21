package com.konpi.quantummeteorology.common.init;

import com.konpi.quantummeteorology.api.capabilities.temperature.ITemperature;
import com.konpi.quantummeteorology.api.capabilities.temperature.TemperatureHandler;
import com.konpi.quantummeteorology.api.capabilities.thirst.IThirst;
import com.konpi.quantummeteorology.api.capabilities.thirst.ThirstHandler;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModStates {

	public static void init() {
		CapabilityManager.INSTANCE.register(IThirst.class, new Capability.IStorage<IThirst>() {

			public static final String capname = "flower.thirst";

			@Override
			public NBTBase writeNBT(Capability<IThirst> capability, IThirst instance, EnumFacing side) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger(capname, instance.getThirst());
				return nbt;
			}

			@Override
			public void readNBT(Capability<IThirst> capability, IThirst instance, EnumFacing side, NBTBase nbt) {
				if (!(nbt instanceof NBTTagCompound))
					throw new IllegalArgumentException("Thirst must be read from an NBTTagCompound!");
				NBTTagCompound compound = (NBTTagCompound) nbt;
				if (compound.hasKey(capname)) {
					instance.setThirst(compound.getInteger(capname));
				}
			}
		}, () -> {
			return new ThirstHandler();
		});

		CapabilityManager.INSTANCE.register(ITemperature.class, new Capability.IStorage<ITemperature>() {

			public static final String capname = "flower.temperature";

			@Override
			public NBTBase writeNBT(Capability<ITemperature> capability, ITemperature instance, EnumFacing side) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger(capname, instance.getTemperature());
				return nbt;
			}

			@Override
			public void readNBT(Capability<ITemperature> capability, ITemperature instance, EnumFacing side,
					NBTBase nbt) {
				if (!(nbt instanceof NBTTagCompound))
					throw new IllegalArgumentException("Thirst must be read from an NBTTagCompound!");
				NBTTagCompound compound = (NBTTagCompound) nbt;
				if (compound.hasKey(capname)) {
					instance.setTemperature(compound.getInteger(capname));
				}
			}

		}, () -> {
			return new TemperatureHandler();
		});
	}
}
