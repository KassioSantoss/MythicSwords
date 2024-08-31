package br.com.kassin.item.power.task;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.power.implementations.PeaceKeeperSwordPower;
import br.com.kassin.item.power.utils.MythicCache;
import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.*;

@Getter
public final class PeaceKeepSwordTask extends BukkitRunnable {

    private final Player player;
    private int count = 0;
    private final int size = 15;
    private final int height = 10;
    private final Random random;
    private final Particle.DustOptions dustOptions;

    private PeaceKeepSwordTask(Player player) {
        this.player = player;
        random = new Random();
        this.dustOptions = new Particle.DustOptions(Color.YELLOW, 5);
    }

    public static void start(Player player) {
        PeaceKeepSwordTask task = new PeaceKeepSwordTask(player);
        task.runTaskTimer(MythicSwordsPlugin.getInstance(), 0, 10);
    }

    @Override
    public void run() {

        if (count >= 20) {
            PeaceKeeperSwordPower.removeEffect(player);
            cancel();
            return;
        }

        Location playerLocation = MythicCache.getLocation(player.getUniqueId());
        World world = player.getWorld();

        double halfSize = size / 2.0;
        double minX = playerLocation.getX() - halfSize;
        double maxX = playerLocation.getX() + halfSize;
        double minZ = playerLocation.getZ() - halfSize;
        double maxZ = playerLocation.getZ() + halfSize;

        for (int y = 0; y <= height; y++) {
            for (double x = minX; x <= maxX; x++) {
                world.spawnParticle(Particle.REDSTONE, new Location(world, x, playerLocation.getY() + y, minZ), 1, dustOptions);
                world.spawnParticle(Particle.REDSTONE, new Location(world, x, playerLocation.getY() + y, maxZ), 1, dustOptions);
            }

            for (double z = minZ; z <= maxZ; z++) {
                world.spawnParticle(Particle.REDSTONE, new Location(world, minX, playerLocation.getY() + y, z), 1, dustOptions);
                world.spawnParticle(Particle.REDSTONE, new Location(world, maxX, playerLocation.getY() + y, z), 1, dustOptions);
            }
        }
        count++;
    }
}

