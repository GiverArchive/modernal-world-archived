package net.modernalworld.plugin;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import net.modernalworld.plugin.command.ModernalCommandExecutor;
import net.modernalworld.plugin.command.ModernalCommand;
import net.modernalworld.plugin.command.commands.FlyCommand;
import net.modernalworld.plugin.manager.ConfigManager;
import net.modernalworld.plugin.manager.PlayerManager;
import net.modernalworld.plugin.objects.Rank;

public class ModernalWorld extends JavaPlugin
{
	private static ModernalWorld instance;
	
	private HashMap<String, ModernalCommand> commands = new HashMap<>();
	private HashMap<String, PlayerManager> players = new HashMap<>();
	private HashMap<String, Rank> rankups = new HashMap<>();
	
	private ConfigManager settings;
	private ConfigManager ranks;
	
	private ModernalCommandExecutor executor;
	
	private Economy economy;
	
	private String prefix = "§a[ModernalWorld]§r";
	
	public static ModernalWorld getInstance()
	{
		return instance;
	}
	
	// TODO Ferramentas
	
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
		return this.prefix;
	}
	
	// TODO Getters - Coleções
	
	public HashMap<String, ModernalCommand> getRegisteredCommands()
	{
		return this.commands;
	}
	
	public HashMap<String, Rank> getRankups()
	{
		return this.rankups;
	}
	
	// TODO Encapsulamentos principais
	
	public void addCommand(String name, ModernalCommand command)
	{
		if(executor == null)
		  executor = new ModernalCommandExecutor(this);
		
		commands.put(name, command);
		getCommand(name).setExecutor(executor);
	}
	
	public void addPlayerManager(String name)
	{
		players.put(name, new PlayerManager(name));
	}
	
	public PlayerManager getPlayerManager(String name)
	{
		return (players.containsKey(name) ? players.get(name) : null);
	}
	
	public Economy getEconomy()
	{
		return this.economy;
	}
	
	// TODO Metodos De Registro
	
	private void setupConfigs()
	{
		settings = new ConfigManager("settings", null);
		settings.saveDefaultConfig();
		
		ranks = new ConfigManager("ranks", null);
		ranks.saveDefaultConfig();
		
		this.prefix = settings.getConfig().getString("servidor.prefixo");
	}
	
	public void setupRanks()
	{
		for(String s : ranks.getConfig().getConfigurationSection("ranks").getKeys(false))
		{
			String path = "ranks." + s + ".";
			String proximo = ranks.getConfig().getString(path + "proximo");
			
			double custo = ranks.getConfig().getDouble(path + "custo");
			
			boolean last = ranks.getConfig().getBoolean(path + "ultimo");
			boolean prim = ranks.getConfig().getBoolean(path + "primeiro");
			
			rankups.put(s, new Rank(s, proximo, custo, last, prim));
		}
	}
	
	private boolean setupEconomy() 
	{
		if (getServer().getPluginManager().getPlugin("Vault") == null) 
		{
			return false;
		}
		
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		
		if (rsp == null) 
		{
			return false;
		}
		
		economy = rsp.getProvider();
		return economy != null;
	}
	
	private void registerCommands()
	{
		addCommand("fly", new FlyCommand());
	}
	
	// TODO Enable e Disable
	
	@Override
	public void onEnable()
	{
		instance = this;
		
		print(" §aHabilitando plugin");
		
		setupConfigs();
		setupRanks();
		setupEconomy(); // Sem validação hehe
		registerCommands();
	}
	
	@Override
	public void onDisable()
	{
		print(getPrefix() + " §cDesabilitando plugin");
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
