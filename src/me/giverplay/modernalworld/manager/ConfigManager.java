package me.giverplay.modernalworld.manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.giverplay.modernalworld.ModernalWorld;

public class ConfigManager
{
  private ModernalWorld plugin; 
  private String name, folder;
  private File file, datafolder;
  private YamlConfiguration config;
  
  public ConfigManager(String nome, String datafolder)
  {
  	plugin = ModernalWorld.getInstance();
  	folder = datafolder;
    setName(nome + ".yml");
    reloadConfig();
  }
  
  private Plugin getPlugin()
  {
    return this.plugin;
  }
	  
  private String getName()
  {
    return this.name;
  }
	  
  private void setName(String name)
  {
    this.name = name;
  }
	  
  private File getFile()
  {
    return this.file;
  }
	  
  public YamlConfiguration getConfig()
  {
    return this.config;
  }
	  
  public void saveConfig()
  {
    try
    {
      getConfig().save(getFile());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void saveDefaultConfig()
  {
    if (!existeConfig())
    {
      getPlugin().saveResource(getName(), false);
      reloadConfig();
    }
  }
	  
  public void createConfig()
  {
  	if(!getFile().exists())
  	{
  		try
  		{
  			getFile().createNewFile();
  		}
  		catch(IOException e)
  		{
  			e.printStackTrace();
  		}
  	}
  	
  	reloadConfig();
  }
  
  public void reloadConfig()
  {
  	this.datafolder = (this.folder != null ? new File(plugin.getDataFolder(), this.folder) : plugin.getDataFolder());
  	
  	if(!datafolder.exists())
  	{
  		datafolder.mkdir();
  	}
  	
    this.file = new File(datafolder, getName());
    this.createConfig();
    this.config = YamlConfiguration.loadConfiguration(getFile());
  }
	  
  public void deleteConfig()
  {
    getFile().delete();
  }
  
  public boolean existeConfig()
  {
    return getFile().exists();
  }
}
