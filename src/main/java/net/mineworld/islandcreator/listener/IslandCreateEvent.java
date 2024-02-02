package net.mineworld.islandcreator.listener;

import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.IslandFlag;
import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import com.bgsoftware.superiorskyblock.api.island.PlayerRole;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import net.mineworld.islandcreator.api.LuckPermsAPI;
import net.mineworld.islandcreator.api.SuperiorAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;
import java.util.logging.Level;

public class IslandCreateEvent implements Listener {

    @EventHandler
    public void onIslandCreate(com.bgsoftware.superiorskyblock.api.events.IslandCreateEvent event) {

        SuperiorPlayer superiorPlayer = event.getPlayer();
        Island island = event.getIsland();
        Player player = superiorPlayer.asPlayer();

        if (player == null) {
            Bukkit.getLogger().log(Level.WARNING, "[IslandCreation-Event] Player " + superiorPlayer.getName() + " is null.");
            return;
        }

        if (LuckPermsAPI.getGroupName(player).equals("default")) {
            island.setIslandSize(175);
            island.setCoopLimit(1);
            island.setTeamLimit(1);
            return;
        }

        if (player.hasPermission("mineworld.staff")) {
            island.setCoopLimit(10);
            island.setIslandSize(400);
            island.setWarpsLimit(10);
            island.setTeamLimit(10);
            SuperiorAPI.setChests(island, 15);
            Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + player.getName() + " has been given a staff island.");
            return;
        }

        switch(Objects.requireNonNull(LuckPermsAPI.getGroupName(player))) {
            case "scout":
                island.setCoopLimit(1);
                island.setTeamLimit(2);
                island.setIslandSize(200);
                SuperiorAPI.setChests(island, 2);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + player.getName() + " has been given a scout island.");
                return;
            case "ranger":
                island.setCoopLimit(3);
                island.setIslandSize(225);
                SuperiorAPI.setChests(island, 4);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + player.getName() + " has been given a ranger island.");
                return;
            case "sentinel":
                island.setCoopLimit(4);
                island.setIslandSize(250);
                SuperiorAPI.setChests(island, 6);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + player.getName() + " has been given a sentinel island.");
                return;
            case "hero":
                island.setCoopLimit(5);
                island.setIslandSize(275);
                island.setWarpsLimit(2);
                SuperiorAPI.setChests(island, 8);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + player.getName() + " has been given a hero island.");
                return;
            case "elder":
                island.setCoopLimit(7);
                island.setIslandSize(300);
                island.setWarpsLimit(3);
                SuperiorAPI.setChests(island, 10);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + player.getName() + " has been given an elder island.");
                return;
            case "emperor":
                island.setCoopLimit(7);
                island.setIslandSize(350);
                island.setWarpsLimit(4);
                island.setTeamLimit(7);
                SuperiorAPI.setChests(island, 12);
                Bukkit.getLogger().log(Level.INFO, "[MINEWORLD] " + player.getName() + " has been given an emperor island.");
                return;

        }
    }

}
