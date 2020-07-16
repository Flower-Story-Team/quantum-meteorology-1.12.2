package com.konpi.flower.common.config;

import com.konpi.flower.Flower;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Flower.MODID, name = Flower.NAME + "/general", category = "")
@Mod.EventBusSubscriber
public class flower {

	public static General_Config general_config = new General_Config();

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(Flower.MODID)) {
			ConfigManager.sync(event.getModID(), Config.Type.INSTANCE); // resync config
		}
	}

	public static class General_Config {
		@Config.Comment({ "The duration of a Minecraft day in ticks" })
		@Config.LangKey("flower.day_duration")
		@Config.RangeInt(min = 20)
		public int day_duration = 48000;

		@Config.Comment({ "The duration of a sub season in days" })
		@Config.LangKey("flower.season_state_duration")
		@Config.RangeInt(min = 1)
		public int season_state_duration = 7;

		@Config.Comment({
				"The starting sub season for new worlds.  0 = Random, 1 - 3 = Early/Mid/Late Spring, 4 - 6 = Early/Mid/Late Summer, 7 - 9 = Early/Mid/Late Autumn, 10 - 12 = Early/Mid/Late Winter" })
		@Config.LangKey("flower.starting_season_state")
		@Config.RangeInt(min = 0, max = 12)
		public int starting_season_state = 7;

	}

}
