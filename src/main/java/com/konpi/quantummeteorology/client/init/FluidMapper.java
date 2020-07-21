package com.konpi.quantummeteorology.client.init;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.common.init.ModFluid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class FluidMapper {
	@SubscribeEvent
	public static void map(ModelRegistryEvent event) {
		ModFluid.fluidList.forEach(FluidMapper::mapBlock);
	}

	private static void mapBlock(Fluid fluid) {
		BlockFluidBase block = (BlockFluidBase) fluid.getBlock();
		ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
			final ModelResourceLocation state = new ModelResourceLocation(FLUID_STATE_FILE, block.getFluid().getName());

			@Override
			@Nonnull
			protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
				return this.state;
			}
		});
	}

	static final ResourceLocation FLUID_STATE_FILE = new ResourceLocation(QuantumMeteorology.MODID, "fluid");
}
