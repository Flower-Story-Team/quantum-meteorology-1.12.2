package com.konpi.flower.item.creativetab;

import com.konpi.flower.Flower;
import com.konpi.flower.init.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * 所有的物品栏都放这个类里，用匿名内部类实例化
 */
@SuppressWarnings("all")
public class ModCreativeTabs
{
	public static final CreativeTabs TABLEWARE = new CreativeTabs(getLabel("tableware"))
	{
	    @Override
	    public ItemStack createIcon()
	    {
	        return new ItemStack(ItemRegistry.ItemHolder.BOWL);
	    }
	};
	
    public static final CreativeTabs FOOD = new CreativeTabs(getLabel("food"))
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ItemRegistry.ItemHolder.CORN);
        }
    };

    public static final CreativeTabs MISC = new CreativeTabs(getLabel("misc"))
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ItemRegistry.ItemHolder.RICE_SEED);
        }
    };

    public static String getLabel(String name)
    {
        return Flower.MODID + "." + name;
    }
    
    

}
