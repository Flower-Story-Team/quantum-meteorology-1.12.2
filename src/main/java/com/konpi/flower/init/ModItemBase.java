package com.konpi.flower.init;

import com.konpi.flower.Flower;

import net.minecraft.item.Item;

/**
 * 物品的基础类
 */
public class ModItemBase extends Item
{
    /**
     * @param registryName 注册名
     */
    public ModItemBase(String registryName)
    {
        super();
        this.setRegistryName(registryName);
        this.setTranslationKey(Flower.MODID + "." + registryName);
    }
    
}
