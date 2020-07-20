package com.konpi.quantummeteorology.common.thirst;

import com.konpi.quantummeteorology.api.state.capability.IThirst;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ThirstStorage implements IStorage<IThirst> {
	@Override
	public NBTBase writeNBT(Capability<IThirst> capability, IThirst instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();

		compound.setInteger("thirstLevel", instance.getThirst());
		compound.setInteger("thirstTimer", instance.getChangeTime());
		compound.setFloat("thirstHydrationLevel", instance.getHydration());
		compound.setFloat("thirstExhaustionLevel", instance.getExhaustion());

		return compound;
	}

	@Override
	public void readNBT(Capability<IThirst> capability, IThirst instance, EnumFacing side, NBTBase nbt) {
		if (!(nbt instanceof NBTTagCompound))
			throw new IllegalArgumentException("Thirst must be read from an NBTTagCompound!");

		NBTTagCompound compound = (NBTTagCompound) nbt;

		if (compound.hasKey("thirstLevel")) {
			instance.setThirst(compound.getInteger("thirstLevel"));
			instance.setHydration(compound.getInteger("thirstHydrationLevel"));
			instance.setExhaustion(compound.getInteger("thirstExhaustionLevel"));
			instance.setChangeTime(compound.getInteger("thirstTimer"));
		}

	}

}
