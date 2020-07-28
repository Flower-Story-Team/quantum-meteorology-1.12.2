package com.konpi.quantummeteorology.common.init;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.potion.ModPotion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModPotions {

	public static Potion heat_stroke;

	public static void init() {
		heat_stroke = registerPotion("heat_stroke",
				new ModPotion(true, 0xFF0000, 0, 0).setPotionName("potion.heatstroke").registerPotionAttributeModifier(
						SharedMonsterAttributes.MOVEMENT_SPEED, "4107DE5E-7CE8-0000-940E-514C1F160890", -0.17D, 2));
	}

	public static Potion registerPotion(String name, Potion potion) {
		ResourceLocation location = new ResourceLocation(QuantumMeteorology.MODID, name);
		potion.setRegistryName(location);
		ForgeRegistries.POTIONS.register(potion);
		return potion;
	}

	public static PotionType registerPotionType(String name, PotionType potionType) {
		ResourceLocation location = new ResourceLocation(QuantumMeteorology.MODID, name);
		potionType.setRegistryName(location);
		ForgeRegistries.POTION_TYPES.register(potionType);
		return potionType;
	}
}
