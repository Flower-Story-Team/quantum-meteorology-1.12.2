package com.konpi.flower.item;

import com.konpi.flower.init.ItemRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.Objects;

/**
 * 碗里的食物，不能把碗给吃了
 */
public class ItemFoodInBowl extends ModFoodBase
{
    /**
     * @param registryName 注册名
     * @param amount       回复饥饿值
     * @param saturation   回复饱和度
     * @param isWolfFood   是否能给狼吃
     */
    public ItemFoodInBowl(String registryName, int amount, float saturation, boolean isWolfFood) {
        super(registryName, amount, saturation, isWolfFood);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));

            if (entityplayer instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
        }

        return new ItemStack(ItemRegistry.ItemHolder.BOWL);
    }

}
