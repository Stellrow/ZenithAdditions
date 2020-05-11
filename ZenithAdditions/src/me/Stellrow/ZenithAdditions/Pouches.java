package me.Stellrow.ZenithAdditions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Pouches implements Listener {
    private String chest;
    private String echest;
    private final ZenithAdditions pl;

    public Pouches(ZenithAdditions pl) {
        this.pl = pl;
        init();
    }
    private void init(){
        pl.getServer().getPluginManager().registerEvents(this,pl);
        chest = ChatColor.translateAlternateColorCodes('&',pl.getConfig().getString("Pouches.normalchest.name"));
        echest = ChatColor.translateAlternateColorCodes('&',pl.getConfig().getString("Pouches.enderchest.name"));
    }
    @EventHandler
    public void onUse(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getItem().hasItemMeta()&&e.getItem().getItemMeta().hasDisplayName()){
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(chest)){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',pl.getConfig().getString("Pouches.normalchest.messageToSend")));
                Bukkit.dispatchCommand(pl.getServer().getConsoleSender(),pl.getConfig().getString("Pouches.normalchest.commandToExecute").replaceAll("%player",p.getName()));
                Sound toPlay = Sound.valueOf(pl.getConfig().getString("Pouches.normalchest.soundToPlay.sound"));
                p.playSound(p.getLocation(),toPlay,pl.getConfig().getInt("Pouches.normalchest.soundToPlay.pitch"), (float) pl.getConfig().getDouble("Pouches.enderchest.soundToPlay.volume"));
                e.getItem().setAmount(e.getItem().getAmount()-1);
                return;
            }
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(echest)){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',pl.getConfig().getString("Pouches.enderchest.messageToSend")));
                Bukkit.dispatchCommand(pl.getServer().getConsoleSender(),pl.getConfig().getString("Pouches.enderchest.commandToExecute").replaceAll("%player",p.getName()));
                Sound toPlay = Sound.valueOf(pl.getConfig().getString("Pouches.enderchest.soundToPlay.sound"));
                p.playSound(p.getLocation(),toPlay,pl.getConfig().getInt("Pouches.enderchest.soundToPlay.pitch"), (float) pl.getConfig().getDouble("Pouches.enderchest.soundToPlay.volume"));
                e.getItem().setAmount(e.getItem().getAmount()-1);
                return;
            }
        }
    }
}
