package com.konpi.quantummeteorology.common.block.tileenty;

import javax.annotation.Nonnull;

import com.konpi.quantummeteorology.common.init.ModItems;
import com.konpi.quantummeteorology.common.init.ModItems.ItemHolder;
import com.konpi.quantummeteorology.common.inventory.QMContainer;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;


public class EntityCoalGenerator extends TileEntity implements ITickable
{
	public int cook = 0;
    public ItemStackHandler items = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            markDirty();
        }
    };
	public SlotItemHandler slot1 = new SlotItemHandler(items,0,80,50){
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem()==Items.DIAMOND;
        }
    };
	public SlotItemHandler slot2 = new SlotItemHandler(items,1,44,32){
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem()== ItemHolder.BOWL;
        }
    };
	public SlotItemHandler slot3 = new SlotItemHandler(items,2,116,32){
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return false;
        }
    };


	public void setRun(boolean run)
    {
    }
	
	@Override
    public void update()
    {
        World world = this.world;
        if(world!=null&&!world.isRemote){
            if(slot1.getHasStack()&&slot2.getHasStack()&&(!slot3.getHasStack()||slot3.getStack().getCount()<64))
            {
                if(cook<=0)setCookTime(QMContainer.MAX_TIME);
                
                updateCookTime();
                if(cook<=0){
                    //完成制作
                    slot1.getStack().shrink(1);
                    slot2.getStack().shrink(1);
                    if(slot3.getHasStack())slot3.getStack().grow(1);
                    else slot3.putStack(new ItemStack(ItemHolder.CORN));
                    clearCookTime();
                }
            }else{
                clearCookTime();
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
	    compound.setInteger("cookTime",cook);
	    compound.setTag("items",items.serializeNBT());
        return super.writeToNBT(compound);
    }
    public void setCookTime(int cookTime){
	    this.cook = cookTime;
	    this.markDirty();
    }
    public void clearCookTime(){
	    setCookTime(0);
    }
    public void updateCookTime(){
	    setCookTime(Math.max(0,cook-1));
    }
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.cook = compound.getInteger("cookTime");
        this.items.deserializeNBT(compound.getCompoundTag("items"));
    }
	
}
