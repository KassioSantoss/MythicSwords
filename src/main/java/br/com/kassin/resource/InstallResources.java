package br.com.kassin.resource;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class InstallResources implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setResourcePack("https://download.mc-packs.net/pack/ddd17ad31de4cabb2b0764c0b233e4834387eabf.zip");
    }

}
