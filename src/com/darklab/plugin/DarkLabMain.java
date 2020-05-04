package com.darklab.plugin;

import com.darklab.plugin.annotation.Command;
import com.darklab.plugin.command.CommandBase;
import com.darklab.plugin.command.CommandKit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DarkLabMain extends JavaPlugin implements Listener {
    private static Logger LOGGER = null;

    @Override
    public void onEnable() {
        LOGGER = this.getLogger();
        try {
            getServer().getPluginManager().registerEvents(this, this);

            registerCommand(CommandKit.class);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        event.setJoinMessage(p.getName() + " welcome to this server!");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        event.setQuitMessage(p.getName() + " has left the server!");
    }

    private void registerCommand(Class<? extends CommandBase> c) throws IllegalAccessException, InstantiationException {
        String commandAlias = c.getAnnotation(Command.class).value();

        if (commandAlias.isEmpty()) {
            commandAlias = c.getName().replace("Command", "").toLowerCase();
        }

        Objects.requireNonNull(this.getCommand(commandAlias)).setExecutor(c.newInstance());
    }
}
