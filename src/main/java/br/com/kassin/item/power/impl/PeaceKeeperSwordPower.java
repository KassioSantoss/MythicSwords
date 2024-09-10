package br.com.kassin.item.power.impl;

import br.com.kassin.item.power.interfaces.MythicSwordPower;
import br.com.kassin.item.power.utils.MythicCache;
import br.com.kassin.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public final class PeaceKeeperSwordPower implements MythicSwordPower {
    private final static PotionEffect POTION_EFFECT = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 4);
    private final static Map<UUID, List<UUID>> ENTITIES = new HashMap<>();

    @Override
    public void activatePower(Player player) {
        paralyze(player);
        MythicCache.getINITIAL_USE_LOCATION_MAP().put(player.getUniqueId(), player.getLocation());
    }

    private void paralyze(final Player player) {
        List<Entity> entityList = player.getNearbyEntities(10, 10, 10);
        List<UUID> entityIds = new ArrayList<>();
        for (Entity entity : entityList) {
            applyEffect(entity);
            entityIds.add(entity.getUniqueId());
        }
        ENTITIES.put(player.getUniqueId(), entityIds);
        Message.Chat.sendMessage(player, "&6&lPoder Ativado! &6Entidades Paralizadas.");
    }

    private void applyEffect(final Entity entity) {
        if (entity instanceof Player) return;
        if (entity instanceof LivingEntity livingEntity)
            livingEntity.addPotionEffect(POTION_EFFECT);
    }

    public static void removeEffect(Player player) {
        for (UUID uuid : getEntities(player.getUniqueId())) {
            Entity entity = Bukkit.getEntity(uuid);
            if (entity instanceof LivingEntity livingEntity)
                livingEntity.removePotionEffect(POTION_EFFECT.getType());
            ENTITIES.clear();
        }
    }

    private static List<UUID> getEntities(UUID uuid) {
        return ENTITIES.get(uuid);
    }
}
