package br.com.kassin.item.power.cooldown;

import br.com.kassin.item.power.utils.MythicCache;
import br.com.kassin.item.power.task.ReloadPowerTask;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class MythicPowerCooldown {

    private static MythicPowerCooldown instance;
    private static final HashMap<UUID, ItemCooldown> COOLDOWNS = new HashMap<>();

    public static MythicPowerCooldown getInstance() {
        if (instance == null) {
            instance = new MythicPowerCooldown();
        }
        return instance;
    }

    public boolean isInCooldown(Player player, String itemId) {
        UUID playerId = player.getUniqueId();
        ItemCooldown itemCooldown = COOLDOWNS.get(playerId);
        return itemCooldown != null && itemCooldown.isInCooldown(itemId);
    }

    public void setCooldown(Player player, String itemId, int seconds) {
        UUID playerId = player.getUniqueId();
        ItemCooldown itemCooldown = COOLDOWNS.computeIfAbsent(playerId, k -> new ItemCooldown());
        itemCooldown.setCooldown(itemId, seconds);
    }

    public int getCooldownTime(Player player, String itemId) {
        UUID playerId = player.getUniqueId();
        ItemCooldown itemCooldown = COOLDOWNS.get(playerId);
        return itemCooldown != null ? itemCooldown.getRemainingTime(itemId) : 0;
    }

}
