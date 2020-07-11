package com.konpi.flowerofhua.flower.item.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativesTabsManager {
    public static CreativeTabs foodTab;
    public CreativesTabsManager(FMLPreInitializationEvent event) {
        foodTab = new FoodTab("food");
    }
}
