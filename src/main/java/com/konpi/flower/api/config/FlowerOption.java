package com.konpi.flower.api.config;

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
