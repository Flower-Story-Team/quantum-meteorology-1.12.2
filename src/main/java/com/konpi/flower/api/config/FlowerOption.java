package com.konpi.flower.api.config;

public enum FlowerOption implements ISyncedOption
{
    DAY_DURATION("Day Duration"),
    SEASON_STATE_DURATION("Sub Season Duration"),
    STARTING_SEASON_STATE("Starting Season State");
    
    private final String optionName;

    FlowerOption(String name)
    {
        this.optionName = name;
    }

    @Override
    public String getOptionName()
    {
        return this.optionName;
    }
}
