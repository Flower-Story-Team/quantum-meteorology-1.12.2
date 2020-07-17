package com.konpi.flower.common.command;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.konpi.flower.common.seasons.Season.SeasonState;
import com.konpi.flower.common.handler.season.SeasonHandler;
import com.konpi.flower.common.savedata.season.SeasonSaveData;
import com.konpi.flower.common.savedata.season.SeasonTime;
import com.konpi.flower.common.seasons.Season;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class SeasonCommand extends CommandBase {

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
		} else if ("set".equals(args[0])) {
			setSeason(sender, args);
		} else if ("query".equals(args[0])) {
			query(sender, args);
		}
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, "set", "query");
		} else if (args.length == 2 && "set".equals(args[0])) {
			String s[] = new String[12];
			int i = 0;
			for (SeasonState season : SeasonState.VALUES) {
				s[i] = season.toString().toLowerCase();
				i++;
			}
			return getListOfStringsMatchingLastWord(args, s);
		}

		return Collections.<String>emptyList();
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
			SeasonHandler.sendSeasonUpdate(player.world);
			sender.sendMessage(new TextComponentTranslation("commands.flower.season.set.success", args[1]));
		} else {
			sender.sendMessage(new TextComponentTranslation("commands.flower.season.set.fail", args[1]));
		}
	}

	private void query(ICommandSender sender, String[] args) throws CommandException {
		// TODO:热带季节？
		SeasonSaveData seasonData = SeasonHandler.getSeasonSavedData(sender.getEntityWorld());
		int i = (seasonData.seasonCycleTicks / SeasonTime.ZERO.getSeasonStateDuration())
				% Season.SeasonState.VALUES.length;
		String Season = SeasonState.VALUES[i].toString().toLowerCase();
		sender.sendMessage(new TextComponentTranslation("commands.flower.season.query", Season));
	}

}
