package com.konpi.quantummeteorology.common.init;

import com.konpi.quantummeteorology.QuantumMeteorology;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * 所有的物品栏都放这个类里，用匿名内部类实例化
 */
@SuppressWarnings("all")
public class ModCreativeTabs
{
    //餐具
	public static final CreativeTabs TABLEWARE = new CreativeTabs(getLabel("tableware"))
	{
	    @Override
	    public ItemStack createIcon()
	    {
	        return new ItemStack(ModItems.ItemHolder.BOWL);
	    }
	};

	//食物
    public static final CreativeTabs FOOD = new CreativeTabs(getLabel("food"))
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.ItemHolder.CORN);
        }
    };

    //杂项
    public static final CreativeTabs MISC = new CreativeTabs(getLabel("misc"))
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.ItemHolder.RICE_SEED);
        }
    };

    public static String getLabel(String name)
    {
        return QuantumMeteorology.MODID + "." + name;
    }

}
