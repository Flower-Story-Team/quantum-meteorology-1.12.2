package com.konpi.flowerofhua.flower.item.creativetab;

import com.konpi.flowerofhua.flower.Flower;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FlowerCreativeTabs {

    public static final CreativeTabs FOOD = new CreativeTabs(getLabel("food")) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.APPLE);
        }
    };

    public static String getLabel(String name) {
        return Flower.MODID + "." + name;
    }
}
