package com.konpi.flower.item;

import com.konpi.flower.Flower;
import com.konpi.flower.item.creativetab.ModCreativeTabs;

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
        //this.setMaxStackSize(16);
    }
    
}
