package net.modernalworld.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import net.modernalworld.plugin.manager.CommandManager;
import net.modernalworld.plugin.manager.ConfigManager;
import net.modernalworld.plugin.manager.PlayerManager;
import net.modernalworld.plugin.manager.RankManager;
import net.modernalworld.plugin.objects.Config;

public class ModernalWorld extends JavaPlugin
{
	private static ModernalWorld instance;
	
	private CommandManager cmdManager;
	private ConfigManager cfgManager;
	private PlayerManager playerManager;
	private RankManager rankManager;
	private Config settings;
	private String prefix = "§a[ModernalWorld]§r";
	
	private Economy economy;
	
	public static ModernalWorld getInstance()
	{
		return instance;
	}

	public String getPrefix()
	{
		return this.prefix;
	}

	public PlayerManager getPlayerManager()
	{
		return this.playerManager;
	}
	
	public Economy getEconomy()
	{
		return this.economy;
	}
	
	public RankManager getRankManager()
	{
		return this.rankManager;
	}
	
	public ConfigManager getConfigManager()
	{
		return this.cfgManager;
	}
	
	public CommandManager getCommandManager()
	{
		return this.cmdManager;
	}
	
	@Override
	public void onEnable()
	{
		instance = this;
		
		print(" §aHabilitando plugin");
		
		cfgManager = new ConfigManager(this);
		rankManager = new RankManager(this);
		cmdManager = new CommandManager(this);
		playerManager = new PlayerManager(this);
		
		settings = cfgManager.getSettingsConfig();
		this.prefix = settings.getConfig().getString("servidor.prefixo");
		
		setupEconomy(); // Sem validação hehe
	}
	
	@Override
	public void onDisable()
	{
		print(getPrefix() + " §cDesabilitando plugin");
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
	
	public void print(String msg)
	{
		Bukkit.getConsoleSender().sendMessage(msg);
	}
	
	public void runConsole(String command)
	{
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
	}
	
}
