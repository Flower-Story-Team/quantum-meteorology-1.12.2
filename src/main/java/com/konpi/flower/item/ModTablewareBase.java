package com.konpi.flower.item;

import com.konpi.flower.Flower;
import com.konpi.flower.item.creativetab.ModCreativeTabs;

import net.minecraft.item.Item;

/**
 * 餐具的基础类
 */
public class ModTablewareBase extends Item
{
	//有参数构建函数
	/**ModItemBase([字符串型,物品注册名(ID)]）*/
    public ModTablewareBase(String registryName)
    {
        super();
        this.setRegistryName(registryName);
        this.setTranslationKey(Flower.MODID + "." + registryName);
        this.setCreativeTab(ModCreativeTabs.TABLEWARE);
        this.setMaxStackSize(1);
    }
    
}
