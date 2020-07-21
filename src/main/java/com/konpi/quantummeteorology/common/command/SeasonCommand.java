package com.konpi.quantummeteorology.common.command;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.konpi.quantummeteorology.QuantumMeteorology;
import com.konpi.quantummeteorology.api.data.Month;
import com.konpi.quantummeteorology.common.config.CommonConfig;

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
		return "quantummeteorology.season";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.quantummeteorology.season.usage";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1) {
			throw new WrongUsageException("commands.quantummeteorology.season.usage");
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
			for (Month month : Month.values()) {
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
		for (int i = 0; i < Month.values().length; i++) {
			if (Month.values()[i].toString().toLowerCase().equals(args[1].toLowerCase())) {
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
			CommonConfig.general_config.starting_time = st;
			CommonConfig.onConfigChanged(new OnConfigChangedEvent(QuantumMeteorology.MODID, null, true, false));
			sender.sendMessage(new TextComponentTranslation("commands.quantummeteorology.season.set.success", args[1]));
		} else {
			sender.sendMessage(new TextComponentTranslation("commands.quantummeteorology.season.set.fail", args[1]));
		}
	}

	private void query(ICommandSender sender, String[] args) throws CommandException {
		World world = sender.getEntityWorld();
		String s = Month.getmonth(world.getWorldTime()).toString();
		sender.sendMessage(new TextComponentTranslation("commands.quantummeteorology.season.query", s));
	}

}
