package com.konpi.quantummeteorology.api.capabilities.thirst;

import javax.vecmath.Vector3d;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.api.data.FlowerDamageSource;
import com.konpi.quantummeteorology.api.data.IPlayerState;
import com.konpi.quantummeteorology.common.util.ylllutil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class ThirstHandler implements IPlayerState, IThirst {

	private int thirst;
	private int prethirst;
	private long time_1 = -1;
	private int time_2 = 0;

	@Override
	public void setThirst(int thirst) {
		if (thirst < 0 || thirst > 100) {
			QuantumMeteorology.logger.error("水分设置错误！范围为0~100 ");
			this.thirst = 50;
		}
		this.thirst = thirst;
	}

	@Override
	public int getThirst() {
		return this.thirst;
	}

	boolean b = false;

	@Override
	public void update(EntityPlayer player, World world, Phase phase) {
		if (player.isCreative())
			return;
		long time = world.getWorldTime();
		if (phase == Phase.END && time % 20 == 0 && b) {
			long costtime = 30;
			int temp = ylllutil.GetTemperature(world, player.getPosition());
			if (temp > 20) {
				while (temp > 24) {
					temp -= 5;
					costtime--;
				}
			} else if (temp < 20) {
				while (temp < 12) {
					temp += 10;
					costtime--;
				}
			}

			// 用来判断玩家速度，正常步行速度为0.22左右，跑步为0.28，跑跳为0.3+
			double velocity = new Vector3d(player.chasingPosX - player.prevChasingPosX, 0,
					player.chasingPosZ - player.prevChasingPosZ).length();
			if (velocity > 0.3) {
				costtime -= 10;
			} else if (velocity > 0.25) {
				costtime -= 5;
			}
			costtime = Math.max(costtime, 0);

			if (world.getDifficulty() != EnumDifficulty.PEACEFUL) {
				if (time_1 == -1) {
					time_1 = time;
				} else if ((time_1 + costtime * 20) <= world.getWorldTime()) {
					this.thirst = Math.max(thirst - 1, 0);
					time_1 = time;
				}
				if (this.thirst < 0) {
					player.attackEntityFrom(FlowerDamageSource.THIRSE, 5);
				} else if (this.thirst < 10) {
					player.attackEntityFrom(FlowerDamageSource.THIRSE, 1);
				}
			}
			b = false;
		} else {
			b = true;
		}
	}

	@Override
	public void BreakBlock(EntityPlayer player, World world) {
		if (player.isCreative())
			return;
		if (world.getDifficulty() != EnumDifficulty.PEACEFUL) {
			time_2 += 1;
			if (time_2 == 10) {
				this.thirst = Math.max(thirst - 1, 0);
			}
		}
	}

	@Override
	public boolean hasChanged() {
		return this.prethirst != this.thirst;
	}

	@Override
	public void onSendClientUpdate() {
		this.prethirst = this.thirst;
	}

}
