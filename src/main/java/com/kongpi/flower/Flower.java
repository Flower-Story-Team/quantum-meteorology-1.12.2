package com.kongpi.flower;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kongpi.flower.common.command.SeasonCommand;
import com.kongpi.flower.proxy.CommonProxy;

@Mod(modid = Flower.MODID, name = Flower.NAME, version = Flower.VERSION, acceptedMinecraftVersions = "1.12.2")
public class Flower {

	public static final String MODID = "flower";
	public static final String NAME = "Flower of Hua";
	public static final String VERSION = "1.1.0";

	public static Logger logger = LogManager.getLogger(Flower.MODID);

	@SidedProxy(clientSide = "com.kongpi.flower.proxy.ClientProxy", serverSide = "com.kongpi.flower.proxy.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new SeasonCommand());
	}

}
