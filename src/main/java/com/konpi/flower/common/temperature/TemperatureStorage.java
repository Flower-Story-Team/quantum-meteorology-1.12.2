package com.konpi.flower.common.temperature;

import java.util.Map;

import com.google.common.collect.Maps;
import com.konpi.flower.api.state.capability.ITemperature;
import com.konpi.flower.api.temperature.Temperature;
import com.konpi.flower.common.temperature.modifier.TemperatureModifier.ExternalModifier;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.util.Constants;

public class TemperatureStorage implements IStorage<ITemperature> {
	@Override
	public NBTBase writeNBT(Capability<ITemperature> capability, ITemperature instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();

		compound.setInteger("temperatureLevel", instance.getTemperature().getRawValue());
		compound.setInteger("temperatureTimer", instance.getChangeTime());

		NBTTagList externalModifierList = new NBTTagList();
		for (ExternalModifier modifier : instance.getExternalModifiers().values()) {
			externalModifierList.appendTag(modifier.serializeNBT());
		}
		compound.setTag("ExternalModifiers", externalModifierList);

		return compound;
	}

	@Override
	public void readNBT(Capability<ITemperature> capability, ITemperature instance, EnumFacing side, NBTBase nbt) {
		if (!(nbt instanceof NBTTagCompound))
			throw new IllegalArgumentException("Temperature must be read from an NBTTagCompound!");

		NBTTagCompound compound = (NBTTagCompound) nbt;

		if (compound.hasKey("temperatureLevel")) {
			instance.setTemperature(new Temperature(compound.getInteger("temperatureLevel")));
			instance.setChangeTime(compound.getInteger("temperatureTimer"));

			NBTTagList externalModifierTagList = compound.getTagList("ExternalModifiers", Constants.NBT.TAG_COMPOUND);
			Map<String, ExternalModifier> externalModifierList = Maps.newHashMap();
			for (int i = 0; i < externalModifierTagList.tagCount(); i++) {
				NBTTagCompound externalModifierCompound = externalModifierTagList.getCompoundTagAt(i);
				ExternalModifier modifier = new ExternalModifier();
				modifier.deserializeNBT(externalModifierCompound);
				externalModifierList.put(modifier.getName(), modifier);
			}
			instance.setExternalModifiers(externalModifierList);
		}
	}

}
