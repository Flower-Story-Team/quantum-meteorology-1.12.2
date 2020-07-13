package com.konpi.flower.init;

import com.konpi.flower.Flower;
import com.konpi.flower.item.ModFoodBase;
import com.konpi.flower.item.ModItemBase;
import com.konpi.flower.item.ModTablewareBase;
import com.konpi.flower.item.ModItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class ItemRegistry
{
	//一些普通的物品，它们只有一个模型.
    public static final ArrayList<Item> simpleItemList = new ArrayList<>();

    static
    {
        //向一些固定类型的集合里添加一些东西
    	Flower.logger.info("initializing normal items");
        
        
        simpleItemList.add(new ModItemBase("rice_seed"));	//水稻种子
        
        simpleItemList.add(new ModTablewareBase("bowl"));	//碗 物品堆叠是1个
        
        //基础食物
        simpleItemList.add(new ModFoodBase("rice", 1, 1, false, 1));	//大米
        
        simpleItemList.add(new ModFoodBase("pepper_chili", 1, 1, false, 64)); //辣椒
        
        simpleItemList.add(new ModFoodBase("cabbage", 1, 1, false, 64)); //白菜

        simpleItemList.add(new ModFoodBase("corn", 1, 1, false, 64)); //玉米
    }
    
    /**注册Item*/
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        //实例化方块物品
        Flower.logger.info("initializing block items");
        for (Block b : BlockRegistry.simpleBlockList)
        {
            event.getRegistry().register(new ModItemBlockBase(b));
        }

        //注册普通物品
        Flower.logger.info("registering items");
        
        event.getRegistry().registerAll(simpleItemList.toArray(new Item[0]));
        event.getRegistry().registerAll(simpleItemList.toArray(new Item[1]));
        event.getRegistry().registerAll(simpleItemList.toArray(new Item[2]));
        event.getRegistry().registerAll(simpleItemList.toArray(new Item[3]));
        event.getRegistry().registerAll(simpleItemList.toArray(new Item[4]));
        event.getRegistry().registerAll(simpleItemList.toArray(new Item[5]));

    }
    
    
    /**注册物品后这个会自动变成对应物品的引用*/
    @ObjectHolder(Flower.MODID)
    public static class ItemHolder
    {
    	//水稻种子
    	@ObjectHolder("rice_seed")
        public static final Item RICE_SEED = null;
    	
    	//大米
        @ObjectHolder("rice")
        public static final Item RICE = null;
        
        //碗
        @ObjectHolder("bowl")
        public static final Item BOWL = null;
        
        //辣椒
        @ObjectHolder("pepper_chili")
        public static final Item PEPPER_CHILI = null;
        
        //白菜
        @ObjectHolder("cabbage")
        public static final Item CABBAGE = null;
        
        //玉米
        @ObjectHolder("corn")
        public static final Item CORN = null;
    }

}
