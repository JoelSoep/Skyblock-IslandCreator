package net.mineworld.islandcreator.listener;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.events.IslandTransferEvent;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import net.kyori.adventure.text.Component;
import net.luckperms.api.model.group.Group;
import net.mineworld.islandcreator.api.LuckPermsAPI;
import net.mineworld.islandcreator.api.SuperiorAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Locale;
import java.util.logging.Level;

public class IslandOwnershipTransferEvent implements Listener {

    @EventHandler
    public void onTransfer(IslandTransferEvent event) {
        SuperiorPlayer oldOwner = event.getOldOwner();
        SuperiorPlayer newOwner = event.getNewOwner();
        Player oldPlayer = oldOwner.asPlayer();
        Player newPlayer = newOwner.asPlayer();

        if (oldPlayer == null || newPlayer == null) {
            return;
        }

        Group oldGroup = LuckPermsAPI.getGroup(oldPlayer);
        Group newGroup = LuckPermsAPI.getGroup(newPlayer);

        if (oldGroup == null || newGroup == null) {
            Bukkit.getLogger().log(Level.WARNING, "Luckperms group is null.");
            return;
        }

        if (!oldGroup.getWeight().isPresent() || !newGroup.getWeight().isPresent()) {
            Bukkit.getLogger().log(Level.WARNING, "Luckperms group weight is null.");
            return;
        }

        if (oldPlayer.hasPermission("mineworld.admin") || newPlayer.hasPermission("mineworld.admin")) {
            return;
        }

        if (oldGroup.getWeight().getAsInt() >= newGroup.getWeight().getAsInt()) {
            event.setCancelled(true);
            oldPlayer.sendMessage(Component.text(ChatColor.RED + "You cannot transfer ownership to someone with a lower rank."));
        }

        Island island = event.getIsland();

        if (newPlayer.hasPermission("mineworld.staff")) {
            island.setCoopLimit(10);
            island.setIslandSize(400);
            island.setWarpsLimit(10);
            island.setTeamLimit(10);
            SuperiorAPI.setChests(island, 15);
            Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + newPlayer.getName() + " has been given a staff island by " + oldPlayer.getName() + ".");
            return;
        }

        switch(newGroup.getName().toLowerCase(Locale.ROOT)) {
            case "scout":
                island.setCoopLimit(1);
                island.setTeamLimit(2);
                island.setIslandSize(200);
                SuperiorAPI.setChests(island, 2);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + newPlayer.getName() + " has been given a scout island via transfer from " + oldPlayer.getName());
                return;
            case "ranger":
                island.setCoopLimit(3);
                island.setIslandSize(225);
                SuperiorAPI.setChests(island, 4);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + newPlayer.getName() + " has been given a ranger island via transfer from " + oldPlayer.getName());
                return;
            case "sentinel":
                island.setCoopLimit(4);
                island.setIslandSize(250);
                SuperiorAPI.setChests(island, 6);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + newPlayer.getName() + " has been given a sentinel island via transfer from " + oldPlayer.getName());
                return;
            case "hero":
                island.setCoopLimit(5);
                island.setIslandSize(275);
                island.setWarpsLimit(2);
                SuperiorAPI.setChests(island, 8);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + newPlayer.getName() + " has been given a hero island via transfer from " + oldPlayer.getName());
                return;
            case "elder":
                island.setCoopLimit(7);
                island.setIslandSize(300);
                island.setWarpsLimit(3);
                SuperiorAPI.setChests(island, 10);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + newPlayer.getName() + " has been given an elder island via transfer from " + oldPlayer.getName());
                return;
            case "emperor":
                island.setCoopLimit(7);
                island.setIslandSize(350);
                island.setWarpsLimit(4);
                island.setTeamLimit(7);
                SuperiorAPI.setChests(island, 12);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + newPlayer.getName() + " has been given an emperor island via transfer from " + oldPlayer.getName());
                return;
        }

    }
}
