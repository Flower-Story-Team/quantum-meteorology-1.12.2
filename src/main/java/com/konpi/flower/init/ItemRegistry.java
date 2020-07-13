package com.konpi.flower.init;

import com.konpi.flower.Flower;
import com.konpi.flower.item.*;
import com.konpi.flower.item.creativetab.ModCreativeTabs;
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
	//装普通物品的容器
    public static final ArrayList<Item> simpleItemList = new ArrayList<>();

    //在这里往容器里添加物品, 加进去就自动注册了
    static
    {
        //实例化普通物品
    	Flower.logger.info("initializing normal items");
        
    	
    	//种子
        simpleItemList.add(new ModItemBase("rice_seed").setCreativeTab(ModCreativeTabs.MISC)); //水稻种子

        
        //食材
    	simpleItemList.add(new ModItemBase("rice").setCreativeTab(ModCreativeTabs.MISC)); //生米
        simpleItemList.add(new ModFoodBase("pepper_chili", 1, 0.6F, false)); //辣椒
        simpleItemList.add(new ModFoodBase("cabbage", 1, 0.6F, false)); //白菜
        simpleItemList.add(new ModFoodBase("tomato", 1, 0.6F, false)); //番茄
        simpleItemList.add(new ModFoodBase("corn", 1, 0.6F, false)); //玉米
        simpleItemList.add(new ModFoodBase("onion", 1, 0.5F, false)); //大葱

        
        //熟食
        simpleItemList.add(new ItemFoodInBowl("steamed_rice", 1, 0.6F, false)); //米饭
    	
        
    	//餐具
        simpleItemList.add(new ModTablewareBase("bowl").setMaxStackSize(16)); //碗

        
        //实例化方块物品
        Flower.logger.info("initializing block items");
        for (Block b : BlockRegistry.simpleBlockList)
        {
            simpleItemList.add(new ModItemBlockBase(b));
        }

    }
    
    /**注册Item*/
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
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
        
        @ObjectHolder("tomato")		//番茄
        public static final Item TOMATO = null;

        @ObjectHolder("onion")      //大葱
        public static final Item ONION = null;

        @ObjectHolder("corn")	//玉米
        public static final Item CORN = null;

        @ObjectHolder("steamed_rice")	//米饭
        public static final Item STEAMED_RICE = null;
    }

}
