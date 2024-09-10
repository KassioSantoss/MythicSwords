package br.com.kassin.item.power.impl;

import br.com.kassin.item.power.cooldown.MythicPowerCooldown;
import br.com.kassin.item.power.interfaces.MythicSwordPower;
import br.com.kassin.item.power.utils.MythicList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public final class WormHammerPower implements MythicSwordPower {

    private final Map<UUID, List<UUID>> entityList = MythicList.getEntityList();

    @Override
    public void activatePower(Player player) {
        impulse(player);
    }

    private void impulse(final Player player) {
        List<Entity> entities = player.getNearbyEntities(6, 2, 6);
        entityList.put(player.getUniqueId(),entities.stream().map(Entity::getUniqueId).collect(Collectors.toList()));

        for (Entity entity : entities) {
            Vector playerToEntity = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
            playerToEntity.setY(0.6);
            Vector impulseDirection = playerToEntity.multiply(2);
            entity.setVelocity(impulseDirection);
        }
    }

}
