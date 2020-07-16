package com.konpi.flower.common.seasons.savedata;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

/**
 * according to sereneseason
 *
 */
public class SeasonSaveData extends WorldSavedData {
	public static final String DATA_IDENTIFIER = "seasons";

	public int seasonCycleTicks;

	public SeasonSaveData() {
		this(DATA_IDENTIFIER);
	}

	public SeasonSaveData(String identifier) {
		super(identifier);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.seasonCycleTicks = nbt.getInteger("SeasonCycleTicks");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("SeasonCycleTicks", this.seasonCycleTicks);

		return nbt;
	}
}
