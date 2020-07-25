package com.konpi.quantummeteorology.common.init;

import javax.annotation.Nullable;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.block.tileenty.EntityCoalGenerator;
import com.konpi.quantummeteorology.common.gui.QMGuiContainer;
import com.konpi.quantummeteorology.common.inventory.QMContainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ModGuiLoader implements IGuiHandler
{
	public static final int EngineerWorktable = 0;
    public static final int RobotInventory=1;
    
    
    
    public ModGuiLoader()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(QuantumMeteorology.instance,this);
    }
    
    
    @Nullable
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id){
            case 0:{
               return new QMContainer(player,(EntityCoalGenerator) getTileEntity(world, x, y, z));

            }
            case 1:{
           //    return new RobotContainer(player);
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new QMGuiContainer(new QMContainer(player,(EntityCoalGenerator) getTileEntity(world, x, y, z)));
    }
    public TileEntity getTileEntity(World world, int x, int y, int z) {
        return world.getTileEntity(new BlockPos(x, y, z));
    }

}
