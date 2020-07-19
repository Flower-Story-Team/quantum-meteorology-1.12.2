package com.kongpi.flower.common.config;

import com.kongpi.flower.Flower;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Flower.MODID, name = Flower.NAME + "/general", category = "")
@Mod.EventBusSubscriber
public class CommonConfig {

	public static General_Config general_config = new General_Config();

	public static Temperature_Config temperature_dependence = new Temperature_Config();

	public static Humidity_Config humidity_dependence = new Humidity_Config();

	public static class General_Config {
		@Config.Comment({ "是否使用季节颜色 | Whether to use seasonal color" })
		@Config.LangKey("flower.color_change")
		public boolean color_change = true;

		@Config.Comment({ "使用季节颜色的维度 | Dimensions that use seasonal color" })
		@Config.LangKey("flower.whitelist_dimension")
		public String[] whitelist = { "0" };

		@Config.Comment({ "一个月的天数 | Days of a month" })
		@Config.LangKey("flower.monthduration")
		public int month_duration = 8;

		public int starting_time = 0;
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
		@Config.LangKey("flower.temperature.world")
		public boolean temperature_world = true;

		@Config.LangKey("flower.temperature.month")
		public boolean temperature_month = true;

		@Config.LangKey("flower.temperature.day")
		public boolean temperature_day = true;

		@Config.LangKey("flower.temperature.biome")
		public boolean temperature_biome = true;

		@Config.LangKey("flower.temperature.height")
		public boolean temperature_height = true;

		@Config.LangKey("flower.temperature.playermove")
		public boolean temperature_playermove = true;

	}

	public static class Humidity_Config {

		@Config.LangKey("flower.humidity.world")
		public boolean humidity_world = true;

		@Config.LangKey("flower.humidity.month")
		public boolean humidity_month = true;

		@Config.LangKey("flower.humidity.biome")
		public boolean humidity_biome = true;

		@Config.LangKey("flower.humidity.playermove")
		public boolean humidity_playermove = true;
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(Flower.MODID)) {
			ConfigManager.sync(event.getModID(), Config.Type.INSTANCE);
		}
	}

}