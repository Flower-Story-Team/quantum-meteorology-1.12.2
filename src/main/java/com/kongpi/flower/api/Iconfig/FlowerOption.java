package com.kongpi.flower.api.Iconfig;

import com.kongpi.flower.api.Iconfig.ISyncedOption;

/**
 * 这里的是不显示在游戏里的配置文件设置。 凡显示的都要订阅事件，不显示的都要extends ConfigHandler
 *
 */
public enum FlowerOption implements ISyncedOption {

	;// 本来有东西的，但是被我放到PlantConfig里面去了
	private final String optionName;

	FlowerOption(String name) {
		this.optionName = name;
	}

	@Override
	public String getOptionName() {
		return this.optionName;
	}
}
