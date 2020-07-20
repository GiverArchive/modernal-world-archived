package net.modernalworld.plugin.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.modernalworld.plugin.ModernalWorld;

public class ModernalCommandExecutor implements CommandExecutor
{
	private ModernalWorld plugin;
	
	public ModernalCommandExecutor(ModernalWorld plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
		ModernalCommand command = plugin.getRegisteredCommand(cmd.getName());
		
		if(command.getType().equals(CommandType.PLAYER))
		{
			if(!(sender instanceof Player))
			{
				sender.sendMessage("§cEste comando só pode ser executado por jogadores");
				return true;
			}
		} 
		
		if(!sender.hasPermission(command.getPermission()))
		{
			sender.sendMessage("§cPermissões insulficientes");
			return true;
		}
		
		command.execute(sender, args);
		return true;
	}
}
