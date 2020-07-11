package com.konpi.flower.item;

import com.konpi.flower.Flower;
import com.konpi.flower.item.creativetab.FlowerCreativeTabs;
import net.minecraft.item.ItemFood;

/**
 * 食物的基础类
 */
public class ModFoodBase extends ItemFood {

    public ModFoodBase(String registryName, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.setRegistryName(registryName);
        this.setTranslationKey(Flower.MODID + "." + registryName);
        this.setCreativeTab(FlowerCreativeTabs.FOOD);
    }

}
