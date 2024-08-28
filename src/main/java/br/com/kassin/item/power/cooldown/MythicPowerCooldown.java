package br.com.kassin.item.power.cooldown;

import br.com.kassin.item.power.utils.MythicCache;
import br.com.kassin.item.power.task.ReloadPowerTask;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MythicPowerCooldown {
    private static MythicPowerCooldown instance;
    private static final HashMap<UUID, Long> cooldowns = new HashMap<>();

    private MythicPowerCooldown() {
    }

    public static MythicPowerCooldown getInstance() {
        if (instance == null) {
            instance = new MythicPowerCooldown();
        }
        return instance;
    }

    public boolean isInCooldown(Player player) {
        UUID playerId = player.getUniqueId();
        if (!cooldowns.containsKey(playerId)) {
            return false;
        }

        long cooldownEndTime = cooldowns.get(playerId);
        return System.currentTimeMillis() < cooldownEndTime;
    }

    public void setCooldown(Player player, int seconds) {
        long cooldownEndTime = System.currentTimeMillis() + (seconds * 1000L);
        cooldowns.put(player.getUniqueId(), cooldownEndTime);

        MythicCache.get().add(player.getUniqueId());
        ReloadPowerTask.start(player);
    }

    public int getCooldownTime(Player player) {
        UUID playerId = player.getUniqueId();
        if (!cooldowns.containsKey(playerId)) {
            return 0;
        }

        long cooldownEndTime = cooldowns.get(playerId);
        return (int) ((cooldownEndTime - System.currentTimeMillis()) / 1000);
    }
}
