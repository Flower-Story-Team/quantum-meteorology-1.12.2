package com.konpi.flower.block;

import com.konpi.flower.Flower;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class BlockRegistry
{

    //没有subItem的普通方块
    public static final ArrayList<Block> simpleBlockList = new ArrayList<>();

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
        //event.getRegistry().register(...);
    }

    @ObjectHolder(Flower.MODID)
    public static class BlockHolder
    {

        //注册方块后这个会自动变成对应方块的引用
        //@ObjectHolder(registryName)
        //public static final Block NAME = null;

    }

}
