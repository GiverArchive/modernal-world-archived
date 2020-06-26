package net.modernalworld.plugin.command;

import org.bukkit.command.CommandSender;

public abstract class ModernalCommand
{
	private String name, usage;
	private CommandType type;
	
	public ModernalCommand(String name, String usage, CommandType type)
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
		return "§cUtilize: /" + this.usage;
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