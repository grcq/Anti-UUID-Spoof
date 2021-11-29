package me.gamer.antiuuidspoof;

import me.gamer.antiuuidspoof.listeners.GeneralListener;
import me.gamer.antiuuidspoof.managers.OnlyProxyJoin;
import me.gamer.antiuuidspoof.managers.UUIDManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiUUIDSpoof extends JavaPlugin {

    private static UUIDManager uuidManager = null;
    private static AntiUUIDSpoof instance = null;
    private static OnlyProxyJoin onlyProxyJoin = null;

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        uuidManager = new UUIDManager();
        instance = this;

        this.getServer().getPluginManager().registerEvents(new GeneralListener(), this);

        onlyProxyJoin = new OnlyProxyJoin(this);
        onlyProxyJoin.init();

    }

    @Override
    public void onDisable() {
        uuidManager = null;
        instance = null;
        onlyProxyJoin = null;

        this.saveDefaultConfig();
    }

    public static AntiUUIDSpoof getInstance() {
        return instance;
    }

    public static UUIDManager getUUIDManager() {
        return uuidManager;
    }

    public static OnlyProxyJoin getOnlyProxyJoin() {
        return onlyProxyJoin;
    }
}
