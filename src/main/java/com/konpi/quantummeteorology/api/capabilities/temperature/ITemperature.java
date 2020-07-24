package com.konpi.quantummeteorology.api.capabilities.temperature;

public interface ITemperature {

	/**
	 * @param 玩家体温
	 */
	public void setTemperature(int temperature);

	/**
	 * @return 玩家体温
	 */
	public int getTemperature();
	

}
