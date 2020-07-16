package com.konpi.flower.common.item;

import com.konpi.flower.Flower;
import com.konpi.flower.init.ModCreativeTabs;
import net.minecraft.item.ItemFood;

/**
 * 食物的基础类
 */
public class ItemFoodBase extends ItemFood
{
    /**
     * @param registryName 注册名
     * @param amount 回复饥饿值
     * @param saturation 回复饱和度
     * @param isWolfFood 是否能给狼吃
     */
    public ItemFoodBase(String registryName, int amount, float saturation, boolean isWolfFood)
    {
        super(amount, saturation, isWolfFood);
        this.setRegistryName(registryName);
        this.setTranslationKey(Flower.MODID + "." + registryName);
        this.setCreativeTab(ModCreativeTabs.FOOD);
    }
    
}
