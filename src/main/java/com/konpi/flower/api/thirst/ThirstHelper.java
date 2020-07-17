package com.konpi.flower.api.thirst;

import com.konpi.flower.api.Capabilities;
import com.konpi.flower.api.state.capability.IThirst;

import net.minecraft.entity.player.EntityPlayer;

public class ThirstHelper {

    public static IThirst getThirstData(EntityPlayer player)
    {
        return player.getCapability(Capabilities.THIRST, null);
    }
}
