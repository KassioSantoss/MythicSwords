package br.com.kassin;

import br.com.kassin.commands.MythicSwordsCommand;
import br.com.kassin.listeners.MythicUseItemListener;
import br.com.kassin.resource.InstallResources;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MythicSwordsPlugin extends JavaPlugin {

    @Getter
    private static MythicSwordsPlugin instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        registerEvents(
                new InstallResources(),
                new MythicUseItemListener()
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
        getCommand("mythicItem").setExecutor(new MythicSwordsCommand());
    }

}
