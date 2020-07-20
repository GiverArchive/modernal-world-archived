package net.modernalworld.plugin.manager;

import java.util.HashMap;

import net.modernalworld.plugin.ModernalWorld;
import net.modernalworld.plugin.objects.Config;
import net.modernalworld.plugin.objects.Rank;

public class RankManager
{
	private HashMap<String, Rank> rankups = new HashMap<>();
	private ModernalWorld modernal;
	private Config config;
	
	public RankManager(ModernalWorld modernal)
	{
		this.modernal = modernal;
		config = modernal.getConfigManager().getRanksConfig();
	}
	
	public void setupRanks()
	{
		for(String s : config.getConfig().getConfigurationSection("ranks").getKeys(false))
		{
			String path = "ranks." + s + ".";
			String proximo = config.getConfig().getString(path + "proximo");
			
			double custo = config.getConfig().getDouble(path + "custo");
			
			boolean last = config.getConfig().getBoolean(path + "ultimo");
			boolean prim = config.getConfig().getBoolean(path + "primeiro");
			
			rankups.put(s, new Rank(s, proximo, custo, last, prim));
		}
		
		modernal.print("Ranks inicializados");
	}
	
	public HashMap<String, Rank> getRankups()
	{
		return this.rankups;
	}
}
