package com.konpi.flower.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import java.util.Objects;

/**
 * ItemBlock的基础类
 * 将ItemBlock的注册名设置为其对应方块的注册名
 */
public class ModItemBlockBase extends ItemBlock
{

    public ModItemBlockBase(Block block)
    {
        super(block);
        this.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }

}
