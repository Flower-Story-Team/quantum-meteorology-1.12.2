package flowerofhua.forge.flower.proxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 *
 */
@Mod(modid = flower.MODID, name = flower.NAME, version = flower.VERSION, acceptedMinecraftVersions = "1.12.2")
public class flower
{
    public static final String MODID = "flower";
    public static final String NAME = "Flower Of Hua";
    public static final String VERSION = "1.0.0";

    @Instance(flower.MODID)
    public static flower instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // TODO
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // TODO
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // TODO
    }
}