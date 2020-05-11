package me.Stellrow.ZenithAdditions;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Tips implements Listener {
    private Set<UUID> players = new HashSet<UUID>();
    private Set<UUID> spawners = new HashSet<UUID>();
    private final ZenithAdditions pl;

    public Tips(ZenithAdditions pl) {
        this.pl = pl;
        init();
    }
    private void init(){
        pl.getServer().getPluginManager().registerEvents(this,pl);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(e.getBlockPlaced().getType()== Material.CRAFTING_TABLE){
            Player p = e.getPlayer();
            if(!players.contains(p.getUniqueId())){
                sendCTMessages(p);
                players.add(p.getUniqueId());
                Sound toPlay = Sound.valueOf(pl.getConfig().getString("Tips.craftingtable.soundToPlay.sound"));
                p.playSound(p.getLocation(),toPlay,pl.getConfig().getInt("Tips.craftingtable.soundToPlay.pitch"), (float) pl.getConfig().getDouble("Tips.craftingtable.soundToPlay.volume"));

            }
            return;
        }
        if(e.getBlockPlaced().getType()== Material.SPAWNER){
            Player p = e.getPlayer();
            if(!spawners.contains(p.getUniqueId())){
                sendSpawnerMessages(p);
                spawners.add(p.getUniqueId());
                Sound toPlay = Sound.valueOf(pl.getConfig().getString("Tips.spawner.soundToPlay.sound"));
                p.playSound(p.getLocation(),toPlay,pl.getConfig().getInt("Tips.spawner.soundToPlay.pitch"), (float) pl.getConfig().getDouble("Tips.spawner.soundToPlay.volume"));
            }
            return;
        }
    }
    private void sendCTMessages(Player toSend){
    for(String s : pl.getConfig().getStringList("Tips.craftingtable.messagesToSend")){
        toSend.sendMessage(ChatColor.translateAlternateColorCodes('&',s));
    }
    }
    private void sendSpawnerMessages(Player toSend){
        for(String s : pl.getConfig().getStringList("Tips.spawner.messagesToSend")){
            toSend.sendMessage(ChatColor.translateAlternateColorCodes('&',s));
        }
    }

}
