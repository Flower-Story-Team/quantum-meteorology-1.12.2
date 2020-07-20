package com.konpi.quantummeteorology.api.thirst;

import com.konpi.quantummeteorology.api.Capabilities;
import com.konpi.quantummeteorology.api.state.capability.IThirst;

import net.minecraft.entity.player.EntityPlayer;

public class ThirstHelper {

    public static IThirst getThirstData(EntityPlayer player)
    {
        return player.getCapability(Capabilities.THIRST, null);
    }
}
