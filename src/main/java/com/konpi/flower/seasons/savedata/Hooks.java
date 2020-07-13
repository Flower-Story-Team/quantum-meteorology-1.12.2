package com.konpi.flower.seasons.savedata;
/*
import com.konpi.flower.seasons.Season;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;

public class Hooks {
    public static float getBiomeTemperatureInSeason(Season.SubSeason subSeason, Biome biome, BlockPos pos)
    {
        boolean tropicalBiome = BiomeConfig.usesTropicalSeasons(biome);
        float biomeTemp = biome.getTemperature(pos);

        if (!tropicalBiome && biome.getTemperature() <= 0.8F && BiomeConfig.enablesSeasonalEffects(biome))
        {
            switch (subSeason)
            {
                default:
                    break;

                case LATE_SPRING: case EARLY_AUTUMN:
                biomeTemp = MathHelper.clamp(biomeTemp - 0.1F, -0.5F, 2.0F);
                break;

                case MID_SPRING: case MID_AUTUMN:
                biomeTemp = MathHelper.clamp(biomeTemp - 0.2F, -0.5F, 2.0F);
                break;

                case EARLY_SPRING: case LATE_AUTUMN:
                biomeTemp = MathHelper.clamp(biomeTemp - 0.4F, -0.5F, 2.0F);
                break;

                case EARLY_WINTER: case MID_WINTER: case LATE_WINTER:
                biomeTemp = MathHelper.clamp(biomeTemp - 0.8F, -0.5F, 2.0F);
                break;
            }
        }

        return biomeTemp;
    }

}


 */