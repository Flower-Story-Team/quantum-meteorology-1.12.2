package com.konpi.flowerofhua.flower.util;


import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Objects;

import com.konpi.flowerofhua.flower.item.CoarseSalt;

public class RegistryItem {
    public static CoarseSalt coarseSalt;

    @Mod.EventHandler
    public static void regitser() {
        coarseSalt = new CoarseSalt();
        ForgeRegistries.ITEMS.register(coarseSalt.setRegistryName("coarse_salt"));
        ModelLoader.setCustomModelResourceLocation(coarseSalt,0,new ModelResourceLocation(Objects.requireNonNull(coarseSalt.getRegistryName()), "inventory"));
    }
}