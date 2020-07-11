package com.konpi.flowerofhua.flower.init;

import com.konpi.flowerofhua.flower.Flower;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Flower.MODID)
@Mod.EventBusSubscriber
public class BlockRegistry {

    /**
     * 注册Block
     */
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        Flower.logger.info("registering blocks");
        //event.getRegistry().register(...);
    }

    @SubscribeEvent
    public static void onItemBlockRegister(RegistryEvent.Register<Item> event) {
        Flower.logger.info("registering block items");
    }

}
