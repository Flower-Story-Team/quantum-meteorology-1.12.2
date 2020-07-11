package com.konpi.flowerofhua.flower.proxy;

import com.konpi.flowerofhua.flower.item.creativetab.CreativesTabsManager;
import com.konpi.flowerofhua.flower.util.RegistryItem;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        new CreativesTabsManager(event);
        new RegistryItem();
        /*new RegistryCapability(event);*/
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}