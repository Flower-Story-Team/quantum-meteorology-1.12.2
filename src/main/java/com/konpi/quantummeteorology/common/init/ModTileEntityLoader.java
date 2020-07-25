package com.konpi.quantummeteorology.common.init;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.block.tileenty.EntityCoalGenerator;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntityLoader
{
    public ModTileEntityLoader()
    {
        //registerTileEntity(TileEntityMiningMachine.class,"mining_machine");
        //registerTileEntity(TileEntityCoreBlock.class,"core_block");
        registerTileEntity(EntityCoalGenerator.class,"log_xz");
    }
    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass,String id)
    {
        GameRegistry.registerTileEntity(tileEntityClass, QuantumMeteorology.MODID+":"+id);
    }
}
