package com.konpi.flower.seasons;

import java.util.List;

import com.google.common.collect.Lists;
import com.konpi.flower.seasons.Season.SeasonState;
import com.konpi.flower.seasons.Handler.SeasonHandler;
import com.konpi.flower.seasons.savedata.SeasonSaveData;
import com.konpi.flower.seasons.savedata.SeasonTime;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class FlowerCommand extends CommandBase {

	@Override
	public String getName() {
		return "flower.season";
	}

	@Override
	public List getAliases() {
		return Lists.newArrayList("ss");
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.flower.season.usage";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1) {
			throw new WrongUsageException("commands.flower.season.usage");
		} else if ("setseason".equals(args[0])) {
			setSeason(sender, args);
		}
	}

	private void setSeason(ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		SeasonState newSeason = null;

		for (SeasonState season : SeasonState.VALUES) {
			if (season.toString().toLowerCase().equals(args[1].toLowerCase())) {
				newSeason = season;
				break;
			}
		}

		if (newSeason != null) {
			SeasonSaveData seasonData = SeasonHandler.getSeasonSavedData(player.world);
			seasonData.seasonCycleTicks = SeasonTime.ZERO.getSeasonStateDuration() * newSeason.ordinal();
			seasonData.markDirty();
			// SeasonHandler.sendSeasonUpdate(player.world);
			sender.sendMessage(new TextComponentTranslation("commands.flower.season.setseason.success", args[1]));
		} else {
			sender.sendMessage(new TextComponentTranslation("commands.flower.season.setseason.fail", args[1]));
		}
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, "setseason");
		}

		return null;
	}
}
