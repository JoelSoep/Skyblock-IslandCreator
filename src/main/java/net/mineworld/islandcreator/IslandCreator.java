package net.mineworld.islandcreator;

import net.mineworld.islandcreator.command.CreateIslandCommand;
import net.mineworld.islandcreator.command.SkyblockOpenMenuCommand;
import net.mineworld.islandcreator.command.TeleportPlayerIslandCommand;
import net.mineworld.islandcreator.listener.*;
import net.mineworld.islandcreator.placeholder.PlayerHasIslandPlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class IslandCreator extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        if (!(Bukkit.getPluginManager().isPluginEnabled("LuckPerms"))) {
            Bukkit.getLogger().log(Level.SEVERE, "LuckPerms is not installed on this server!");
            Bukkit.getLogger().log(Level.SEVERE, "IslandCreator will be disabled.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            // this returns a boolean, true if your placeholder is successfully registered, false if it isn't
            new PlayerHasIslandPlaceholder().register();
            Bukkit.getLogger().log(Level.FINE, "PlaceholderAPI is enabled, registering placeholder");
        } else {
            Bukkit.getLogger().log(Level.SEVERE, "PlaceholderAPI is not installed!");
            Bukkit.getLogger().log(Level.SEVERE, "IslandCreator will be disabled.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        getServer().getPluginCommand("mw-consoleislandmgr").setExecutor(new CreateIslandCommand());
        getServer().getPluginCommand("mw-consoletpisland").setExecutor(new TeleportPlayerIslandCommand());
        getServer().getPluginCommand("mw-som").setExecutor(new SkyblockOpenMenuCommand());
        getServer().getPluginManager().registerEvents(new IslandCreateEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new CommandEvent(), this);
        getServer().getPluginManager().registerEvents(new IslandOwnershipTransferEvent(), this);
        getServer().getPluginManager().registerEvents(new IslandPlayerRoleChangeEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
