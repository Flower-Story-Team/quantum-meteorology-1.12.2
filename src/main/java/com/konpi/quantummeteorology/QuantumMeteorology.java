package com.konpi.quantummeteorology;

import com.konpi.quantummeteorology.common.command.PlayerCommond;
import com.konpi.quantummeteorology.common.command.SeasonCommand;
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

@Mod(modid = QuantumMeteorology.MODID, name = QuantumMeteorology.NAME, version = QuantumMeteorology.VERSION, acceptedMinecraftVersions = "1.12.2")
public class QuantumMeteorology {
	public static final String MODID = "quantummeteorology";
	public static final String NAME = "Quantum Meteorology";
	public static final String VERSION = "1.0.1";

	public static Logger logger = LogManager.getLogger(QuantumMeteorology.NAME);

	@SidedProxy(clientSide = "com.konpi.quantummeteorology.proxy.ClientProxy", serverSide = "com.konpi.quantummeteorology.proxy.CommonProxy")
	public static CommonProxy proxy;

	static {
		// 允许万能桶
		FluidRegistry.enableUniversalBucket();
	}

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
		event.registerServerCommand(new PlayerCommond());
	}

}
