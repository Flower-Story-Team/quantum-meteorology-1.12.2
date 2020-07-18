package com.kongpi.flower.common.command;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.kongpi.flower.Flower;
import com.kongpi.flower.api.Month;
import com.kongpi.flower.common.config.ClientConfig;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;

public class SeasonCommand extends CommandBase {

	@Override
	public String getName() {
		return "flower.season";
	}

	@Override
	public List getAliases() {
		return Lists.newArrayList("fs");
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
			for (Month month : Month.VALUES) {
				s[i] = month.toString().toLowerCase();
				i++;
			}
			return getListOfStringsMatchingLastWord(args, s);
		}
		return Collections.<String>emptyList();
	}

	private void setSeason(ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		int t = -1;
		for (int i = 0; i < Month.VALUES.length; i++) {
			if (Month.VALUES[i].toString().toLowerCase().equals(args[1].toLowerCase())) {
				t = i;
				break;
			}
		}
		if (t != -1) {
			// 把这个作为一个补充的时间
			// 补时 = 季节时间 - 世界时间
			int st = (int) (t * Month.getMonthDuration() - player.getEntityWorld().getWorldTime());
			int y = Month.getYearDuration();
			// 调整范围
			while (st < 0) {
				st += y;
			}
			while (st > y) {
				st -= y;
			}
			ClientConfig.general_config.starting_time = st;
			ClientConfig.onConfigChanged(new OnConfigChangedEvent(Flower.MODID, null, true, false));
			sender.sendMessage(new TextComponentTranslation("commands.flower.season.set.success", args[1]));
		} else {
			sender.sendMessage(new TextComponentTranslation("commands.flower.season.set.fail", args[1]));
		}
	}

	private void query(ICommandSender sender, String[] args) throws CommandException {
		World world = sender.getEntityWorld();
		String s = Month.getmonth(world.getWorldTime()).toString();
		sender.sendMessage(new TextComponentTranslation("commands.flower.season.query", s));
	}

}
