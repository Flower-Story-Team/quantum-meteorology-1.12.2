package com.konpi.quantummeteorology.common.capabilities.temperature;
/*

*下面这些仅供参考 ，先做框架
*虚无世界的代码，参考一下 可以修改player datamanager 达到效果
* 温度 为摄氏度
* 100 沸腾（在世界中无法达到这个温度），在熔炉中的水温有100，参考不建议加入这条功能
* 80 暂无说明
* 60 在夏天的沙漠中是这个温度 会导致死亡（后期可以加入死亡生物）
* 50 同上
* 40 发烧了, 每秒掉2滴血（也就是一格），在夏天的太阳下工作会导致发烧 （达到这个温度）
* 30 正常
* 20正常
* 10有点冷
* 0 如果没有保暖设备情况下会掉血 每秒2滴（一格）
* -15 暂无
* -30 暂无
* -50 在特殊的工具中达到这个温度
*
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FlowerPlayerProvider implements ICapabilitySerializable<NBTTagCompound> {
@CapabilityInject(CapabilityBasePlayer.class)
public static Capability<CapabilityBasePlayer> ADVENT_PLAYER = null;
private CapabilityBasePlayer instance = ADVENT_PLAYER.getDefaultInstance();

public FlowerPlayerProvider(EntityPlayer pl) {
        if (instance != null)
        instance.init(pl);
        }

@Override
public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == ADVENT_PLAYER;
        }

@Nullable
@Override
public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == ADVENT_PLAYER ? ADVENT_PLAYER.<T>cast(instance) : null;
        }

@Override
public NBTTagCompound serializeNBT() {
        return instance.getPlayerData().saveToNBT();
        }

@Override
public void deserializeNBT(NBTTagCompound nbt) {
        instance.getPlayerData().loadFromNBT(nbt);
        }
        }

 */