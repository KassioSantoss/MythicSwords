package br.com.kassin.item.power.task;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.power.utils.MythicList;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class WormHammerTask extends BukkitRunnable {

    private final Map<UUID, List<UUID>> entityList = MythicList.getEntityList();
        private final Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 4);
    private final String name;
    private int count = 0;

    private WormHammerTask(String name) {
        this.name = name;
    }

    public static void start(String name) {
        WormHammerTask task = new WormHammerTask(name);
        task.runTaskTimer(MythicSwordsPlugin.getInstance(), 0, 1);
    }

    @Override
    public void run() {

        Player player = Bukkit.getPlayerExact(name);

        if (player == null) return;

        if (count >= 50) {
            entityList.get(player.getUniqueId()).clear();
            cancel();
            return;
        }

        if (entityList.isEmpty()) {
            cancel();
            return;
        }

        for (UUID uuid : entityList.get(player.getUniqueId())) {
            Entity entity = Bukkit.getEntity(uuid);
            if (entity != null) {
                entity.getWorld().spawnParticle(Particle.REDSTONE, entity.getLocation(), 1, dustOptions);
            }
        }
        count++;
    }
}




