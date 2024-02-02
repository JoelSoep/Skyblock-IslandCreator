package net.mineworld.islandcreator.listener;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getTo().getWorld() != Bukkit.getWorld("SuperiorWorld")) {
            return;
        }

        Player player = event.getPlayer();
        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getPlayer(player);;

        if (superiorPlayer == null) {
            return;
        }

        if (event.getTo().getBlockX() < 10 && event.getTo().getBlockX() > -10 && event.getTo().getBlockZ() < 10 && event.getTo().getBlockZ() > -10) {
            Island island = superiorPlayer.getIsland();

                if (island == null) {
                    player.teleport(new Location(Bukkit.getWorld("world"), -0.5, 32, -0.5, 90, 0));
                    return;
                }
                    superiorPlayer.teleport(island.getCenter(World.Environment.NORMAL));
        }
    }
}
