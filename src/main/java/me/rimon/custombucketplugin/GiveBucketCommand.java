package me.rimon.custombucketplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GiveBucketCommand implements CommandExecutor {

    private static final int MAX_USES = 5;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Create the custom bucket item
            ItemStack customBucket = new ItemStack(Material.WATER_BUCKET);
            ItemMeta meta = customBucket.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ChatColor.AQUA + "Custom Water Bucket");
                meta.setLore(Arrays.asList(
                        ChatColor.GRAY + "Uses: " + MAX_USES
                ));
                customBucket.setItemMeta(meta);
            }

            // Give the item to the player
            player.getInventory().addItem(customBucket);
            player.sendMessage(ChatColor.GREEN + "You have received a custom water bucket!");

            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return false;
        }
    }
}
