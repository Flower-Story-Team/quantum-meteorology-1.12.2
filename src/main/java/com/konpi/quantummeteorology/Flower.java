package com.konpi.quantummeteorology;

import com.konpi.quantummeteorology.common.command.SeasonCommand;
import com.konpi.quantummeteorology.common.command.TANCommand;
import com.konpi.quantummeteorology.common.fluid.FluidBase;
import com.konpi.quantummeteorology.common.init.ModConfig;
import com.konpi.quantummeteorology.common.init.ModFertility;
import com.konpi.quantummeteorology.common.init.ModHandlers;
import com.konpi.quantummeteorology.common.init.ModStates;
import com.konpi.quantummeteorology.proxy.CommonProxy;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 需要把各种init放进common proxy和client proxy里面去
 */
@Mod(modid = Flower.MODID, name = Flower.NAME, version = Flower.VERSION, acceptedMinecraftVersions = "1.12.2")
public class Flower {
	public static final String MODID = "quantummeteorology";
	public static final String NAME = "Quantum Meteorology";
	public static final String VERSION = "1.0.1";
	
	public static Logger logger = LogManager.getLogger(Flower.NAME);

	@SidedProxy(clientSide = "com.konpi.quantummeteorology.proxy.ClientProxy", serverSide = "com.konpi.quantummeteorology.proxy.CommonProxy")
	public static CommonProxy proxy;

	static
	{
		// 允许万能桶
		FluidRegistry.enableUniversalBucket();
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModStates.init();
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
		event.registerServerCommand(new TANCommand());
	}

}
