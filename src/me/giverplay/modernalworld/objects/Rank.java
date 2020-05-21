package me.giverplay.modernalworld.objects;

public class Rank
{
	private String name;
	private Rank next;
	private double cost;
	
	public Rank(String name, Rank next, double cost)
	{
		this.name = name;
		this.next = next;
		this.cost = cost;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Rank nextRank()
	{
		return this.next;
	}
	
	public double getCost()
	{
		return this.cost;
	}
}
