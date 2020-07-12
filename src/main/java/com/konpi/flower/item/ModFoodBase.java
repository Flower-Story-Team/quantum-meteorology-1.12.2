package com.konpi.flower.item;

import com.konpi.flower.Flower;
import com.konpi.flower.item.creativetab.FlowerCreativeTabs;
import net.minecraft.item.ItemFood;

/**
 * 食物的基础类
 */

//有参数构建函数
public ModFoodBase(String registryName, int amount, float saturation, boolean isWolfFood)
//ModFoodBase([字符串型,物品注册名(ID)],[整数型，回复的饥饿值(2点=一个鸡腿)],[小数型,添加的饱和度(用于回血)],[布尔值,能否给驯服的狼吃])
{
    super(amount, saturation, isWolfFood);
    this.setRegistryName(registryName);//注册名
    this.setTranslationKey(Flower.MODID + "." + registryName);//lang文件相关
    this.setCreativeTab(FlowerCreativeTabs.FOOD);//添加到物品栏
}

