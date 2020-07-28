package com.konpi.quantummeteorology.api.data;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.util.miscutil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum PlantData {
	SAPLING(Blocks.SAPLING.getRegistryName().toString(), -10, 70);//

	private PlantData(String name, float temperature_min, float temperature_max) {
		this(name, temperature_min, (temperature_min + temperature_max) / 2, temperature_max);
	}

	private PlantData(String name, float temperature_min, float temperature_proference, float temperature_max) {

		if (temperature_min > temperature_proference || temperature_proference > temperature_max
				|| temperature_min > temperature_max || temperature_min < -30 || temperature_max > 70) {
			QuantumMeteorology.logger.error("temeprature setting error: " + this.toString());
			this.temperature_min = 20;
			this.temperature_max = 20;
			this.temperature_proference = 20;
		} else {
			this.temperature_min = temperature_min;
			this.temperature_proference = temperature_proference;
			this.temperature_max = temperature_max;
		}
		this.plant_name = name;
	}

	private float temperature_min;
	private float temperature_proference;
	private float temperature_max;
	private String plant_name;

	public static boolean canGrow(String name, float temp, float humidity) {
		for (PlantData data : PlantData.values()) {
			if (data.getPlantName().equals(name)) {
				boolean b1 = temp <= data.temperature_max && temp >= data.temperature_min;
				return b1;
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	// TODO
	public static void setupTooltips(ItemTooltipEvent event) {
		String name = Block.getBlockFromItem(event.getItemStack().getItem()).getRegistryName().toString();
		PlantData plant = null;
		for (PlantData data : PlantData.values()) {
			if (data.getPlantName().equals(name))
				plant = data;
		}
		if (plant != null) {
			float min = plant.getTemperature_min();
			float max = plant.getTemperature_max();
			float proference = plant.getTemperature_proference();
			event.getToolTip().add(//
					I18n.format("quantummeteorology.plant.temperature") + ": "//
							+ miscutil.getTemperatureColor(min) + min//
							+ TextFormatting.RESET + " ~ "//
							+ miscutil.getTemperatureColor(max) + max);
			event.getToolTip().add(//
					I18n.format("quantummeteorology.plant.proferencetemperature") + ": "//
							+ miscutil.getTemperatureColor(proference) + proference);

			if (GuiScreen.isShiftKeyDown()) {
				event.getToolTip().add(I18n.format("quantummeteorology.plant." + plant.toString().toLowerCase()));
			}
		}
	}

	public String getPlantName() {
		return plant_name;
	}

	public float getTemperature_max() {
		return temperature_max;
	}

	public float getTemperature_min() {
		return temperature_min;
	}

	public float getTemperature_proference() {
		return temperature_proference;
	}

	public void setPlant_name(String plant_name) {
		this.plant_name = plant_name;
	}

	public void setTemperature_max(float temperature_max) {
		this.temperature_max = temperature_max;
	}

	public void setTemperature_min(float temperature_min) {
		this.temperature_min = temperature_min;
	}

	public void setTemperature_proference(float temperature_proference) {
		this.temperature_proference = temperature_proference;
	}

	private float min(float a, float b, float c) {
		return Math.min(Math.min(a, c), b);
	}

	private float max(float a, float b, float c) {
		return Math.max(Math.max(a, c), b);
	}
}