package com.konpi.flower;

import com.konpi.flower.common.command.SeasonCommand;
import com.konpi.flower.common.init.ModConfig;
import com.konpi.flower.common.init.ModFertility;
import com.konpi.flower.common.init.ModHandlers;
import com.konpi.flower.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Flower.MODID, name = Flower.NAME, version = Flower.VERSION, acceptedMinecraftVersions = "1.12.2")
public class Flower {
	public static final String MODID = "flower";
	public static final String NAME = "Flower of Hua";
	public static final String VERSION = "1.0.0";

	public static Logger logger = LogManager.getLogger(Flower.NAME);

	@SidedProxy(clientSide = "com.konpi.flower.proxy.ClientProxy", serverSide = "com.konpi.flower.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static File configDirectory;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		configDirectory = new File(event.getModConfigurationDirectory(), this.NAME);
		ModConfig.preInit(configDirectory);
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ModHandlers.init();
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ModFertility.init();
		proxy.postInit(event);
		ModHandlers.postInit();
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new SeasonCommand());
	}

}
