package me.giverplay.modernalworld;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import me.giverplay.modernalworld.command.Command;
import me.giverplay.modernalworld.command.CommandManager;

public class ModernalWorld extends JavaPlugin
{
	private static ModernalWorld instance;
	
	private HashMap<String, Command> commands = new HashMap<>();
	
	public static ModernalWorld getInstance()
	{
		return instance;
	}
	
	@Override
	public void onEnable()
	{
		instance = this;
	}
	
	@Override
	public void onDisable()
	{
		
	}

	public Command getRegisteredCommand(String name)
	{
		if(!commands.containsKey(name)){
			return null;
		}
		
		return commands.get(name);
	}

	public void addCommand(String name, Command command)
	{
		commands.put(name, command);
	}
	
	public void removeCommand(String name)
	{
		commands.remove(name);
	}
	
	public void registerCommands(JavaPlugin plugin)
	{
		CommandManager manager = new CommandManager(this);
		
		for(String cmd : commands.keySet())
		{
			plugin.getCommand(cmd).setExecutor(manager);
		}
	}
}
