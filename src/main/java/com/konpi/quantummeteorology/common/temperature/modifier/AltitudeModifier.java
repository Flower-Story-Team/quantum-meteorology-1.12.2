package com.konpi.quantummeteorology.common.temperature.modifier;

import com.konpi.quantummeteorology.api.temperature.IModifierMonitor;
import com.konpi.quantummeteorology.api.temperature.Temperature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class AltitudeModifier extends TemperatureModifier {
	public AltitudeModifier(String id) {
		super(id);
	}

	@Override
	public Temperature applyEnvironmentModifiers(World world, BlockPos pos, Temperature initialTemperature,
			IModifierMonitor monitor) {
		int temperatureLevel = initialTemperature.getRawValue();
		int newTemperatureLevel = temperatureLevel;
		// TODO
		// if (world.provider.isSurfaceWorld()) {
		// newTemperatureLevel -= MathHelper
		// .abs(MathHelper.floor(((64 - pos.getY()) / 64.0) *
		// ModConfig.temperature.altitudeModifier) + 1);
		// }

		monitor.addEntry(new IModifierMonitor.Context(this.getId(), "Altitude", initialTemperature,
				new Temperature(newTemperatureLevel)));

		return new Temperature(newTemperatureLevel);
	}

	@Override
	public boolean isPlayerSpecific() {
		return false;
	}
}
