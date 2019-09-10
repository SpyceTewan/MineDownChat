package at.tewan.minedownchat;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MinedownPlaceholderExpansion extends PlaceholderExpansion {

    private Plugin plugin;

    public MinedownPlaceholderExpansion(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "minedown";
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {

        if(identifier.equalsIgnoreCase("message")) {
            System.out.println(Main.message);
            return Main.message;
        }

        return null;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }
}
