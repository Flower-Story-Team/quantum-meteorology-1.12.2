package com.konpi.flowerofhua.flower.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import java.util.Objects;

/**
 * ItemBlock的基础类
 */
public class ModItemBlockBase extends ItemBlock {

    public ModItemBlockBase(Block block) {
        super(block);
        this.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }

}
