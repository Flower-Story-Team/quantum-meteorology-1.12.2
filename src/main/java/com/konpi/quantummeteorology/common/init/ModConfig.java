package com.konpi.quantummeteorology.common.init;

import com.google.common.collect.Lists;
import com.konpi.quantummeteorology.common.config.ConfigHandler;
import com.konpi.quantummeteorology.common.config.plant;

import java.io.File;
import java.util.List;

/**
 * 用来生成不显示的配置文件
 *
 */
public class ModConfig {

	/**
	 * 静态必须，在季节作物那里有用到
	 */
	public static plant plant;

	public static void preInit(File configDir) {
		plant = new plant(new File(configDir, "plants.cfg"));
	}

}
