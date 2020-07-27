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
	
	public void addCommand(ModernalCommand command)
	{
		if(executor == null)
		  executor = new ModernalCommandExecutor(modernal);
		
		commands.put(command.getName(), command);
		modernal.getCommand(command.getName()).setExecutor(executor);
	}
	
	private void registerCommands()
	{
		addCommand(new FlyCommand());
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
