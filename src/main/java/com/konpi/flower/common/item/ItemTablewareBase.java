package com.konpi.flower.common.item;

import com.konpi.flower.Flower;
import com.konpi.flower.common.init.ModCreativeTabs;

import net.minecraft.item.Item;

/**
 * 餐具的基础类
 */
public class ItemTablewareBase extends Item
{
    /**
     * @param registryName 注册名
     */
    public ItemTablewareBase(String registryName)
    {
        super();
        this.setRegistryName(registryName);
        this.setTranslationKey(Flower.MODID + "." + registryName);
        this.setCreativeTab(ModCreativeTabs.TABLEWARE);
    }
    
}
