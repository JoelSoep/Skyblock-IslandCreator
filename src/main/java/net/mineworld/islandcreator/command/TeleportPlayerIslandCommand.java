package net.mineworld.islandcreator.command;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportPlayerIslandCommand implements CommandExecutor {

    private Island island;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player notConsole) {
            notConsole.sendMessage(Component.text(ChatColor.RED + "This command can only be executed by the console."));
            return true;
        }

        ConsoleCommandSender console = (ConsoleCommandSender) sender;

        if (args.length == 0) {
            console.sendMessage(Component.text(ChatColor.RED + "Couldn't find player."));
            return true;
        }

        SuperiorPlayer target = SuperiorSkyblockAPI.getPlayer(args[0]);

        if (target == null) {
            console.sendMessage(Component.text(ChatColor.RED + "That player is not online."));
            return true;
        }

        Island island = target.getIsland();

        if (island == null) {
            console.sendMessage(Component.text(ChatColor.RED + "That player does not have an island."));
            return true;
        }

        target.teleport(island);

        return true;
    }
}
