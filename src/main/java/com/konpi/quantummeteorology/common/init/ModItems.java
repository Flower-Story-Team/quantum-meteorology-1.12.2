package com.konpi.quantummeteorology.common.init;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.api.capabilities.temperature.ITemperature;
import com.konpi.quantummeteorology.common.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import java.util.ArrayList;

/**
 * 注册物品的类
 */
@Mod.EventBusSubscriber
public class ModItems {
	// 装普通物品的容器
	public static final ArrayList<Item> simpleItemList = new ArrayList<>();

	// 在这里往容器里添加物品, 加进去就自动注册了
	static {
		// 实例化普通物品
		QuantumMeteorology.logger.info("initializing normal items");

		// 种子
		simpleItemList.add(new ItemBase("rice_seed").setCreativeTab(ModCreativeTabs.MISC)); 		//水稻种子

		// 食材
		simpleItemList.add(new ItemFoodBase("chinese_cabbage", 1, 0.6F, false)); 	//白菜
		simpleItemList.add(new ItemFoodBase("tomato", 1, 0.6F, false));	 //番茄
		simpleItemList.add(new ItemSeedFoodBase("peanut", 1, 0.6F, ModBlocks.BlockHolder.PEANUT));	 //花生
		simpleItemList.add(new ItemBase("rice").setCreativeTab(ModCreativeTabs.MISC)); 	//大米
		simpleItemList.add(new ItemFoodBase("corn", 1, 0.6F, false)); 	//玉米
		simpleItemList.add(new ItemFoodBase("eggplant", 1, 0.6F, false)); 	//茄子
		simpleItemList.add(new ItemFoodBase("cabbage", 1, 0.6F, false)); 	//卷心菜
		simpleItemList.add(new ItemFoodBase("scallion", 1, 0.6F, false));	 //葱
		simpleItemList.add(new ItemFoodBase("ginger", 1, 0.6F, false)); 	//生姜
		simpleItemList.add(new ItemFoodBase("cauliflower", 1, 0.6F, false));	 //花菜
		simpleItemList.add(new ItemFoodBase("chili", 1, 0.6F, false)); 				//辣椒
		simpleItemList.add(new ItemFoodBase("pepper", 1, 0.6F, false));			 //花椒
		simpleItemList.add(new ItemFoodBase("sweet_pepper", 1, 0.6F, false));		 //甜椒
		simpleItemList.add(new ItemFoodBase("onion", 1, 0.6F, false)); 			//洋葱
		simpleItemList.add(new ItemFoodBase("lentil", 1, 0.6F, false)); 		//扁豆
		simpleItemList.add(new ItemFoodBase("garlic", 1, 0.6F, false)); 		//大蒜
		
		simpleItemList.add(new ItemFoodBase("cuke", 1, 0.6F, false)); 			//黄瓜
		simpleItemList.add(new ItemFoodBase("filteredwater",0,0.3F,false)); //过滤水

		// 熟食
		simpleItemList.add(new ItemFoodInBowl("steamed_rice", 1, 0.6F, false)); 	//米饭
		simpleItemList.add(new ItemFoodBase("popcorn",3,0.6F,false)); 				//爆米花

		// 餐具
		simpleItemList.add(new ItemTablewareBase("bowl").setMaxStackSize(16)); 		//碗

		//
		simpleItemList.add(new ItemBase("thermometer").setCreativeTab(ModCreativeTabs.MISC)); //温度计 ,占时没啥用
		simpleItemList.add(new ItemBase("ironplate").setCreativeTab(ModCreativeTabs.MISC));//
		simpleItemList.add(new ItemBase("goldplate").setCreativeTab(ModCreativeTabs.MISC));//
		simpleItemList.add(new ItemBase("aluminumplate").setCreativeTab(ModCreativeTabs.MISC));//
		simpleItemList.add(new ItemBase("copperplate").setCreativeTab(ModCreativeTabs.MISC));//
		simpleItemList.add(new ItemBase("tinplate").setCreativeTab(ModCreativeTabs.MISC));//
		simpleItemList.add(new ItemBase("leadplate").setCreativeTab(ModCreativeTabs.MISC));//

		
		
		
		// 实例化方块物品
		QuantumMeteorology.logger.info("initializing block items");
		for (Block b : ModBlocks.simpleBlockList)
		{
			simpleItemList.add(new ItemBlockBase(b));
		}

	}

	/** 注册Item */
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		// 注册普通物品
		QuantumMeteorology.logger.info("registering items");
		event.getRegistry().registerAll(simpleItemList.toArray(new Item[0]));
	}

	/** 注册物品后这个会自动变成对应物品的引用 */
	@ObjectHolder(QuantumMeteorology.MODID)
	public static class ItemHolder
	{
		// 种子
		@ObjectHolder("rice_seed") 		//水稻种子
		public static final Item RICE_SEED = null;
		
		// 餐具
		@ObjectHolder("bowl") 			//碗
		public static final Item BOWL = null;

		//物品
		@ObjectHolder("thermometer") 			//温度计
		public static final Item THERMOMETER = null;

		@ObjectHolder("ironplate")     //铁板
		public static final Item PLATEIRON = null;

		@ObjectHolder("goldplate")   //金板
		public static final Item PLATEGOLD = null;

		@ObjectHolder("copperplate")   //铜板
		public static final Item PLATECOPPER = null;

		@ObjectHolder("tinplate") //
		public static final Item PLATETIN = null;

		@ObjectHolder("aluminumplate") //
		public static final Item PLATEALUMINUM = null;

		@ObjectHolder("leadplate") //
		public static final Item PLATELEAD = null;




		//熟食
		@ObjectHolder("steamed_rice")		 //米饭
		public static final Item STEAMED_RICE = null;

		@ObjectHolder("popcorn") 		//爆米花
		public static final Item POPCORN = null;
		
		
		//食材
		@ObjectHolder("chinese_cabbage") //白菜
		public static final Item CHINESE_CABBAGE = null;

		@ObjectHolder("tomato") //番茄
		public static final Item TOMATO = null;

		@ObjectHolder("peanut") //花生
		public static final Item PEANUT = null;

		@ObjectHolder("rice") //大米
		public static final Item RICE = null;

		@ObjectHolder("corn") //玉米
		public static final Item CORN = null;

		@ObjectHolder("eggplant") //茄子
		public static final Item EGGPLANT = null;

		@ObjectHolder("cabbage") //卷心菜
		public static final Item CABBAGE = null;

		@ObjectHolder("scallion") //葱
		public static final Item SCALLION = null;

		@ObjectHolder("ginger") //姜
		public static final Item GINGER = null;

		@ObjectHolder("cauliflower") //花菜
		public static final Item CAULIFLOWER = null;

		@ObjectHolder("chili") //辣椒
		public static final Item CHILI = null;

		@ObjectHolder("pepper") //花椒
		public static final Item PEPPER = null;

		@ObjectHolder("sweet_pepper") //甜椒
		public static final Item SWEET_PEPPER = null;

		@ObjectHolder("onion") //洋葱
		public static final Item ONION = null;

		@ObjectHolder("lentil") //扁豆
		public static final Item LENTIL = null;

		@ObjectHolder("garlic") //大蒜
		public static final Item GARLIC = null;

		@ObjectHolder("cuke") //黄瓜
		public static final Item CUKE = null;
		
		@ObjectHolder("filteredwater") //过滤水
		public static final Item FILTEREDWATER = null;

	}
}

