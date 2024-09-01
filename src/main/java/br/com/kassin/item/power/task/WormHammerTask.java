package br.com.kassin.item.power.task;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.power.utils.MythicList;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WormHammerTask extends BukkitRunnable {

    private final Map<UUID, List<Entity>> entityList = MythicList.getEntityList();
    private final Player player;
    private int count = 0;

    private WormHammerTask(Player player) {
        this.player = player;
    }

    public static void start(Player player) {
        WormHammerTask task = new WormHammerTask(player);
        task.runTaskTimer(MythicSwordsPlugin.getInstance(), 0, 1);
    }

    @Override
    public void run() {
        final List<Entity> entities = entityList.get(player.getUniqueId());
        final Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 3);

        if (count >= 50) {
            entityList.get(player.getUniqueId()).clear();
            cancel();
            return;
        }

        for (Entity entity : entities) {
            entity.getWorld().spawnParticle(Particle.REDSTONE, entity.getLocation(), 1, dustOptions);
        }
        count++;
    }

}




