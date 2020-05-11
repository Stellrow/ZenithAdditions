package me.Stellrow.ZenithAdditions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Silk implements CommandExecutor {
    private final ZenithAdditions pl;

    public Silk(ZenithAdditions pl) {
        this.pl = pl;
        init();

    }
    private void init(){
        pl.getCommand("silk").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("za.silk")){
                addSilkEnchant(p.getInventory().getItemInMainHand());
                return true;
            }
        }else{
            sender.sendMessage("Players only!");
            return true;
        }
        return true;
    }
    private void addSilkEnchant(ItemStack item){
        if(item==null){
            return;
        }
        if(item.getType().toString().endsWith("_PICKAXE")||item.getType().toString().endsWith("_AXE")||item.getType().toString().endsWith("_SHOVEL")){
            item.addEnchantment(Enchantment.SILK_TOUCH,1);
        }
    }
}
