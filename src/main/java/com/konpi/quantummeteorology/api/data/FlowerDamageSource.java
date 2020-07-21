package com.konpi.quantummeteorology.api.data;

import net.minecraft.util.DamageSource;

public class FlowerDamageSource {

	public static final DamageSource THIRSE = new DamageSource("quantummeteorology.thirst").setDamageIsAbsolute();

	public static final DamageSource HEAT = new DamageSource("quantummeteorology.heat").setDamageIsAbsolute();

	public static final DamageSource COLD = new DamageSource("quantummeteorology.cold").setDamageIsAbsolute();
}
