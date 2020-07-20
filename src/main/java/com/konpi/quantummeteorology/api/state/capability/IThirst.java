package com.konpi.quantummeteorology.api.state.capability;

import com.konpi.quantummeteorology.api.state.IPlayerState;

public interface IThirst extends IPlayerState {

	public void setThirst(int thirst);

	public void setHydration(float hydration);

	public void setExhaustion(float exhaustion);

	public void addStats(int thirst, float hydration);

	public int getThirst();

	public float getHydration();

	public float getExhaustion();

	public void setChangeTime(int ticks);

	public int getChangeTime();
}
