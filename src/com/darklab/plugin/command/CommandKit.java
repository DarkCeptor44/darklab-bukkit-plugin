package com.darklab.plugin.command;

import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@com.darklab.plugin.annotation.Command("kit")
public class CommandKit extends CommandBase implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            // player used command
            Player player = (Player) sender;
            ItemStack irons = new ItemStack(Material.IRON_INGOT, 3);
            player.getInventory().addItem(irons);
        } else if (sender instanceof ConsoleCommandSender) {
            // console used command
        } else if (sender instanceof BlockCommandSender) {
            // a command block used command
        }
        return true;
    }
}
