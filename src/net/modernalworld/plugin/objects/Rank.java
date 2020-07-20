package net.modernalworld.plugin.objects;

import net.modernalworld.plugin.ModernalWorld;

public class Rank
{
	private ModernalWorld plugin;
	private String name, next;
	private double cost;
	private boolean isLast, isPrimary;
	
	public Rank(String name, String next, double cost, boolean isLast, boolean isPrimary)
	{
		this.plugin = ModernalWorld.getInstance();
		this.name = name;
		this.next = (next != null ? next : "nenhum");
		this.cost = cost;
		this.isLast = isLast;
		this.isPrimary = isPrimary;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Rank nextRank()
	{
		return (plugin.getRankManager().getRankups().containsKey(next) ? plugin.getRankManager().getRankups().get(next) : null);
	}
	
	public double getCost()
	{
		return this.cost;
	}
	
	public boolean isLastRank()
	{
		return this.isLast;
	}
	
	public boolean isPrimaryRank()
	{
		return this.isPrimary;
	}
}
