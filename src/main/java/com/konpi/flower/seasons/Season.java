package com.konpi.flower.seasons;

import com.konpi.flower.seasons.intefaces.ISeasonColor;

public enum Season 
{
    SPRING, SUMMER, AUTUMN, WINTER;

    public enum SeasonState implements ISeasonColor
    {
        EARLY_SPRING(SPRING, 0x66CC66, 0x66CC66),
        MID_SPRING(SPRING, 0x66CC33, 0x66CC33),
        LATE_SPRING(SPRING, 0x009933, 0x009933),
        EARLY_SUMMER(SUMMER, 0x009933, 0x009933),
        MID_SUMMER(SUMMER, 0x006600, 0x006600),
        LATE_SUMMER(SUMMER, 0x669900, 0x669900),
        EARLY_AUTUMN(AUTUMN, 0x666633, 0x666633),
        MID_AUTUMN(AUTUMN, 0x666600, 0x666600),
        LATE_AUTUMN(AUTUMN, 0x663300,  0x663300),
        EARLY_WINTER(WINTER, 0xCCCC99, 0xCCCC99),
        MID_WINTER(WINTER, 0xCCCC99,  0xCCCC99),
        LATE_WINTER(WINTER, 0x669966,  0x669966);

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
