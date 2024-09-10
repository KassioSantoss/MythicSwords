package br.com.kassin.item.power.impl;

import br.com.kassin.item.power.interfaces.MythicSwordAnimation;
import br.com.kassin.item.power.task.WormHammerTask;
import lombok.AllArgsConstructor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

@AllArgsConstructor
public final class WormHammerAnimation implements MythicSwordAnimation {

    private final double radius;
    private final Color color;

    @Override
    public void playAttackAnimation(Player player) {
        createCircleEffect(player, radius, color);
        impulseEffect(player.getName());
    }

    private void createCircleEffect(final Player player, final double radius, final Color color) {
        Particle.DustOptions dustOptions = new Particle.DustOptions(color, 4);

        for (int i = 0; i <= 360; i += 2) {
            double radians = Math.toRadians(i);
            double x = radius * Math.cos(radians);
            double z = radius * Math.sin(radians);
            Location location = player.getLocation().add(x, 0.3, z);
            player.getWorld().spawnParticle(Particle.REDSTONE, location, 1, dustOptions);
        }
    }

    private void impulseEffect(final String name) {
        WormHammerTask.start(name);
    }

}
