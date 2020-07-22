package com.konpi.quantummeteorology.common.block;



import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.init.ModCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CoalGenerator extends Block
{
	//燃煤发电机(基础)
	//Coal Generator

	//Coal_Generator
	
	public CoalGenerator()
	{
		super(Material.ROCK);
		this.setTranslationKey(QuantumMeteorology.MODID + "." + "test");
		
		
		this.setRegistryName("coal_generator");		//coal_generator
		this.setCreativeTab(ModCreativeTabs.MISC);
		
		
		
		/*
		 *	this.setRegistryName(registryName);
        	this.setTranslationKey(QuantumMeteorology.MODID + "." + registryName);
        	this.setCreativeTab(ModCreativeTabs.FOOD);
        */
	}
	
}
