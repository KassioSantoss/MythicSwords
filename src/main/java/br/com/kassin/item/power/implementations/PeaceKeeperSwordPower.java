package br.com.kassin.item.power.implementations;

import br.com.kassin.item.power.cooldown.MythicPowerCooldown;
import br.com.kassin.item.power.interfaces.MythicSwordPower;
import br.com.kassin.item.power.utils.MythicCache;
import br.com.kassin.utils.Message;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class PeaceKeeperSwordPower implements MythicSwordPower {
    private static PotionEffect potionEffect;
    private static Map<UUID, List<Entity>> entities;

    public PeaceKeeperSwordPower() {
        potionEffect = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 4);
        entities = new HashMap<>();
    }

    @Override
    public void activatePower(Player player) {
        if (MythicPowerCooldown.getInstance().isInCooldown(player)) return;
        paralyze(player);
        MythicCache.getLocationMap().put(player.getUniqueId(), player.getLocation());
    }

    private void paralyze(final Player player) {
        List<Entity> entityList = player.getNearbyEntities(10, 10, 10);
        for (Entity entity : entityList) {
            applyEffect(entity);
            entities.put(player.getUniqueId(), entityList);
        }
        Message.Chat.sendMessage(player, "&6&lPoder Ativado! &6Entidades Paralizadas.");
    }

    private void applyEffect(final Entity entity) {
        if (entity instanceof Player) return;
        if (entity instanceof LivingEntity livingEntity)
            livingEntity.addPotionEffect(potionEffect);
    }

    public static void removeEffect(Player player) {
        for (Entity entity : getEntities(player.getUniqueId())) {
            if (entity instanceof LivingEntity livingEntity)
                livingEntity.removePotionEffect(potionEffect.getType());
            entities.clear();
        }
        Message.Chat.sendMessage(player, "&4Área Desativada!");
    }

    private static List<Entity> getEntities(UUID uuid) {
        return entities.get(uuid);
    }
}
