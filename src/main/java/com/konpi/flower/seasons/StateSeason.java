package com.konpi.flower.seasons;

import com.konpi.flower.seasons.intefaces.ISeasonColor;

public enum StateSeason implements ISeasonColor
{
    EARLY_SPRING(Season.SPRING, 0x778087, 0.85F, 0x6F818F, 0.85F),
    MID_SPRING(Season.SPRING, 0x6F818F, 0x5F849F),
    LATE_SPRING(Season.SPRING, 0x678297, 0x3F89BF),
    EARLY_SUMMER(Season.SUMMER, 0x73808B, 0x5F849F),
    MID_SUMMER(Season.SUMMER, 0xFFFFFF, 0xFFFFFF),
    LATE_SUMMER(Season.SUMMER, 0x877777, 0x9F5F5F),
    EARLY_AUTUMN(Season.AUTUMN, 0x8F6F6F, 0xC44040),
    MID_AUTUMN(Season.AUTUMN, 0x9F5F5F, 0xEF2121),
    LATE_AUTUMN(Season.AUTUMN, 0xAF4F4F, 0.85F, 0xDB3030, 0.85F),
    EARLY_WINTER(Season.WINTER, 0xAF4F4F, 0.60F, 0xDB3030, 0.60F),
    MID_WINTER(Season.WINTER, 0xAF4F4F, 0.45F, 0xDB3030, 0.45F),
    LATE_WINTER(Season.WINTER, 0x8E8181, 0.60F, 0xA57070, 0.60F);


    private Season season;
    private int grassColor;
    private float grassSaturation;
    private int foliageColor;
    private float foliageSaturation;

    StateSeason(Season season, int grassColour, float grassSaturation, int foliageColour, float foliageSaturation)
    {
        this.season = season;
        this.grassColor = grassColour;
        this.grassSaturation = grassSaturation;
        this.foliageColor = foliageColour;
        this.foliageSaturation = foliageSaturation;
    }

    StateSeason(Season season, int grassColour, int foliageColour)
    {
        this(season, grassColour, -1, foliageColour, -1);
    }

    public Season getSeason()
    {
        return this.season;
    }

    public int getGrassColor()
    {
        return this.grassColor;
    }

    public float getGrassSaturation()
    {
        return this.grassSaturation;
    }

    public int getFoliageColor()
    {
        return this.foliageColor;
    }

    public float getFoliageSaturation()
    {
        return this.foliageSaturation;
    }



}
