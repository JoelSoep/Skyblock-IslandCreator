package net.mineworld.islandcreator.api;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.PermissionHolder;
import net.luckperms.api.model.data.DataType;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;

public class LuckPermsAPI {

    private static final LuckPerms api = LuckPermsProvider.get();

    public static String getGroupName(Player player) {
        User user = api.getUserManager().getUser(player.getUniqueId());

        if (user == null) {
            user = api.getUserManager().loadUser(player.getUniqueId()).join();

            if (user == null) {
                Bukkit.getLogger().warning("Luckperms user " + player.getName() + " is null.");
                return null;
            }
        }

        return user.getPrimaryGroup();
    }

    public static Group getGroup(Player player) {
        String rank = getGroupName(player);
        if (rank == null) {
            return null;
        }

        return api.getGroupManager().getGroup(rank);
    }

    public static User getUser(Player player) {
        User user = api.getUserManager().getUser(player.getUniqueId());

        if (user == null) {
            CompletableFuture<User> future = CompletableFuture.supplyAsync(() -> api.getUserManager().loadUser(player.getUniqueId()).join());
            try {
                user = future.get();
            } catch (Exception e) {
                Bukkit.getLogger().log(Level.WARNING, "Luckperms user " + player.getName() + " is null (Async response).");
            }


            if (user == null) {
                Bukkit.getLogger().warning("Luckperms user " + player.getName() + " is null.");
                return null;
            }
        }

        return user;
    }

    public static User getUser(UUID uuid) {
        User user = api.getUserManager().getUser(uuid);

        if (user == null) {
            CompletableFuture<User> future = CompletableFuture.supplyAsync(() -> api.getUserManager().loadUser(uuid).join());
            try {
                user = future.get();
            } catch (Exception e) {
                Bukkit.getLogger().log(Level.WARNING, "Luckperms user with uuid " + uuid + " is null (Async response).");
            }

            if (user == null) {
                Bukkit.getLogger().warning("Luckperms user with uuid " + uuid + " is null.");
                return null;
            }
        }

        return user;
    }

    public static boolean hasPermission(User user, String permission) {
        return user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }
}
