package br.com.kassin;

import br.com.kassin.commands.MythicSwordsCommand;
import br.com.kassin.listeners.MythicItemListener;
import br.com.kassin.listeners.MythicUseItemListener;
import br.com.kassin.listeners.InstallResources;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MythicSwordsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        registerEvents(
                new InstallResources(),
                new MythicUseItemListener(),
                new MythicItemListener()
        );

        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    private void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("mythicItem")).setExecutor(new MythicSwordsCommand());
    }

    public static MythicSwordsPlugin getInstance() {
        return getPlugin(MythicSwordsPlugin.class);
    }

}
