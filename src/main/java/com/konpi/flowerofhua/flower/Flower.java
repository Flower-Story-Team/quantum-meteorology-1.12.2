package com.konpi.flowerofhua.flower;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.konpi.flowerofhua.flower.proxy.CommonProxy;


@Mod(modid = Flower.MODID, name = Flower.NAME, version = Flower.VERSION, acceptedMinecraftVersions = "1.12.2")
public class Flower
{
    public static final String MODID = "flower";
    public static final String NAME = "Flower of Hua";
    public static final String VERSION = "1.0.0";

    public static Logger logger = LogManager.getLogger(Flower.NAME);

    public Logger getLogger() {
        return logger;
    }
    @SidedProxy(clientSide = "com.konpi.flowerofhua.flower.proxy.ClientProxy", serverSide = "com.konpi.flowerofhua.flower.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}