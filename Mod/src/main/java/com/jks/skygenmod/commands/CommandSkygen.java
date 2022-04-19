package com.jks.skygenmod.commands;

import java.util.List;

import com.google.common.collect.Lists;
import com.jks.skygenmod.commands.util.GenerateSky;
import com.jks.skygenmod.util.Reference;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandSkygen extends CommandBase {

	private final List<String> aliases = Lists.newArrayList(Reference.MOD_ID, "sg", "skygen", "skyexpand", "skyextend");
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "skygen";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "skygen <distance>";
	}
	
	@Override
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		int minDistance = 64;
		int distance = minDistance;
		
		if (args.length > 0) {
			try {
				distance = Math.abs(Integer.parseInt(args[0]));
			} catch (NumberFormatException e)
			{
				sender.sendMessage(new TextComponentString(TextFormatting.RED + "Distance invalid"));
				return;
			}
			
			if (distance < 64) {
				sender.sendMessage(new TextComponentString(TextFormatting.RED + "Distance should be greater than 64 for best experience"));
			}
		}
		
		GenerateSky.generate(server.getWorld(0), distance);
		
	}

}
