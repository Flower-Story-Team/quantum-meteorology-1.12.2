package com.konpi.flower.api.state.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.konpi.flower.api.state.IPlayerState;
import com.konpi.flower.api.temperature.Temperature;
import com.konpi.flower.common.temperature.modifier.TemperatureModifier.ExternalModifier;

public interface ITemperature extends IPlayerState {

	public void setTemperature(Temperature temperature);

	public void addTemperature(Temperature difference);

	public void applyModifier(String name, int amount, int rate, int duration);

	public boolean hasModifier(String name);

	public int getPlayerTarget(EntityPlayer player);

	public Temperature getTemperature();

	public void setChangeTime(int ticks);

	public int getChangeTime();

	public ImmutableMap<String, ExternalModifier> getExternalModifiers();

	public void setExternalModifiers(Map<String, ExternalModifier> externalModifiers);
}
