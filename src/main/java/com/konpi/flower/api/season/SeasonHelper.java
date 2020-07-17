package com.konpi.flower.api.season;

import net.minecraft.world.World;

/**
 * according to sereneseason
 *
 */
public class SeasonHelper {
	public static ISeasonDataProvider dataProvider;

	/**
	 * 获得关于世界季节周期状态的数据
	 * 
	 */
	public static ISeasonState getSeasonState(World world) {
		ISeasonState data;

		if (!world.isRemote) {
			data = dataProvider.getServerSeasonState(world);
		} else {
			data = dataProvider.getClientSeasonState();
		}

		return data;
	}

	public interface ISeasonDataProvider {
		ISeasonState getServerSeasonState(World world);

		ISeasonState getClientSeasonState();
	}
}
