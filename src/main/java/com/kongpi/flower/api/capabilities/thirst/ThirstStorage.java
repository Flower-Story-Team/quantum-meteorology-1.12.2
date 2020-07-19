package com.kongpi.flower.api.capabilities.thirst;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ThirstStorage implements IStorage<IThirst> {

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

}
