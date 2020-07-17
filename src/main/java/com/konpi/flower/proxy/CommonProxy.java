package com.konpi.flower.proxy;

import java.io.File;

import com.konpi.flower.Flower;
import com.konpi.flower.common.init.ModConfig;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	/**
	 * 配置文件目录，用于不在游戏里显示的配置文件
	 */
	public static File configDirectory;

	public void preInit(FMLPreInitializationEvent event) {
		configDirectory = new File(event.getModConfigurationDirectory(), Flower.NAME);
		// 生成不显示的配置文件
		ModConfig.preInit(configDirectory);
	}

	public void init(FMLInitializationEvent event) {
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

}