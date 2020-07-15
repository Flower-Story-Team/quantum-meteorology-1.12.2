package com.konpi.flower.init;

import com.konpi.flower.Flower;
import com.konpi.flower.tabs.ModCreativeTabs;

import net.minecraft.item.Item;

/**
 * 餐具的基础类
 */
public class ModTablewareBase extends Item
{
    /**
     * @param registryName 注册名
     */
    public ModTablewareBase(String registryName)
    {
        super();
        this.setRegistryName(registryName);
        this.setTranslationKey(Flower.MODID + "." + registryName);
        this.setCreativeTab(ModCreativeTabs.TABLEWARE);
    }
    
}
