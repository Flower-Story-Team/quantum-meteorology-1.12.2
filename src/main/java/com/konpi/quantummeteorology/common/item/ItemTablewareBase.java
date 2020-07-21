package com.konpi.quantummeteorology.common.item;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.init.ModCreativeTabs;

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
        this.setTranslationKey(QuantumMeteorology.MODID + "." + registryName);
        this.setCreativeTab(ModCreativeTabs.TABLEWARE);
    }
    
}
