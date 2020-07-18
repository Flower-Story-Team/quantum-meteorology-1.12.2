package com.kongpi.flower.common.config;

import com.kongpi.flower.Flower;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Flower.MODID, name = Flower.NAME + "/general", category = "")
@Mod.EventBusSubscriber
public class ClientConfig {

	public static General_Config general_config = new General_Config();
	public static Season_Color_Config season_color_config = new Season_Color_Config();

	public static class General_Config {
		@Config.Comment({ "是否使用季节颜色 | Whether to use seasonal color" })
		@Config.LangKey("flower.color_change")
		public boolean color_change = true;

		@Config.Comment({ "使用季节颜色的维度 | Dimensions that use seasonal color" })
		@Config.LangKey("flower.whitelist_dimension")
		public String[] whitelist = { "0" };

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

	public static class Season_Color_Config {
		// TODO:季节颜色可自定义
		@Config.Comment({ "看一下行不行" })
		public int[] s = {};

	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(Flower.MODID)) {
			ConfigManager.sync(event.getModID(), Config.Type.INSTANCE);
		}
	}

}