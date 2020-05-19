package me.giverplay.modernalworld;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.giverplay.modernalworld.command.Command;
import me.giverplay.modernalworld.command.CommandManager;
import me.giverplay.modernalworld.manager.PlayerManager;

public class ModernalWorld extends JavaPlugin
{
	private static ModernalWorld instance;
	
	private HashMap<String, Command> commands = new HashMap<>();
	private HashMap<String, PlayerManager> players = new HashMap<>();
	
	public static ModernalWorld getInstance()
	{
		return instance;
	}
	
	@Override
	public void onEnable()
	{
		instance = this;
		
		print(" §aHabilitando plugin");
	}
	
	@Override
	public void onDisable()
	{
		print(getPrefix() + " §cDesabilitando plugin");
	}

	public Command getRegisteredCommand(String name)
	{
		if(!commands.containsKey(name))
		{
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
	
	public void addPlayerManager(String name)
	{
		players.put(name, new PlayerManager(name));
	}
	
	public PlayerManager getPlayerManager(String name)
	{
		return (players.containsKey(name) ? players.get(name) : null);
	}
	
	public void print(String msg)
	{
		Bukkit.getConsoleSender().sendMessage(msg);
	}
	
	public void runConsole(String command)
	{
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
	}
	
	public String getPrefix()
	{
		return "§a[ModernalWorld]§r";
	}
}
