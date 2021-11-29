package me.gamer.antiuuidspoof.managers;

import me.gamer.antiuuidspoof.AntiUUIDSpoof;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;

import java.net.InetAddress;
import java.util.List;

public class OnlyProxyJoin {

    private boolean enabled;
    private final AntiUUIDSpoof instance;

    public OnlyProxyJoin() {
        this.instance = AntiUUIDSpoof.getInstance();
    }

    public OnlyProxyJoin(AntiUUIDSpoof instance) {
        this.instance = instance;
    }

    public void init() {
        this.enabled = this.instance.getConfig().getBoolean("only-proxy-join");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isConnectingToProxy(PlayerLoginEvent e) {

        final String address = e.getAddress().toString().replace("/", "");

        return !address.equalsIgnoreCase("localhost") && !address.equalsIgnoreCase("127.0.0.1") && !address.equalsIgnoreCase("0.0.0.0") && !address.equalsIgnoreCase("192.168.0.1") && !address.equalsIgnoreCase("192.168.0.0");
    }
}
