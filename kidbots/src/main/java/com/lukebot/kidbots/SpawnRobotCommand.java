package com.lukebot.kidbots;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
            spawnRobot(spawnLocation, "Mahir", NamedTextColor.RED);
            player.sendMessage(Component.text("Spawned the red robot Mahir!").color(NamedTextColor.GREEN));
            return true;
        }

        if (robotName.equals("ruhani")) {
            spawnRobot(spawnLocation, "Ruhani", NamedTextColor.DARK_PURPLE);
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
    private void spawnRobot(Location location, String name, NamedTextColor color) {
        // 1. Use the modern, type-safe spawn method
        IronGolem robot = location.getWorld().spawn(location, IronGolem.class);

        // 2. Use the Adventure Component API for the custom name
        robot.customName(Component.text(name).color(color));
        robot.setCustomNameVisible(true);
        robot.setPlayerCreated(true);

        // This method is still current
        robot.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1, false, false));
    }
}