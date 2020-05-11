package me.Stellrow.ZenithAdditions;

import org.bukkit.plugin.java.JavaPlugin;

public class ZenithAdditions extends JavaPlugin {
    private Silk silk;
    private Bosses bosses;
    private Pouches pouches;
    private Tips tips;
    private Rewards rewards;
    public void onEnable(){
        loadConfig();
    initClasses();
    }
    private void initClasses(){
        rewards = new Rewards(this);
        tips = new Tips(this);
        pouches = new Pouches(this);
        bosses = new Bosses(this);
        silk = new Silk(this);
    }
    private void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
