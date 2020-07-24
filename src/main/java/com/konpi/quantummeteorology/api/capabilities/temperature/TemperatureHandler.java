package com.konpi.quantummeteorology.api.capabilities.temperature;

import javax.vecmath.Vector3d;

import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.api.capabilities.Capabilities;
import com.konpi.quantummeteorology.api.data.FlowerDamageSource;
import com.konpi.quantummeteorology.api.data.IPlayerState;
import com.konpi.quantummeteorology.api.data.MessageUpdateStat;
import com.konpi.quantummeteorology.common.util.miscutil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class TemperatureHandler implements IPlayerState, ITemperature {

	private float temp;
	private float pretemp;
	private boolean send;

	@Override
	public void setTemperature(int temperature) {
		if (temperature < -30 | temperature > 70) {
			QuantumMeteorology.logger.error("温度设置错误！范围为-30~70");
			this.temp = 20;
		}
		this.temp = temperature;
	}

	@Override
	public int getTemperature() {
		return (int) this.temp;
	}

	boolean b = false;

	@Override
	public void update(EntityPlayer player, World world, Phase phase) {
		if (player.isCreative() || player.isDead)
			return;
		if (phase == Phase.END && world.getWorldTime() % 20 == 0 && b) {
			int surround = miscutil.GetTemperature(world, player.getPosition());
			int thirst = player.getCapability(Capabilities.THIRST, null).getThirst();
			if (thirst < 60) {
				this.temp = temp + (surround - temp) / (thirst * 2 + 1);
			} else {
				this.temp = 20 + (surround - temp) * 4 / (thirst + 1);
			}

			double velocity = new Vector3d(player.chasingPosX - player.prevChasingPosX, player.motionY,
					player.chasingPosZ - player.prevChasingPosZ).length();
			if (velocity > 0.2) {
				this.temp += temp * 0.01F;
			} else {
				this.temp = temp * 0.99F;
			}

			// System.out.println("\n\nsu " + surround + " tem " + temp + " th " + thirst +
			// "\n");

			if (world.getDifficulty() != EnumDifficulty.PEACEFUL) {
				if (this.temp > 30) {
					player.attackEntityFrom(FlowerDamageSource.HEAT, 5);
					// TODO:幻觉，其他
				} else if (this.temp > 25) {
					player.attackEntityFrom(FlowerDamageSource.HEAT, 2);
				} else if (this.temp > 23) {
					if (this.send) {
						player.sendMessage(new TextComponentTranslation("quantummeteorology.mention.heat"));
						this.send = false;
					}
				} else if (this.temp < 10) {
					player.attackEntityFrom(FlowerDamageSource.COLD, 5);
					// TODO:减速？
				} else if (this.temp < 15) {
					player.attackEntityFrom(FlowerDamageSource.COLD, 2);
					// TODO：减速？
				} else if (this.temp < 17) {
					if (this.send) {
						player.sendMessage(new TextComponentTranslation("quantummeteorology.mention.cold"));
						this.send = false;
					}
				} else {
					this.send = true;
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
			this.temp += 0.2F;
		}
	}

	@Override
	public boolean hasChanged() {
		return this.temp != this.pretemp;
	}

	@Override
	public void onSendClientUpdate() {
		this.pretemp = this.temp;
	}

	@Override
	public void onjump() {
		this.temp += 0.2;
	}

	@Override
	public IMessage createUpdateMessage() {
		NBTTagCompound data = (NBTTagCompound) Capabilities.TEMPERATURE.getStorage().writeNBT(Capabilities.TEMPERATURE,
				this, null);
		return new MessageUpdateStat(Capabilities.TEMPERATURE, data);
	}
}
