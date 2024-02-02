package net.mineworld.islandcreator.command;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class CreateIslandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            player.sendMessage(Component.text(ChatColor.RED + "This command can only be executed by the console."));
            return true;
        }

        ConsoleCommandSender console = (ConsoleCommandSender) sender;

        if (args.length == 0) {
            console.sendMessage(Component.text(ChatColor.RED + "Couldn't find player."));
            return true;
        }

        if (args.length == 1) {
            console.sendMessage(Component.text(ChatColor.RED + "Couldn't find schema."));
            return true;
        }

        if (args.length == 2) {
            console.sendMessage(Component.text(ChatColor.RED + "Couldn't find island name."));
            return true;
        }

        String schema = args[1];
        String islandName = args[2];
        Biome biome = Biome.valueOf(args[3].toUpperCase());

        SuperiorPlayer target = SuperiorSkyblockAPI.getPlayer(args[0]);

        if (target == null) {
            console.sendMessage(Component.text(ChatColor.RED + "That player is not online."));
            return true;
        }

        Player targetPlayer = target.asPlayer();

        if (targetPlayer == null) {
            console.sendMessage(Component.text(ChatColor.RED + "That player is not online."));
            return true;
        }

        if (target.getIsland() != null) {
            targetPlayer.sendMessage(Component.text(ChatColor.RED + "You already have an island."));
            return true;
        }

        SuperiorSkyblockAPI.createIsland(target, schema, BigDecimal.valueOf(0), biome, islandName);
        targetPlayer.sendMessage(Component.text(ChatColor.GREEN + "You have been given an island."));

        return true;
    }
}
