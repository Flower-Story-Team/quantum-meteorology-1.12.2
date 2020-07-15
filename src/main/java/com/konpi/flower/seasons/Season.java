package com.konpi.flower.seasons;

import com.konpi.flower.seasons.intefaces.ISeasonColor;

public enum Season 
{
    SPRING, SUMMER, AUTUMN, WINTER;

    public enum SeasonState implements ISeasonColor
    {
        EARLY_SPRING(SPRING, 0x66CCFF, 0x66CCFF),
        MID_SPRING(SPRING, 0x66CCFF, 0x5F849F),
        LATE_SPRING(SPRING, 0x678297, 0x3F89BF),
        EARLY_SUMMER(SUMMER, 0x73808B, 0x5F849F),
        MID_SUMMER(SUMMER, 0xFFFFFF, 0xFFFFFF),
        LATE_SUMMER(SUMMER, 0x877777, 0x9F5F5F),
        EARLY_AUTUMN(AUTUMN, 0x8F6F6F, 0xC44040),
        MID_AUTUMN(AUTUMN, 0x9F5F5F, 0xEF2121),
        LATE_AUTUMN(AUTUMN, 0xAF4F4F,  0xDB3030),
        EARLY_WINTER(WINTER, 0xAF4F4F, 0xDB3030),
        MID_WINTER(WINTER, 0xAF4F4F,  0xDB3030),
        LATE_WINTER(WINTER, 0x8E8181,  0xA57070);

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
