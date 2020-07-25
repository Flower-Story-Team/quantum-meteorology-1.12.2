package com.konpi.quantummeteorology.common.config;

import com.konpi.quantummeteorology.QuantumMeteorology;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = QuantumMeteorology.MODID, name = QuantumMeteorology.NAME + "/general", category = "")
@Mod.EventBusSubscriber
public class CommonConfig {

	public static General_Config general_config = new General_Config();

	public static Temperature_Config temperature_dependence = new Temperature_Config();

	public static class General_Config {
		@Config.Comment({ "是否使用季节颜色 | Whether to use seasonal color" })
		@Config.RequiresMcRestart
		@Config.LangKey("quantummeteorology.color_change")
		public boolean seasonal_color_change = true;

		@Config.Comment({ "使用季节颜色的维度 | Dimensions that use seasonal color" })
		@Config.LangKey("quantummeteorology.whitelist_dimension")
		public String[] whitelist = { "0" };

		@Config.Comment({ "一个月的天数 | Days of a month" })
		@Config.LangKey("quantummeteorology.monthduration")
		public int month_duration = 8;

		public int starting_time = 0;

		public boolean bottle_thirst = true;
	}

	public static boolean isWhiteListDimension(int dimension) {
		int d;
		for (String s : general_config.whitelist) {
			d = Integer.valueOf(s);
			if (dimension == d)
				return true;
		}
		return false;
	}

	public static class Temperature_Config {
		@Config.Comment({ "是否启用世界温度|Whether to use world temperature" })
		@Config.LangKey("quantummeteorology.temperature.world")
		public boolean temperature_world = true;

		@Config.Comment({ "是否使用月份温度 | Whether to use month temperature" })
		@Config.LangKey("quantummeteorology.temperature.month")
		public boolean temperature_month = true;

		@Config.Comment({ "是否使用日夜温度 | Whether to use day-night temperature" })
		@Config.LangKey("quantummeteorology.temperature.day")
		public boolean temperature_day = true;

		@Config.Comment({ "是否使用生物群系温度 | Whether to use biome temperature" })
		@Config.LangKey("quantummeteorology.temperature.biome")
		public boolean temperature_biome = true;

		@Config.Comment({ "是否使用高度温度 | Whether to use height temperature" })
		@Config.LangKey("quantummeteorology.temperature.height")
		public boolean temperature_height = true;

		@Config.Comment({ "是否使用玩家运动温度 | Whether to use player-move temperature" })
		@Config.LangKey("quantummeteorology.temperature.playermove")
		public boolean temperature_playermove = true;

	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(QuantumMeteorology.MODID)) {
			ConfigManager.sync(event.getModID(), Config.Type.INSTANCE);
		}
	}

}