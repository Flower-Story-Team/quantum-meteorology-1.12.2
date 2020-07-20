package com.konpi.quantummeteorology.common.item;

import com.konpi.quantummeteorology.Flower;

import net.minecraft.item.Item;

/**
 * 物品的基础类
 */
public class ItemBase extends Item
{
    /**
     * @param registryName 注册名
     */
    public ItemBase(String registryName)
    {
        super();
        this.setRegistryName(registryName);
        this.setTranslationKey(Flower.MODID + "." + registryName);
    }
    
}
