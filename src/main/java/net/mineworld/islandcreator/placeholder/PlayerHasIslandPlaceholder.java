package net.mineworld.islandcreator.placeholder;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerHasIslandPlaceholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "mineworld";
    }

    @Override
    public @NotNull String getAuthor() {
        return "JoelSoep";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.2";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (identifier.equals("player_has_island")) {
            return SuperiorSkyblockAPI.getPlayer(player).hasIsland() ? "true" : "false";
        }
        return null;
    }
}
