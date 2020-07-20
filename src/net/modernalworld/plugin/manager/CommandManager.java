package net.modernalworld.plugin.manager;

import java.util.HashMap;

import net.modernalworld.plugin.ModernalWorld;
import net.modernalworld.plugin.command.ModernalCommand;
import net.modernalworld.plugin.command.ModernalCommandExecutor;
import net.modernalworld.plugin.command.commands.FlyCommand;

public class CommandManager
{
	private HashMap<String, ModernalCommand> commands = new HashMap<>();
	
	private ModernalCommandExecutor executor;
	private ModernalWorld modernal;
	
	public CommandManager(ModernalWorld modernal)
	{
		this.modernal = modernal;
		
		registerCommands();
	}
	
	public void addCommand(String name, ModernalCommand command)
	{
		if(executor == null)
		  executor = new ModernalCommandExecutor(modernal);
		
		commands.put(name, command);
		modernal.getCommand(name).setExecutor(executor);
	}
	
	private void registerCommands()
	{
		addCommand("fly", new FlyCommand());
	}
	
	public ModernalCommand getRegisteredCommand(String name)
	{
		if(!commands.containsKey(name))
		{
			return null;
		}
		
		return commands.get(name);
	}
}
