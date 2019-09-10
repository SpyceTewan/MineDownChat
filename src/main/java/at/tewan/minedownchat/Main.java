package at.tewan.minedownchat;

import de.themoep.minedown.MineDownParser;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private MineDownParser mineDown;
    private String format;

    public static String message;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        mineDown = new MineDownParser();
        format = getConfig().getString("format");
        new MinedownPlaceholderExpansion(this).register();

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        mineDown.reset();

        message = e.getMessage();

        String formattedMessage = PlaceholderAPI.setPlaceholders(e.getPlayer(), format);

        BaseComponent[] component = mineDown.parse(formattedMessage).create();

        for(Player p : e.getRecipients()) {
            p.spigot().sendMessage(component);
        }

        System.out.println(e.getFormat());
    }
}