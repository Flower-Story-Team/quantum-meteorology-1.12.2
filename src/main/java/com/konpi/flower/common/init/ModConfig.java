package com.konpi.flower.common.init;

import com.google.common.collect.Lists;
import com.konpi.flower.common.config.ConfigHandler;
import com.konpi.flower.common.config.plant;

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
