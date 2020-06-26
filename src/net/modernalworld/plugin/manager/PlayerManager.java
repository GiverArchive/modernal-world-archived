package net.modernalworld.plugin.manager;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.modernalworld.plugin.objects.Rank;

public class PlayerManager extends ConfigManager
{
	private String nick;
	private Player player;
	private CraftPlayer craftplayer;
	private Rank rank;
	
	public PlayerManager(String name)
	{
		super(name.toLowerCase(), "users");
		
		this.player = Bukkit.getPlayer(name);
		this.craftplayer = (CraftPlayer) this.player;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public CraftPlayer getCraftPlayer()
	{
		return this.craftplayer;
	}
	
	public String getName()
	{
		return this.nick;
	}
	
	public void sendMessage(String msg)
	{
		this.player.sendMessage(msg);
	}
	
	public void setFlightEnabled(boolean toSet)
	{
		player.setAllowFlight(toSet);
	}
	
	public boolean hasPermission(String perm)
	{
		return this.player.hasPermission(perm);
	}
	
	public Rank getRank()
	{
		return this.rank;
	}
	
	public void setRank(Rank rank)
	{
		this.rank = rank;
	}
	
	public boolean rankup()
	{
		return true;
	}
	
	public boolean isDeveloper()
	{
		return getName().equals("GiverPlay007");
	}
	
	public boolean isMaster()
	{
		return getName().equals("GiverPlay007") || getName().equals("minecraftdgm");
	}
	
	public boolean hasLastRank()
	{
		return this.getRank().isLastRank();
	}
	
	public boolean hasPrimaryRank()
	{
		return this.rank.isPrimaryRank();
	}
	
	public boolean getFlightEnabled()
	{
		return player.getAllowFlight();
	}
}