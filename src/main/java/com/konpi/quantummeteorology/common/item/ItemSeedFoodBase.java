package com.konpi.quantummeteorology.common.item;

import com.konpi.quantummeteorology.Flower;
import com.konpi.quantummeteorology.common.init.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;

/**
 * 可以作为种子的食物的基础类
 */
public class ItemSeedFoodBase extends ItemSeedFood {

    /**
     * @param registryName 注册名
     * @param healAmount 回复饥饿值
     * @param saturation 回复饱和度
     * @param crops 农作物
     */
    public ItemSeedFoodBase(String registryName, int healAmount, float saturation, Block crops) {
        super(healAmount, saturation, crops, Blocks.FARMLAND);
        this.setRegistryName(registryName);
        this.setTranslationKey(Flower.MODID + "." + registryName);
        this.setCreativeTab(ModCreativeTabs.FOOD);
    }

}
