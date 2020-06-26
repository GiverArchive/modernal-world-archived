package net.modernalworld.plugin.command.commands;

import org.bukkit.command.CommandSender;

import net.modernalworld.plugin.ModernalWorld;
import net.modernalworld.plugin.command.CommandType;
import net.modernalworld.plugin.command.ModernalCommand;
import net.modernalworld.plugin.manager.PlayerManager;

public class FlyCommand extends ModernalCommand
{
	private ModernalWorld plugin;
	
	public FlyCommand()
	{
		super("fly", "fly", CommandType.PLAYER);
		plugin = ModernalWorld.getInstance();
	}
	
	@Override
	public void execute(CommandSender sender, String[] args)
	{
		PlayerManager player = plugin.getPlayerManager(sender.getName());
		
		if(player.getFlightEnabled())
		{
			player.setFlightEnabled(false);
			player.sendMessage("§cModo voar desabilitado");
			return;
		}
		
		player.setFlightEnabled(true);
		player.sendMessage("§aModo voar habilitado");
	}
}
