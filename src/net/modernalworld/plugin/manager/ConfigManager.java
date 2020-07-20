package net.modernalworld.plugin.manager;

import net.modernalworld.plugin.ModernalWorld;
import net.modernalworld.plugin.objects.Config;

public class ConfigManager
{
	private Config settings;
	private Config ranks;
	
	public ConfigManager(ModernalWorld modernal)
	{
		setupConfigs();
	}
	
	private void setupConfigs()
	{
		settings = new Config("settings", null);
		settings.saveDefaultConfig();
		
		ranks = new Config("ranks", null);
		ranks.saveDefaultConfig();
	}
	
	public Config getSettingsConfig()
	{
		return this.settings;
	}
	
	public Config getRanksConfig()
	{
		return this.ranks;
	}
}
