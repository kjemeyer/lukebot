package com.lukebot.kidbots;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;


public class SpawnRobotCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Only players can run this command."));
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(Component.text("Usage: /spawnrobot <mahir|ruhani>").color(NamedTextColor.RED));
            return true;
        }

        String robotName = args[0].toLowerCase();
        Location spawnLocation = player.getLocation();

        if (robotName.equals("mahir")) {
            spawnRobot(player, spawnLocation, "Mahir", NamedTextColor.RED);
            player.sendMessage(Component.text("Spawned the red robot Mahir!").color(NamedTextColor.GREEN));
            return true;
        }

        if (robotName.equals("ruhani")) {
            spawnRobot(player, spawnLocation, "Ruhani", NamedTextColor.DARK_PURPLE);
            player.sendMessage(Component.text("Spawned the purple robot Ruhani!").color(NamedTextColor.GREEN));
            return true;
        }

        player.sendMessage(Component.text("Unknown robot name. Use 'mahir' or 'ruhani'.").color(NamedTextColor.RED));
        return true;
    }

    /**
     * Spawns and customizes the robot creature using the modern API.
     * @param location Where to spawn the robot
     * @param name The name for the robot
     * @param color The color for the robot's name
     */
    private void spawnRobot(Player player, Location location, String name, NamedTextColor color) {

        NamespacedKey key = new NamespacedKey(this.plugin, "custom_model_data");

        // Create an ItemStack of the base item
        ItemStack robotItem = new ItemStack(Material.CARROT_ON_A_STICK);

        // Get its metadata (where we can set custom tags)
        ItemMeta meta = robotItem.getItemMeta();

        // Set the custom name
        meta.displayName(Component.text("Robot Spawner").color(NamedTextColor.GOLD));

        // THIS IS THE MAGIC LINE:
        // Set the custom model data to the number we used in the JSON file
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);

        // Apply the new metadata to the item
        robotItem.setItemMeta(meta);

        // Give the item to the player
        player.getInventory().addItem(robotItem);

        player.sendMessage(Component.text("You received a Robot Spawner!").color(NamedTextColor.GREEN));
    }

    private final Kidbots plugin; // Use your main class name

    public SpawnRobotCommand(Kidbots plugin) {
        this.plugin = plugin;
    }
}