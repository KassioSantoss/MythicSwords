package br.com.kassin.item.power.implementations;

import br.com.kassin.item.power.interfaces.MythicSwordPower;
import br.com.kassin.item.power.utils.MythicList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WormHammerPower implements MythicSwordPower {

    private final Map<UUID, List<Entity>> entityList = MythicList.getEntityList();

    @Override
    public void activatePower(Player player) {
        impulse(player);
    }

    private void impulse(final Player player) {
        List<Entity> entities = player.getNearbyEntities(6, 2, 6);
        entityList.put(player.getUniqueId(), entities);

        for (Entity entity : entities) {
            Vector playerToEntity = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
            playerToEntity.setY(0.6);
            Vector impulseDirection = playerToEntity.multiply(1.8);
            entity.setVelocity(impulseDirection);
        }
    }

}
