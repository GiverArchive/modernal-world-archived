package me.giverplay.modernalworld;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import me.giverplay.modernalworld.command.Command;
import me.giverplay.modernalworld.command.CommandManager;

public class ModernalWorld extends JavaPlugin
{
	private static ModernalWorld instance;
	
	private HashMap<String, Command> commands = new HashMap<>();
	
	/**
	 * Base da API
	 * 
	 * @return Instancia da classe principal ModernalWorld
	 */
	public static ModernalWorld getInstance(){
		return instance;
	}
	
	@Override
	public void onEnable(){
		instance = this;
	}
	
	@Override
	public void onDisable(){
		
	}
	
	/**
	 * @return Commando registrado, caso exista
	 */
	public Command getRegisteredCommand(String name){
		if(!commands.containsKey(name)){
			return null;
		}
		
		return commands.get(name);
	}
	
	/**
	 * Registrar comando (Não funciona em tempo de execução (Runtime))
	 * @param name -> Nome do comando
	 * @param command -> Instância do comando
	 */
	public void addCommand(String name, Command command){
		commands.put(name, command);
	}
	
	/**
	 * 
	 * @param name
	 */
	public void removeCommand(String name){
		commands.remove(name);
	}
	
	/**
	 * Registrar comandos no CommandExecutor
	 */
	public void registerCommands(JavaPlugin plugin){
		CommandManager manager = new CommandManager(this);
		
		for(String cmd : commands.keySet()){
			plugin.getCommand(cmd).setExecutor(manager);
		}
	}
	
}
