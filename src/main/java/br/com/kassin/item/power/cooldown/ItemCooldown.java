package br.com.kassin.item.power.cooldown;

import java.util.HashMap;
import java.util.Map;

public final class ItemCooldown {
    private final Map<String, Long> cooldowns = new HashMap<>();

    public void setCooldown(String itemId, int seconds) {
        long cooldownEndTime = System.currentTimeMillis() + (seconds * 1000L);
        cooldowns.put(itemId, cooldownEndTime);
    }

    public boolean isInCooldown(String itemId) {
        Long cooldownEndTime = cooldowns.get(itemId);
        return cooldownEndTime != null && System.currentTimeMillis() < cooldownEndTime;
    }

    public int getRemainingTime(String itemId) {
        Long cooldownEndTime = cooldowns.get(itemId);
        if (cooldownEndTime == null) {
            return 0;
        }
        long remainingTime = cooldownEndTime - System.currentTimeMillis();
        return remainingTime > 0 ? (int) (remainingTime / 1000) : 0;
    }
}
