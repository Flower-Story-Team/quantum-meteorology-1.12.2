package com.konpi.quantummeteorology.api.data;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.util.ylllutil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum PlantData {
	SAPLING0(Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.OAK), -30, 50), //
	SAPLING1(Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.SPRUCE), -20, 60), //
	SAPLING2(Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.BIRCH), -10, 70);//

	//


	private PlantData(IBlockState state, float temperature_min, float temperature_max) {
		this(state, temperature_min, (temperature_min + temperature_max) / 2, temperature_max);
	}

	private PlantData(IBlockState state, float temperature_min, float temperature_proference, float temperature_max) {

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
		this.plant_name = state.toString();
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
	public static void setupTooltips(ItemTooltipEvent event) {
		String name = Block.getBlockFromItem(event.getItemStack().getItem())
				.getStateFromMeta(event.getItemStack().getMetadata()).toString();
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
							+ ylllutil.getTemperatureColor(min) + min//
							+ TextFormatting.RESET + " ~ "//
							+ ylllutil.getTemperatureColor(max) + max);
			event.getToolTip().add(//
					I18n.format("quantummeteorology.plant.proferencetemperature") + ": "//
							+ ylllutil.getTemperatureColor(proference) + proference);

			if (GuiScreen.isShiftKeyDown()) {
				event.getToolTip().add(I18n.format("quantummeteorology.plant." + plant.toString().toLowerCase()));
			}
		}
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
	 * @return 植物的最适生长温度
	 */
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