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
        
    	//基础种子
    	simpleItemList.add(new ModItemBase("rice_seed", 64)); //水稻种子
    	
    	simpleItemList.add(new ModItemBase("rice", 64)); //大米
    	
    	//基础餐具
        simpleItemList.add(new ModTablewareBase("bowl", 64)); //碗
        
        
        
        
        //基础食物
        simpleItemList.add(new ModFoodBase("steamed_rice", 1, 1, 1, false)); //米饭
        
        
        //蔬菜
        simpleItemList.add(new ModFoodBase("pepper_chili", 64, 1, 1, false)); //辣椒
        simpleItemList.add(new ModFoodBase("cabbage", 64, 1, 1, false)); //白菜
        simpleItemList.add(new ModFoodBase("corn", 64, 1, 1, false)); //玉米
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
        
    }
    
    
    /**注册物品后这个会自动变成对应物品的引用*/
    @ObjectHolder(Flower.MODID)
    public static class ItemHolder
    {
    	
    	@ObjectHolder("rice_seed")	//水稻种子
        public static final Item RICE_SEED = null;
    	
    	
        @ObjectHolder("rice")	//大米
        public static final Item RICE = null;
        
        
        @ObjectHolder("bowl")	//碗
        public static final Item BOWL = null;
        
        
        @ObjectHolder("pepper_chili")	//辣椒
        public static final Item PEPPER_CHILI = null;
        
        
        @ObjectHolder("cabbage")	//白菜
        public static final Item CABBAGE = null;
        
        
        @ObjectHolder("corn")	//玉米
        public static final Item CORN = null;
        
        
        @ObjectHolder("steamed_rice")	//米饭
        public static final Item STEAMED_RICE = null;
    }

}
