package net.mineworld.islandcreator.permission;

import org.bukkit.ChatColor;
public enum Rank {
    SCOUT(ChatColor.translateAlternateColorCodes('&', "&e&lSCOUT")),
    RANGER(ChatColor.translateAlternateColorCodes('&', "&d&lRANGER")),
    SENTINEL(ChatColor.translateAlternateColorCodes('&', "&F&F&D&7&0&0&lSENTINEL")),
    HERO(ChatColor.translateAlternateColorCodes('&', "&2&e&7&d&c&7HERO")),
    ELDER(ChatColor.translateAlternateColorCodes('&', "&f&b&8&0&0&e&lE&f&c&7&7&0&b&lL&f&c&6&e&0&7&lD&f&d&6&4&0&4&lE&f&d&5&b&0&0&lR")),
    EMPEROR(ChatColor.translateAlternateColorCodes('&', "&c&e&0&0&a&0&lE&c&3&0&8&a&7&lM&b&9&1&0&a&d&lP&a&e&1&8&b&4&lE&a3&1&f&b&b&lR&9&9&2&7&c&1&lO&8&e&2&f&c&8&lR")),
    ADMINISTRATOR(ChatColor.translateAlternateColorCodes('&', "&c&f&2&b&3&4&lADMIN"));

    private final String displayName;

    Rank(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
