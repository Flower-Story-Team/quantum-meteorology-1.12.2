package com.konpi.flower.api.state;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public interface IPlayerState {

	public void update(EntityPlayer player, World world, Phase phase);
	
	public boolean hasChanged();
	public void onSendClientUpdate();

	public IMessage createUpdateMessage();
}
