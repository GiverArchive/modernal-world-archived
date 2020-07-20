package net.modernalworld.plugin.manager;

import java.util.HashMap;

import net.modernalworld.plugin.ModernalWorld;
import net.modernalworld.plugin.objects.PlayerBase;

public class PlayerManager
{
	private HashMap<String, PlayerBase> players = new HashMap<>();
	
	private ModernalWorld modernal;
	
	public PlayerManager(ModernalWorld modernal)
	{
		this.modernal = modernal;
	}
	
	public void addPlayerBase(String name)
	{
		modernal.print("Adicionando jogador " + name);
		players.put(name, new PlayerBase(name));
	}
	
	public PlayerBase getPlayerBase(String name)
	{
		return (players.containsKey(name) ? players.get(name) : null);
	}
	
	public void removePlayerBase(String name)
	{
		modernal.print("Removendo jogador " + name);
		players.remove(name);
	}
}
