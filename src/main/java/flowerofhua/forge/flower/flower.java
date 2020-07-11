package flowerofhua.forge.flower;

import flowerofhua.forge.flower.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */
@Mod(modid = flower.MODID, name = flower.NAME, version = flower.VERSION, acceptedMinecraftVersions = "1.12.2")
public class flower
{
    public static final String MODID = "flower";
    public static final String NAME = "Flower Of Hua";
    public static final String VERSION = "1.0.0";

    private Logger logger = LogManager.getLogger(flower.NAME);


    public Logger getLogger()
    {
        return logger;
    }
    @SidedProxy(clientSide = "flowerofhua.forge.flower.proxy.ClientProxy",
            serverSide = "flowerofhua.forge.flower.proxy.ServerProxy")
    public static CommonProxy proxy;


    @Instance(value = flower.MODID, owner = flower.MODID)
    public static flower INSTANCE;

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