package me.rimon.custombucketplugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class BucketListener implements Listener {

    private static final int MAX_USES = 5;

    @EventHandler
    public void onPlayerUseBucket(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            ItemStack item = event.getItem();

            if (item != null && item.getType() == Material.WATER_BUCKET) {
                event.setCancelled(true);
                Block block = event.getClickedBlock();
                Location loc = block.getLocation();
                ItemMeta meta = item.getItemMeta();
                if (meta == null) return;

                List<String> lore = meta.getLore();
                if (lore == null || lore.isEmpty() || !lore.get(0).startsWith(ChatColor.GRAY + "Uses: ")) {
                    // Initialize the item with uses if it doesn't have the proper lore
                    meta.setLore(Arrays.asList(
                            ChatColor.GRAY + "Uses: " + MAX_USES
                    ));
                    item.setItemMeta(meta);
                    return;
                }

                // Parse the number of uses remaining
                String usesString = lore.get(0).substring((ChatColor.GRAY + "Uses: ").length());
                int uses = Integer.parseInt(usesString);

                if (uses > 0) {
                    // Decrease the number of uses
                    uses--;
                    meta.setLore(Arrays.asList(
                            ChatColor.GRAY + "Uses: " + uses


                    ));
                    loc.getBlock().getRelative(0,1,0).setType(Material.WATER);;
                } else {
                    // Set the bucket as empty
                    meta.setLore(Arrays.asList(
                            ChatColor.RED + "Empty",
                            ChatColor.AQUA + "Refill me to use again"
                    ));
                    item.setType(Material.BUCKET); // Change to an empty bucket
                }

                item.setItemMeta(meta);
            }
        }
    }

}