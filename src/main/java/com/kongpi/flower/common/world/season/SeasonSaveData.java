package com.kongpi.flower.common.world.season;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

public class SeasonSaveData extends WorldSavedData {

	public static final String DATA_IDENTIFIER = "season_data";

	public int SeasonTicks;

	public SeasonSaveData() {
		this(DATA_IDENTIFIER);
	}

	public SeasonSaveData(String name) {
		super(name);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.SeasonTicks = nbt.getInteger("SeasonTicks");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("SeasonTicks", this.SeasonTicks);
		return nbt;
	}
}
