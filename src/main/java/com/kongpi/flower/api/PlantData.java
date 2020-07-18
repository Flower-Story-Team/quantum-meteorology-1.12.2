package com.kongpi.flower.api;

import java.util.Map;
import com.google.common.collect.Maps;
import com.kongpi.flower.Flower;
import com.kongpi.flower.common.FlowerUtil;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum PlantData {
	SAPLING("minecraft:sapling", -30, 50, 0, 100);//

	private PlantData(String PlantName, float temperature_min, float temperature_max, float humidity_min,
			float humidity_max) {
		this(PlantName, temperature_min, (temperature_min + temperature_max) / 2, temperature_max, humidity_min,
				(humidity_min + humidity_max) / 2, humidity_max, false);
	}

	/**
	 * @param PlantName
	 *            植物的注册名字
	 * @param temperature_min
	 *            植物能承受的最低温度
	 * @param temperature_max
	 *            植物能承受的最高温度
	 * @param humidity_min
	 *            植物能承受的最小绝对湿度
	 * @param humidity_max
	 *            植物能承受的最大绝对湿度
	 */
	private PlantData(String PlantName, float temperature_min, float temperature_max, float humidity_min,
			float humidity_max, boolean usenbt) {
		this(PlantName, temperature_min, (temperature_min + temperature_max) / 2, temperature_max, humidity_min,
				(humidity_min + humidity_max) / 2, humidity_max, usenbt);
	}

	/**
	 * @param PlantName
	 *            植物的注册名字
	 * @param temperature_min
	 *            植物能承受的最低温度
	 * @param temperature_proference
	 *            植物的生长最适温度
	 * @param temperature_max
	 *            植物能承受的最高温度
	 * @param humidity_min
	 *            植物能承受的最小绝对湿度
	 * @param humidity_proference
	 *            植物的生长最适湿度
	 * @param humidity_max
	 *            植物能承受的最大绝对湿度
	 */
	private PlantData(String PlantName, float temperature_min, float temperature_proference, float temperature_max,
			float humidity_min, float humidity_proference, float humidity_max, boolean usenbt) {
		if (temperature_min > temperature_proference || temperature_proference > temperature_max
				|| temperature_min > temperature_max) {
			Flower.logger.error("temeprature setting error: " + this.toString());
			this.temperature_min = 20;
			this.temperature_max = 20;
			this.temperature_proference = 20;
		} else {
			this.temperature_min = temperature_min;
			this.temperature_proference = temperature_proference;
			this.temperature_max = temperature_max;
		}
		if (humidity_min > humidity_max || humidity_min > humidity_proference || humidity_proference > humidity_max) {
			Flower.logger.error("humidity setting error: " + this.toString());
			this.abs_humidity_max = 50;
			this.abs_humidity_min = 50;
			this.abs_humidity_profenerce = 50;
		} else {
			this.abs_humidity_min = humidity_min;
			this.abs_humidity_profenerce = humidity_proference;
			this.abs_humidity_max = humidity_max;
		}
		this.plant_name = PlantName;
		this.usenbt = usenbt;
	}

	private float temperature_min;
	private float temperature_proference;
	private float temperature_max;
	private float abs_humidity_min;
	private float abs_humidity_profenerce;
	private float abs_humidity_max;
	private String plant_name;
	private boolean usenbt;

	public static boolean canGrow(String name, float temp, float humidity) {
		for (PlantData data : PlantData.values()) {
			if (data.getPlantName().equals(name)) {
				boolean b1 = temp <= data.temperature_max && temp >= data.temperature_min;
				boolean b2 = humidity <= data.abs_humidity_max && humidity >= data.abs_humidity_min;
				return b1 && b2;
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public static void setupTooltips(ItemTooltipEvent event) {
		String name = event.getItemStack().getItem().getRegistryName().toString();
		// int nbt =
		// event.getItemStack().getItem().getNBTShareTag(event.getItemStack()).getInteger("variant");
		// 这个是专门针对树种的
		PlantData plant = null;
		for (PlantData data : PlantData.values()) {
			// if (plant.usenbt) {
			// if (data.getPlantName().equals(name)&&?)
			// } else {
			if (data.getPlantName().equals(name))
				plant = data;
			// }
		}
		if (plant != null) {
			float min = plant.getTemperature_min();
			float max = plant.getTemperature_max();
			float proference = plant.getTemperature_proference();
			event.getToolTip().add(//
					I18n.format("flower.plant.temperature") + ": "//
							+ FlowerUtil.getTemperatureColor(min) + min//
							+ TextFormatting.RESET + " ~ "//
							+ FlowerUtil.getTemperatureColor(max) + max//
							+ TextFormatting.RESET + "("//
							+ FlowerUtil.getTemperatureColor(proference) + proference//
							+ TextFormatting.RESET + ")");

			min = plant.getHumidity_min();
			max = plant.getHumidity_max();
			proference = plant.getHumidity_profenerce();
			event.getToolTip().add(//
					I18n.format("flower.plant.humidity") + ": "//
							+ FlowerUtil.getHumidityColor(min) + min//
							+ TextFormatting.RESET + " ~ "//
							+ FlowerUtil.getHumidityColor(max) + max//
							+ TextFormatting.RESET + "("//
							+ FlowerUtil.getHumidityColor(proference) + proference//
							+ TextFormatting.RESET + ")");

			if (GuiScreen.isShiftKeyDown()) {
				event.getToolTip().add(I18n.format("flower.plant." + plant.toString().toLowerCase()));
			}
		}
	}

	/**
	 * @return 植物能承受的最大绝对湿度
	 */
	public float getHumidity_max() {
		return abs_humidity_max;
	}

	/**
	 * @return 植物能承受的最小绝对湿度
	 */
	public float getHumidity_min() {
		return abs_humidity_min;
	}

	/**
	 * @return 植物的注册id
	 */
	public String getPlantName() {
		return plant_name;
	}

	/**
	 * @return 植物能承受的最高温度
	 */
	public float getTemperature_max() {
		return temperature_max;
	}

	/**
	 * @return 植物能承受的最低温度
	 */
	public float getTemperature_min() {
		return temperature_min;
	}

	/**
	 * @return 植物的最适生长湿度
	 */
	public float getHumidity_profenerce() {
		return abs_humidity_profenerce;
	}

	/**
	 * @return 植物的最适生长温度
	 */
	public float getTemperature_proference() {
		return temperature_proference;
	}

	public void setAbs_humidity_max(float abs_humidity_max) {
		this.abs_humidity_max = abs_humidity_max;
	}

	public void setAbs_humidity_min(float abs_humidity_min) {
		this.abs_humidity_min = abs_humidity_min;
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

	public void setAbs_humidity_profenerce(float abs_humidity_profenerce) {
		this.abs_humidity_profenerce = abs_humidity_profenerce;
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