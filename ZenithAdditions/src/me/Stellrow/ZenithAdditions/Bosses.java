package me.Stellrow.ZenithAdditions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Bosses {
    private final ZenithAdditions pl;
    private int hoursBetween;
    private String commandToExecute;

    public Bosses(ZenithAdditions pl) {
        this.pl = pl;
        init();
    }
    private void init(){
        hoursBetween=pl.getConfig().getInt("Bosses.hoursBetween");
        commandToExecute=pl.getConfig().getString("Bosses.commandToExecute");
        startRunnable();
    }
    private void startRunnable(){
        new BukkitRunnable(){

            @Override
            public void run() {
                Bukkit.dispatchCommand(pl.getServer().getConsoleSender(),commandToExecute);
                dispatchBroadcasts();
                playSound();
            }
        }.runTaskTimer(pl,0,hoursBetween*3600*20);
    }
    private void playSound(){
        Sound toPlay = Sound.valueOf(pl.getConfig().getString("Bosses.soundToPlay.sound"));

        for(Player p : pl.getServer().getOnlinePlayers()){
            p.playSound(p.getLocation(),toPlay,pl.getConfig().getInt("Bosses.soundToPlay.pitch"), (float) pl.getConfig().getDouble("Bosses.soundToPlay.volume"));
        }
    }
    private void dispatchBroadcasts(){
        for(String s : pl.getConfig().getStringList("Bosses.messagesToBroadcast")){
            broadcast(s);
        }
    }
    private void broadcast(String toBroadcast){
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',toBroadcast));
    }
}
