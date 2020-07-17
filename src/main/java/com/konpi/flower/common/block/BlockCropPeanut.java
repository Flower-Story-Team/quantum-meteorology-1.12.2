package com.konpi.flower.common.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockCropPeanut extends BlockCropsBase{

    /**
     * @param registryName 注册名
     * @param seed         种子
     * @param crop         收获的农作物
     */
    public BlockCropPeanut(String registryName, Item seed, Item crop) {
        super("crop_peanut",seed, crop);
    }

    @Override
    public Item getSeed() {
        return super.getSeed();
    }
}
