package com.konpi.flower.item;

import com.konpi.flower.Flower;

import net.minecraft.item.Item;

/**
 * 物品的基础类
 */
public class ModItemBase extends Item
{
	//有参数构建函数
	/**ModItemBase([字符串型,物品注册名(ID)]）*/
    public ModItemBase(String registryName)
    {
        super();
        this.setRegistryName(registryName);
        this.setTranslationKey(Flower.MODID + "." + registryName);
    }
    
}
