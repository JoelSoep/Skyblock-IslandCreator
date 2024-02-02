package net.mineworld.islandcreator.listener;

import com.bgsoftware.superiorskyblock.api.events.IslandChangeRoleLimitEvent;
import com.bgsoftware.superiorskyblock.api.events.PlayerChangeRoleEvent;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import net.kyori.adventure.text.Component;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.mineworld.islandcreator.api.LuckPermsAPI;
import net.mineworld.islandcreator.permission.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class IslandPlayerRoleChangeEvent implements Listener {

    @EventHandler
    public void onChange(PlayerChangeRoleEvent event) {
        String message = ChatColor.RED + "The owner of this island needs at least " + Rank.RANGER.getDisplayName() + ChatColor.RED + " rank to use this feature. Purchase it at " + ChatColor.GOLD + "https://store.mineworld.net";
        Island island = event.getPlayer().getIsland();

        if (island == null) {
            return;
        }

        SuperiorPlayer owner = island.getOwner();

        if (owner == null) {
            return;
        }

        User user = LuckPermsAPI.getUser(owner.getUniqueId());
        if (user == null) {
            return;
        }


        if (!LuckPermsAPI.hasPermission(user, "group.ranger")) {
            event.setCancelled(true);
            Player player = event.getPlayer().asPlayer();
            if (player != null) {
                player.sendMessage(Component.text(message));
            }
        }
    }
}
