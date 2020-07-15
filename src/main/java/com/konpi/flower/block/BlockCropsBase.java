package com.konpi.flower.block;

import com.konpi.flower.Flower;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

/**
 * 农作物的基础类
 */
public class BlockCropsBase extends BlockCrops {

    private final Item SEED;
    private final Item CROP;

    /**
     * @param registryName 注册名
     * @param seed 种子
     * @param crop 收获的农作物
     */
    public BlockCropsBase(String registryName, Item seed, Item crop) {
        this.setRegistryName(registryName);
        this.setTranslationKey(Flower.MODID + "." + registryName);
        this.SEED = seed;
        this.CROP = crop;
    }

    @Override
    public Item getSeed()
    {
        return this.SEED;
    }

    @Override
    public Item getCrop()
    {
        return this.CROP;
    }
}
