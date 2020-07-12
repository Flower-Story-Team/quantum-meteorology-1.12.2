package com.konpi.flower.client.init;

import com.konpi.flower.init.ItemRegistry;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class ModelRegistry
{

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event)
    {
        //只有一个模型的物品
        for (Item i : ItemRegistry.simpleItemList)
        {
            registerModel(i);
        }
    }

    /**
     * 注册一般物品的模型
     */
    private static void registerModel(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()),
                        "inventory"));
    }

}
