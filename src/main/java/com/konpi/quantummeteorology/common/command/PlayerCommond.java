package com.konpi.quantummeteorology.common.command;

import java.util.Collections;
import java.util.List;

import com.konpi.quantummeteorology.api.capabilities.Capabilities;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class PlayerCommond extends CommandBase {

	@Override
	public String getName() {
		return "player";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.quantummeteorology.player.usege";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1) {
			throw new WrongUsageException("commands.quantummeteorology.player.usage");
		} else if ("temperature".equals(args[0])) {
			if ("set".equals(args[1])) {
				this.settemp(sender, args);
			} else if ("query".equals(args[1])) {
				this.gettemp(sender, args);
			}
		} else if ("thirst".equals(args[0])) {
			if ("set".equals(args[1])) {
				this.setthirst(sender, args);
			} else if ("query".equals(args[1])) {
				this.getthirst(sender, args);
			}
		}
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, "temperature", "thirst");
		} else if (args.length == 2 && "temperature".equals(args[0])) {
			return getListOfStringsMatchingLastWord(args, "set", "query");
		} else if (args.length == 2 && "thirst".equals(args[0])) {
			return getListOfStringsMatchingLastWord(args, "set", "query");
		}
		return Collections.<String>emptyList();
	}

	private void settemp(ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		if (player.hasCapability(Capabilities.TEMPERATURE, null) && Integer.valueOf(args[2]) != null) {
			player.getCapability(Capabilities.TEMPERATURE, null).setTemperature(Integer.valueOf(args[2]));
			sender.sendMessage(
					new TextComponentTranslation("commands.quantummeteorology.temperature.set.success", args[2]));
		} else {
			sender.sendMessage(
					new TextComponentTranslation("commands.quantummeteorology.temperature.set.fail", args[2]));
		}
	}

	private void setthirst(ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		if (player.hasCapability(Capabilities.THIRST, null) && Integer.valueOf(args[2]) != null) {
			player.getCapability(Capabilities.THIRST, null).setThirst(Integer.valueOf(args[2]));
			sender.sendMessage(new TextComponentTranslation("commands.quantummeteorology.thirst.set.success", args[2]));
		} else {
			sender.sendMessage(new TextComponentTranslation("commands.quantummeteorology.thirst.set.fail", args[2]));
		}
	}

	private void gettemp(ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		if (player.hasCapability(Capabilities.TEMPERATURE, null)) {
			String s = "" + player.getCapability(Capabilities.TEMPERATURE, null).getTemperature();
			player.sendMessage(new TextComponentTranslation("commands.quantummeteorology.temperature.query", s));
		}
	}

	private void getthirst(ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		if (player.hasCapability(Capabilities.THIRST, null)) {
			String s = "" + player.getCapability(Capabilities.THIRST, null).getThirst();
			player.sendMessage(new TextComponentTranslation("commands.quantummeteorology.thirst.query", s));
		}
	}

}
