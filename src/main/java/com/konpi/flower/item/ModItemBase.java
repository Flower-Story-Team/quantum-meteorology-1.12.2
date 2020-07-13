package com.konpi.flower.item;

import com.konpi.flower.Flower;
import com.konpi.flower.item.creativetab.FlowerCreativeTabs;

import net.minecraft.item.Item;

/**
 * 物品的基础类
 */
public class ModItemBase extends Item//Food
{
	//有参数构建函数
	/**ModItemBase([字符串型,物品注册名(ID)]）*/
    public ModItemBase(String registryName, int maxStackSize)
    {
        //super(amount, saturation, isWolfFood);
    	this.setMaxStackSize(maxStackSize);
        this.setRegistryName(registryName);
        this.setTranslationKey(Flower.MODID + "." + registryName);
        //this.setCreativeTab(FlowerCreativeTabs.FOOD);
    }
    
}
