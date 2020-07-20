package com.konpi.quantummeteorology.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import java.util.Objects;

/**
 * ItemBlock的基础类
 * 将ItemBlock的注册名设置为其对应方块的注册名
 */
public class ItemBlockBase extends ItemBlock
{

    public ItemBlockBase(Block block)
    {
        super(block);
        this.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }

}
