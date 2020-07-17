package com.konpi.flower.common.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Events {
    //TODO 未完成
    public static final EventBus EVENT_BUS = new EventBus();
    private EntityPlayer ep;
    private PlayerAttributesManager ms;
    public Events() {
        MinecraftForge.EVENT_BUS.register(this);
        Events.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void satietyBuffer() {
        if (ep.getFoodStats().getSaturationLevel() >= 4 & ep.getFoodStats().getSaturationLevel() < 7) {
        }
        else if (ep.getFoodStats().getSaturationLevel() >= 18 & ep.getFoodStats().getSaturationLevel() < 20) {
            ep.getHeldItemMainhand().getItem().setMaxDamage((int) ((float)ep.getHeldItemMainhand().getMaxDamage() * 2));
        }
        else if (ep.getFoodStats().getSaturationLevel() >= 20) {
            ep.getHeldItemMainhand().getItem().setMaxDamage((int) ((float)ep.getHeldItemMainhand().getMaxDamage() * 3));
            ms.setMoveSpeed(2);
        }
    }
}
