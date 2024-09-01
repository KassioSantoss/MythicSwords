package br.com.kassin.item.power.implementations;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.power.cooldown.MythicPowerCooldown;
import br.com.kassin.item.power.interfaces.MythicSwordAnimation;
import br.com.kassin.item.power.task.WormHammerTask;
import br.com.kassin.item.power.utils.MythicList;
import lombok.AllArgsConstructor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public final class WormHammerAnimation implements MythicSwordAnimation {

    private final Map<UUID, List<Entity>> entityList = MythicList.getEntityList();
    private final double radius;
    private final Color color;

    @Override
    public void playAttackAnimation(Player player) {
        if (MythicPowerCooldown.getInstance().isInCooldown(player)) return;
        createCircleEffect(player, radius, color);
        impulseEffect(player);
    }

    private void createCircleEffect(final Player player, final double radius, final Color color) {
        Particle.DustOptions dustOptions = new Particle.DustOptions(color, 3);

        for (int i = 0; i <= 360; i += 5) {
            double radians = Math.toRadians(i);
            double x = radius * Math.cos(radians);
            double z = radius * Math.sin(radians);
            Location location = player.getLocation().add(x, 0.3, z);
            player.getWorld().spawnParticle(Particle.REDSTONE, location, 1, dustOptions);
        }
    }

    private void impulseEffect(final Player player) {
        WormHammerTask.start(player);
    }

}
