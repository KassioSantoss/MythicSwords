package br.com.kassin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class InstallResources implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setResourcePack("https://download.mc-packs.net/pack/f57c2a38b66f7d0b61067a63f69ef278093ff278.zip");
    }

}
