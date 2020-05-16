package me.giverplay.modernalworld.command;

import org.bukkit.command.CommandSender;

import me.giverplay.modernalworld.command.CommandManager.CommandType;

public abstract class Command
	{
		private String name, usage;
		private CommandType type;
		
		public Command(String name, String usage, CommandType type)
		{
			this.name = name;
			this.type = type;
			this.usage = usage;
		}
		
		public String getName()
		{
			return this.name;
		}
		
		public String getUsage()
		{
			return "§cUtilize : " + this.usage;
		}
		
		public CommandType getType()
		{
			return this.type;
		}
		
		public String getPermission()
		{
			return "modernalworld.command." + this.name;
		}
		
		public abstract void execute(CommandSender sender, String[] args);
	}