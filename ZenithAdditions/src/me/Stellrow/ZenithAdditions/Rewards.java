package me.Stellrow.ZenithAdditions;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rewards implements CommandExecutor {
    private final ZenithAdditions pl;

    public Rewards(ZenithAdditions pl) {
        this.pl = pl;
        init();
    }
    private void init(){
        pl.getCommand("rewards").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String sa, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            for(String s : pl.getConfig().getStringList("Rewards.messagesToSend")){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',s));
            }
            return true;
        }else{
            sender.sendMessage("Players only!");
        }
        return true;
    }
}
