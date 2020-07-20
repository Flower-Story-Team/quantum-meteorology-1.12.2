package com.konpi.quantummeteorology.common.util;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerAttributesManager {
    //TODO 未完成
    private EntityPlayer ep;
    public void setMoveSpeed(float speed) {
        if ((ep.moveForward == 0.0D) && (ep.moveStrafing == 0.0D)) {
            ep.motionX = 0;
            ep.motionZ = 0;
        }
        else {
            if (ep.moveForward != 0.0D) {
                if (ep.moveStrafing > 0.0D) {
                    ep.rotationYaw += (ep.moveForward > 0.0D ? -45 : 45);
                }
                else if (ep.moveStrafing < 0.0D) {
                    ep.rotationYaw += (ep.moveForward > 0.0D ? 45 : -45);
                }
                ep.moveStrafing = (float) 0.0D;
                if (ep.moveForward > 0.0D) {
                    ep.moveForward = 1;
                }
                else if (ep.moveForward < 0.0D) {
                    ep.moveForward = -1;
                }
            }
            ep.motionX = ep.moveForward * speed * Math.cos(Math.toRadians(ep.rotationYaw + 90.0F)) + ep.moveStrafing * speed * Math.sin(Math.toRadians(ep.rotationYaw + 90.0F));
            ep.motionZ = ep.moveForward * speed * Math.sin(Math.toRadians(ep.rotationYaw + 90.0F)) - ep.moveStrafing * speed * Math.cos(Math.toRadians(ep.rotationYaw + 90.0F));
        }
    }
}
