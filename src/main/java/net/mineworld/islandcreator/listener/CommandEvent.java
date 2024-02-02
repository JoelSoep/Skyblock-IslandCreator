package net.mineworld.islandcreator.listener;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.mineworld.islandcreator.api.LuckPermsAPI;
import net.mineworld.islandcreator.permission.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String message = ChatColor.RED + "The owner of this island needs at least {0}" + ChatColor.RED + " rank to use this command. Purchase it at " + ChatColor.GOLD + "https://store.mineworld.net";
        String command = event.getMessage();
        Player player = event.getPlayer();
        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getPlayer(player);
        if (superiorPlayer == null) {
            return;
        }

        Island island = superiorPlayer.getIsland();
        if (island == null) {
            return;
        }

        SuperiorPlayer superiorOwner = island.getOwner();
        if (superiorOwner == null) {
            Bukkit.getLogger().info("Owner of " + player.getName() + "'s island is null");
            player.sendMessage(Component.text(ChatColor.RED + "The owner of this island is null. Please contact an administrator."));
            return;
        }

        User owner = LuckPermsAPI.getUser(superiorOwner.getUniqueId());
        if (owner == null) {
            Bukkit.getLogger().info("Owner of " + player.getName() + "'s island is null");
            player.sendMessage(Component.text(ChatColor.RED + "The owner of this island is null. Please contact an administrator."));
            return;
        }

        if (owner.getUniqueId().equals(player.getUniqueId())) {
            message = ChatColor.RED + "You need at least {0}" + ChatColor.RED + " rank to use this command. Purchase it at " + ChatColor.GOLD + "https://store.mineworld.net";
        }



        if (!(command.startsWith("/island ") || command.startsWith("/is ") || command.startsWith("/island") || command.startsWith("/is"))) {
            return;
        }


        command = command.startsWith("/island") ? command.replace("/island", "") : command.replace("/is", "");
        command = command.startsWith(" ") ? command.replaceFirst(" ", "") : command;

        if (command.startsWith("chest") || command.startsWith("vault")) {
            if (LuckPermsAPI.hasPermission(owner, "group.scout")) {
                return;
            }

            event.setCancelled(true);
            player.sendMessage(Component.text(message.replaceFirst("\\{0}", Rank.SCOUT.getDisplayName())).asComponent().clickEvent(ClickEvent.openUrl("https://store.mineworld.net")));
            return;
        }

        if (command.startsWith("bank")) {
            if (LuckPermsAPI.hasPermission(owner, "group.ranger")) {
                return;
            }

            event.setCancelled(true);
            player.sendMessage(Component.text(message.replaceFirst("\\{0}", Rank.RANGER.getDisplayName())).asComponent().clickEvent(ClickEvent.openUrl("https://store.mineworld.net")));
            return;
        }

        if (command.startsWith("sethome")) {
            if (LuckPermsAPI.hasPermission(owner, "group.scout")) {
                return;
            }

            event.setCancelled(true);
            player.sendMessage(Component.text(message.replaceFirst("\\{0}", Rank.SCOUT.getDisplayName())).asComponent().clickEvent(ClickEvent.openUrl("https://store.mineworld.net")));
            return;
        }

        if (command.startsWith("permissions") || command.startsWith("perms") || command.startsWith("setpermission") || command.startsWith("setperm")) {
            if (LuckPermsAPI.hasPermission(owner, "group.ranger")) {
                return;
            }

            event.setCancelled(true);
            player.sendMessage(Component.text(message.replaceFirst("\\{0}", Rank.RANGER.getDisplayName())).asComponent().clickEvent(ClickEvent.openUrl("https://store.mineworld.net")));
            return;
        }

        if (command.startsWith("promote")) {
            if (LuckPermsAPI.hasPermission(owner, "group.ranger")) {
                return;
            }

            event.setCancelled(true);
            player.sendMessage(Component.text(message.replaceFirst("\\{0}", Rank.RANGER.getDisplayName())).asComponent().clickEvent(ClickEvent.openUrl("https://store.mineworld.net")));
            return;
        }

        if (command.startsWith("demote")) {
            if (LuckPermsAPI.hasPermission(owner, "group.ranger")) {
                return;
            }

            event.setCancelled(true);
            player.sendMessage(Component.text(message.replaceFirst("\\{0}", Rank.RANGER.getDisplayName())));
            return;
        }

        if (command.startsWith("name") || command.startsWith("setname") || command.startsWith("rename")) {
            if (LuckPermsAPI.hasPermission(owner, "group.administrator")) {
                return;
            }

            event.setCancelled(true);
            player.sendMessage(Component.text(message.replaceFirst("\\{0}", Rank.ADMINISTRATOR.getDisplayName())).asComponent().clickEvent(ClickEvent.openUrl("https://store.mineworld.net")));
            return;
        }

        if (command.startsWith("setbiome")) {
            if (LuckPermsAPI.hasPermission(owner, "group.elder")) {
                return;
            }

            event.setCancelled(true);
            player.sendMessage(Component.text(message.replaceFirst("\\{0}", Rank.ELDER.getDisplayName())).asComponent().clickEvent(ClickEvent.openUrl("https://store.mineworld.net")));
        }

    }
}
