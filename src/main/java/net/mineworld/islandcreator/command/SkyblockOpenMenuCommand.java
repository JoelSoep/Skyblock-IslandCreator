package net.mineworld.islandcreator.command;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import net.mineworld.islandcreator.api.SuperiorAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SkyblockOpenMenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("Usage: /skyblockopenmenu <menu>");
            return true;
        }

        switch(args[0]) {
            case "permissions" -> SuperiorAPI.openPermissions(SuperiorSkyblockAPI.getPlayer(player));
            case "chest" -> SuperiorAPI.openIslandChest(SuperiorSkyblockAPI.getPlayer(player));
            case "bank" -> SuperiorAPI.openBank(SuperiorSkyblockAPI.getPlayer(player));
            case "biomes" -> SuperiorAPI.openBiomes(SuperiorSkyblockAPI.getPlayer(player));
        }
        return true;
    }
}
