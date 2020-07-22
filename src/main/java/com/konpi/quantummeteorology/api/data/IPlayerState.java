package com.konpi.quantummeteorology.api.data;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public interface IPlayerState {

	public void update(EntityPlayer player, World world, Phase phase);

	public void BreakBlock(EntityPlayer player, World world);

	public boolean hasChanged();

	public void onSendClientUpdate();

	public void onjump();

	public IMessage createUpdateMessage();
}
