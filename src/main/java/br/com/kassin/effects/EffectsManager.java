package br.com.kassin.effects;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import java.util.List;

public class EffectsManager implements MythicEffects {

    public static void createCircle(final Entity entity, final double radius, final Color color) {
        for (int i = 0; i <= 360; i += 5) {
            double radians = Math.toRadians(i);

            double x = radius * Math.cos(radians);
            double z = radius * Math.sin(radians);
            Particle.DustOptions dustOptions = new Particle.DustOptions(color, 2);

            Location location = entity.getLocation().add(x, 0.3, z);
            entity.getWorld().spawnParticle(Particle.REDSTONE, location, 1, dustOptions);
        }
    }

    public static void impulse(final Player player) {
        List<Entity> entities = player.getNearbyEntities(4, 2, 4);

        for (Entity entity : entities) {
            Vector playerToEntity = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
            playerToEntity.setY(0.5);
            Vector impulseDirection = playerToEntity.multiply(1.5);
            entity.setVelocity(impulseDirection);
        }
        entities.clear();
    }

}
