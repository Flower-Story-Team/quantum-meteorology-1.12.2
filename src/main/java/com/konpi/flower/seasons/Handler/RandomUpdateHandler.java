package com.konpi.flower.seasons.Handler;

import java.util.Iterator;

import com.konpi.flower.seasons.Season;
import com.konpi.flower.seasons.Season.SeasonState;
import com.konpi.flower.seasons.intefaces.SeasonHelper;
import com.konpi.flower.seasons.savedata.Hooks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

/**
 * according to sereneseason
 *
 */
public class RandomUpdateHandler {
	// Randomly melt ice and snow when it isn't winter
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		if (event.phase == Phase.END && event.side == Side.SERVER) {

			SeasonState SeasonState = SeasonHelper.getSeasonState(event.world).getSeasonState();
			Season season = SeasonState.getSeason();

			if (season == Season.WINTER) {
				if (event.world.getWorldInfo().isThundering()) {
					event.world.getWorldInfo().setThundering(false);

				}
				if (!event.world.getWorldInfo().isRaining() && event.world.getWorldInfo().getRainTime() > 36000) {
					event.world.getWorldInfo().setRainTime(event.world.rand.nextInt(24000) + 12000);
				}
			} else {
				if (season == Season.SPRING) {
					if (!event.world.getWorldInfo().isRaining() && event.world.getWorldInfo().getRainTime() > 96000) {
						event.world.getWorldInfo().setRainTime(event.world.rand.nextInt(84000) + 12000);
					}
				} else if (season == Season.SUMMER) {
					if (!event.world.getWorldInfo().isThundering()
							&& event.world.getWorldInfo().getThunderTime() > 36000) {
						event.world.getWorldInfo().setThunderTime(event.world.rand.nextInt(24000) + 12000);
					}
				}

				if (event.world.provider.getDimension() == 0) {
					WorldServer world = (WorldServer) event.world;
					for (Iterator<Chunk> iterator = world.getPersistentChunkIterable(
							world.getPlayerChunkMap().getChunkIterator()); iterator.hasNext();) {
						Chunk chunk = iterator.next();
						int x = chunk.x << 4;
						int z = chunk.z << 4;

						int rand;
						switch (SeasonState) {
						case EARLY_SPRING:
							rand = 16;
							break;
						case MID_SPRING:
							rand = 12;
							break;
						case LATE_SPRING:
							rand = 8;
							break;
						default:
							rand = 4;
							break;
						}

						if (world.rand.nextInt(rand) == 0) {
							// world.updateLCG = world.updateLCG * 3 + 1013904223;// TODO:updateLCG是protcet
							// int randOffset = world.updateLCG >> 2;
							int randOffset = world.rand.nextInt();//上面那个的替代，不知道行不行
							
							BlockPos pos = world.getPrecipitationHeight(
									new BlockPos(x + (randOffset & 15), 0, z + (randOffset >> 8 & 15)));
							Biome biome = world.getBiome(pos);

							boolean first = true;
							for (int y = pos.getY(); y >= 0; y--) {
								Block block = chunk.getBlockState(pos.getX(), y, pos.getZ()).getBlock();

								if (block == Blocks.SNOW_LAYER) {
									pos = new BlockPos(pos.getX(), y, pos.getZ());
									if (Hooks.getFloatTemperature(world, biome, pos) >= 0.15F) {
										world.setBlockToAir(pos);
										break;
									}
								}

								if (!first) {
									if (block == Blocks.ICE) {
										pos = new BlockPos(pos.getX(), y, pos.getZ());
										if (Hooks.getFloatTemperature(world, biome, pos) >= 0.15F) {
											//也是一个替代
											world.setBlockState(pos, Blocks.WATER.getDefaultState());
											// ((BlockIce) Blocks.ICE).turnIntoWater(world, pos);
											break;
										}
									}
								} else
									first = false;
							}
						}
					}
				}
			}
		}
	}
}
