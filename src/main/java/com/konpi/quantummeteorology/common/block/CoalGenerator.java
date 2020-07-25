package com.konpi.quantummeteorology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.init.ModCreativeTabs;


public class CoalGenerator extends BlockContainer
{
	//燃煤发电机(基础)
	//Coal Generator
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	
	public CoalGenerator()
	{
		super(Material.ROCK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		
		this.setTranslationKey(QuantumMeteorology.MODID + "." + "coal_generator");
		this.setRegistryName("coal_generator");		//coal_generator
		this.setCreativeTab(ModCreativeTabs.MISC);
	}
	
	
	/*
	@Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
        	TileEntityMiningMachine tile = (TileEntityMiningMachine) worldIn.getTileEntity(pos);
            if (tile != null)
            {
                tile.DeleteMiningTube();
            }
        }
    }
	*/
	
	
	

	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}


	/*--------------------------------------------------------------------------*/
    /**
     * 方块自动更新。。。
     *
     *
     * */
	
	
	
	

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }
    
    /*
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityMiningMachine();
    }
    */
    
    
    /*--------------------------------------------------------------------------*/






    /*--------------------------------------------------------------------------*/
   /*
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityFurnace();
    }
*/


    /**
     * 从传递的blockstate返回具有给定旋转的blockstate。如果不适用，则返回通过的封锁状态。
     */

    @Override   //ok --- 每个方块里有！ 不要删除
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    /**
     * 从传递的blockstate返回具有给定旋转的blockstate。如果不适用，则返回通过的封锁状态。
     */

    @Override   //ok --- 每个方块里有！ 不要删除
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    /**
     * 调用的呈现函数的类型。混合的tesr模型和静态模型，只有tesr的MODELBLOCK_ANIMATED，
     * 液体为香草液体，不可见，跳过所有渲染
     */

    // OK ！！！不要删除
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }





    /**
     *在实际设置块之前由ItemBlocks调用，以允许对
     *伊布洛克状态
     */
    @Override   //ok 不要删除
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    /**
     *将给定的元数据转换为此块的块状态
     */
    @Override   //ok !
    public IBlockState getStateFromMeta(int meta)
    {
       return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    /**
     *将块状态转换为正确的元数据值
     */
    @Override   //ok +1
    public int getMetaFromState(IBlockState state)	//从状态获取元
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    /*--------------------------------------------------------------------------------------*/
    @Override   //ok +1
    protected BlockStateContainer createBlockState()	//块状态容器
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
    /*--------------------------------------------------------------------------------------*/


    
    
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		// TODO 自动生成的方法存根
		return null;
	}



   /*--------------------------------------------------------------------------*/

    /**
	*获取查询面在给定位置和状态下的几何图形。这是用来决定
	*按钮可以放置在面上，或者玻璃面板如何与面连接，等等。
	*<p>
	*常见的值是{@code SOLID}，这是默认值，还有{@code UNDEFINED}，它表示
	*与其他描述不符，通常会导致其他事物与面部不相连。
	*
	*@返回给定表面形式的近似值
	*/
    /*
	@Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.SOLID;
    }
*/
	
	
}
