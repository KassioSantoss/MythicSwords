package br.com.kassin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class InstallResources implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setResourcePack("https://download.mc-packs.net/pack/7e48c02187b7016debd0ddd6861b95cbc216682e.zip");
    }

}
