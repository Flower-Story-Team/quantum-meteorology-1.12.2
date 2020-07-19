package com.kongpi.flower.api;

import net.minecraft.util.DamageSource;

public class FlowerDamageSource {

	public static final DamageSource THIRSE = new DamageSource("flower.thirst").setDamageIsAbsolute();

	public static final DamageSource HEAT = new DamageSource("flwoer.heat").setDamageIsAbsolute();

	public static final DamageSource COLD = new DamageSource("flower.cold").setDamageIsAbsolute();
}
