package me.rimon.custombucketplugin;

import org.bukkit.plugin.java.JavaPlugin;


public class CustomBucketPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register the event listener
        getServer().getPluginManager().registerEvents(new BucketListener(), this);
        this.getCommand("givebucket").setExecutor(new GiveBucketCommand());
        getLogger().info("CustomBucketPlugin has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomBucketPlugin has been disabled.");
    }
}
