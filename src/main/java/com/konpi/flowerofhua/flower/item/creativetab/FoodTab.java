package com.konpi.flowerofhua.flower.item.creativetab;

import com.konpi.flowerofhua.flower.item.CoarseSalt;

//import flowerofhua.forge.flower.item.CoarseSalt;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class FoodTab extends CreativeTabs {
    public FoodTab(String floweroffood) {
        super(floweroffood);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(new CoarseSalt());
    }
}
