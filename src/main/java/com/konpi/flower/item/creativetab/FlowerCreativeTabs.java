package com.konpi.flower.item.creativetab;

import com.konpi.flower.Flower;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * 所有的物品栏都放这个类里，用匿名内部类实例化
 */
public class FlowerCreativeTabs {

    public static final CreativeTabs FOOD = new CreativeTabs(getLabel("food")) {

        @Override
        public ItemStack createIcon() {
            return null;
        }
    };

    public static String getLabel(String name) {
        return Flower.MODID + "." + name;
    }

}
