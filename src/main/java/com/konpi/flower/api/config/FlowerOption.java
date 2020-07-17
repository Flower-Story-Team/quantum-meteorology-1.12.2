package com.konpi.flower.api.config;

/**
 * 这里的是不显示在游戏里的配置文件设置。 凡显示的都要订阅事件，不显示的都要extends ConfigHandler
 *
 */
public enum FlowerOption implements ISyncedOption {

	spring_crops("spring_crops"), //
	summer_crops("summer_crops"), //
	autumn_crops("autumn_crops"), //
	winter_crops("winter_crops");//

	private final String optionName;

	FlowerOption(String name) {
		this.optionName = name;
	}

	@Override
	public String getOptionName() {
		return this.optionName;
	}
}
