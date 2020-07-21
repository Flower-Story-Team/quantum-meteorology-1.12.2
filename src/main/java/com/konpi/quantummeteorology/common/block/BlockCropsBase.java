package com.konpi.quantummeteorology.common.block;

import com.konpi.quantummeteorology.QuantumMeteorology;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

/**
 * 农作物的基础类
 * 抽象类，必须重写getSeed和getCrop方法
 * 为什么不用构造方法传参？因为构造方法执行时所有的模组物品引用都是null
 */
public abstract class BlockCropsBase extends BlockCrops {

    /**
     * @param registryName 注册名
     */
    public BlockCropsBase(String registryName) {
        this.setRegistryName(registryName);
        this.setTranslationKey(QuantumMeteorology.MODID + "." + registryName);
    }

    @Override
    public abstract Item getSeed();

    @Override
    public abstract Item getCrop();

}