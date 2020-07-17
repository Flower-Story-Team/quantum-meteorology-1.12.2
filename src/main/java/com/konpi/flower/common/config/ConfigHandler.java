package com.konpi.flower.common.config;

import java.io.File;
import com.konpi.flower.Flower;
import com.konpi.flower.api.config.ISyncedOption;
import com.konpi.flower.api.config.SyncedConfig;
import com.konpi.flower.common.init.ModConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public abstract class ConfigHandler {

	public Configuration config;
	public final String description;

	protected ConfigHandler(File configFile, String description) {
		config = new Configuration(configFile);
		loadConfiguration();

		MinecraftForge.EVENT_BUS.register(this);
		this.description = description;
		ModConfig.configHandlers.add(this);
	}

	protected abstract void loadConfiguration();

	protected <T> void addSyncedValue(ISyncedOption option, T defaultValue, String category, String comment,
			T... args) {
		String value = "";

		if (defaultValue instanceof String) {
			value = config.getString(option.getOptionName(), category, defaultValue.toString(), comment);
		} else if (defaultValue instanceof Integer) {
			value = "" + config.getInt(option.getOptionName(), category, (Integer) defaultValue, (Integer) args[0],
					(Integer) args[1], comment);
		} else if (defaultValue instanceof Boolean) {
			value = "" + config.getBoolean(option.getOptionName(), category, (Boolean) defaultValue, comment);
		} else if (defaultValue instanceof Float) {
			value = "" + config.getFloat(option.getOptionName(), category, (Float) defaultValue, (Float) args[0],
					(Float) args[1], comment);
		}

		SyncedConfig.addOption(option, value);
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equalsIgnoreCase(Flower.MODID)) {
			loadConfiguration();
		}
	}
}
