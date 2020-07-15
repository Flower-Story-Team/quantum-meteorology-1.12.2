package com.konpi.flower.seasons;

import com.konpi.flower.seasons.intefaces.ISeasonColor;

public enum Season 
{
    SPRING, SUMMER, AUTUMN, WINTER;

    public enum SeasonState implements ISeasonColor
    {
        //16进制颜色查询 https://encycolorpedia.cn
        EARLY_SPRING(SPRING, 0x639749, 0x639749),
        MID_SPRING(SPRING, 0x5E9E3d, 0x5E9E3d),
        LATE_SPRING(SPRING, 0x558f38, 0x558f38),
        EARLY_SUMMER(SUMMER, 0x44722C, 0x44722c),
        MID_SUMMER(SUMMER, 0x44722c, 0x44722c),
        LATE_SUMMER(SUMMER, 0x3a6e47, 0x3a6e47),
        EARLY_AUTUMN(AUTUMN, 0x474f2a, 0x3a4322),
        MID_AUTUMN(AUTUMN, 0x4c4a27, 0x57521e),
        LATE_AUTUMN(AUTUMN, 0x575b2e, 0x4a3a1e),
        EARLY_WINTER(WINTER, 0x4d4f2c, 0x363c20),
        MID_WINTER(WINTER, 0x494b31, 0x5a6737),
        LATE_WINTER(WINTER, 0x5a6737, 0x5a6737);

        public static final SeasonState[] VALUES = SeasonState.values();

        private Season season;
        private int grassColor;
        private int foliageColor;
        
        SeasonState(Season season, int grassColour, int foliageColour)
        {
            this.season = season;
            this.grassColor = grassColour;
            this.foliageColor = foliageColour;
        }
        
        public Season getSeason()
        {
            return this.season;
        }
        
        public int getGrassColor()
        {
            return this.grassColor;
        }
        
        public int getFoliageColor()
        {
            return this.foliageColor;
        }
        
    }

    public enum TropicalSeasonState implements ISeasonColor
    {
        EARLY_DRY(0xFFFFFF, 0xFFFFFF),
        MID_DRY(0xA58668, 0xB7867C),
        LATE_DRY(0x8E7B6D, 0xA08B86),
        EARLY_WET(0x758C8A, 0x728C91),
        MID_WET(0x548384, 0x2498AE),
        LATE_WET(0x658989, 0x4E8893);

        public static final TropicalSeasonState[] VALUES = TropicalSeasonState.values();

        private int grassColor;
        private int foliageColor;

        TropicalSeasonState(int grassColour, int foliageColour)
        {
            this.grassColor = grassColour;
            this.foliageColor = foliageColour;
        }

        public int getGrassColor()
        {
            return this.grassColor;
        }

        public int getFoliageColor()
        {
            return this.foliageColor;
        }

    }
}
