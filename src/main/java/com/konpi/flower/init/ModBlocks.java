package com.konpi.flower.init;

import com.konpi.flower.Flower;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import java.util.ArrayList;

/**
 * 注册方块的类
 */
@Mod.EventBusSubscriber
public class ModBlocks
{

    //有对应ItemBlock但是没有subItem的普通方块
    public static final ArrayList<Block> simpleBlockList = new ArrayList<>();
    //无对应ItemBlock的方块，例如农作物
    public static final ArrayList<Block> noItemBlockList = new ArrayList<>();

    static
    {
        //向集合中添加对象
    }

    /**
     * 注册Block
     */
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        Flower.logger.info("registering blocks");
        event.getRegistry().registerAll(simpleBlockList.toArray(new Block[0]));
        event.getRegistry().registerAll(noItemBlockList.toArray(new Block[0]));
    }

    @ObjectHolder(Flower.MODID)
    public static class BlockHolder
    {

        //注册方块后这个会自动变成对应方块的引用
        //@ObjectHolder(registryName)
        //public static final Block NAME = null;

    }

}