package me.gamer.antiuuidspoof.listeners;

import me.gamer.antiuuidspoof.AntiUUIDSpoof;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.UUID;

public class GeneralListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {

        Player p = e.getPlayer();

        try {
            String pUuid = p.getUniqueId().toString().replace("-", "");
            String fUuid = AntiUUIDSpoof.getUUIDManager().fetchUUID(p.getName()).toString().replace("-", "");
            UUID realUUID = AntiUUIDSpoof.getUUIDManager().fetchUUID(p.getName());

            if (!pUuid.equalsIgnoreCase(fUuid)) {
                e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                e.setKickMessage(ChatColor.translateAlternateColorCodes('&', AntiUUIDSpoof.getInstance().getConfig().getString("kick-message")));
            }

            Bukkit.getServer().getOnlinePlayers().stream().filter(player -> player.hasPermission("antiuuidspoof.*"))
                    .forEach(player -> {

                        boolean silentBans = AntiUUIDSpoof.getInstance().getConfig().getBoolean("silent-bans");
                        String reason = AntiUUIDSpoof.getInstance().getConfig().getString("ban-reason");

                        TextComponent banButton = new TextComponent(ChatColor.translateAlternateColorCodes('&',
                                AntiUUIDSpoof.getInstance().getConfig().getString("ban-button")));
                        banButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ban " + realUUID + (silentBans ? " -s " : " ") + reason));
                        banButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&cClick here to ban this player.")).create()));

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                AntiUUIDSpoof.getInstance().getConfig().getString("staff-alert-message").replace("%player%", p.getName())));
                        player.spigot().sendMessage(banButton);
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
