package net.mineworld.islandcreator.api;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.luckperms.api.model.user.User;
import net.mineworld.islandcreator.permission.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SuperiorAPI {

    public static void openPermissions(SuperiorPlayer player) {
        if (!hasOpenPermission(player, "group.ranger", Rank.RANGER)) {
            return;
        }

        SuperiorSkyblockAPI.getMenus().openPermissions(player, null, player.getIsland(), player.getPlayerRole());
    }

    public static void openIslandChest(SuperiorPlayer player) {
        if (!hasOpenPermission(player, "group.scout", Rank.SCOUT)) {
            return;
        }

        SuperiorSkyblockAPI.getMenus().openIslandChest(player, null, player.getIsland());
    }

    public static void openBank(SuperiorPlayer player) {
        if (!hasOpenPermission(player, "group.ranger", Rank.RANGER)) {
            return;
        }

        SuperiorSkyblockAPI.getMenus().openIslandBank(player, null, player.getIsland());
    }

    public static void openBiomes(SuperiorPlayer player) {
        if (!hasOpenPermission(player, "group.elder", Rank.ELDER)) {
            return;
        }

        SuperiorSkyblockAPI.getMenus().openBiomes(player, null, player.getIsland());
    }


    private static boolean hasOpenPermission(SuperiorPlayer player, String permission, Rank rank) {
        String message = ChatColor.RED + "The owner of this island needs at least " + rank.getDisplayName() + ChatColor.RED + " rank to use this feature. Purchase it at " + ChatColor.GOLD + "https://store.mineworld.net";
        Player asPlayer = player.asPlayer();
        if (asPlayer == null) {
            return false;
        }

        if (player.getIsland() == null) {
            return false;
        }

        User owner = LuckPermsAPI.getUser(player.getIsland().getOwner().getUniqueId());
        if (owner == null) {
            asPlayer.sendMessage(Component.text(ChatColor.RED + "The owner of this island is null. Please contact an administrator."));
            return false;
        }

        if (owner.getUniqueId().equals(asPlayer.getUniqueId())) {
            message = ChatColor.RED + "You need at least " + rank.getDisplayName() + ChatColor.RED + " rank to use this feature. Purchase it at " + ChatColor.GOLD + " https://store.mineworld.net";
        }

        if (!LuckPermsAPI.hasPermission(owner, permission)) {
            asPlayer.sendMessage(Component.text(message).asComponent().clickEvent(ClickEvent.openUrl("https://store.mineworld.net")));
            asPlayer.closeInventory();
            return false;
        }
        return true;
    }

    public static void setChests(Island island, int chests) {
        for (int i = 0; i < chests; i++) {
            island.setChestRows(i, 3);
        }
    }
}
