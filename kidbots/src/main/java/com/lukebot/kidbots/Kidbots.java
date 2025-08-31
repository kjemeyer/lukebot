package com.lukebot.kidbots;

import org.bukkit.plugin.java.JavaPlugin;

public final class Kidbots extends JavaPlugin {


    @Override
    public void onEnable() {
        getLogger().info("Hello, World! The HelloWorld plugin has been enabled.");

        // We don't need the PlayerJoinListener anymore, but you can leave it if you want.
        // getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        // Register the new spawnrobot command
        getCommand("spawnrobot").setExecutor(new SpawnRobotCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
