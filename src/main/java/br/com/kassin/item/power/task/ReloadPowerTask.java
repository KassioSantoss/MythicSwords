package br.com.kassin.item.power.task;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.power.cooldown.MythicPowerCooldown;
import br.com.kassin.item.power.utils.ItemUtils;
import br.com.kassin.utils.Message;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.Map;

@Getter
public final class ReloadPowerTask extends BukkitRunnable {

    private final MythicPowerCooldown cooldown = MythicPowerCooldown.getInstance();
    private final String name;
    private final String itemId;

    private ReloadPowerTask(final String name, final String itemId) {
        this.name = name;
        this.itemId = itemId;
    }

    public static void start(String playerName, final String itemId) {
        ReloadPowerTask existingTask = TaskManager.getTask(playerName, itemId);
        if (existingTask == null) {
            ReloadPowerTask task = new ReloadPowerTask(playerName, itemId);
            TaskManager.addTask(playerName, itemId, task);
            task.runTaskTimer(MythicSwordsPlugin.getInstance(), 0, 20);
        }
    }

    @Override
    public void run() {
        Player player = Bukkit.getPlayerExact(name);

        if (player == null) {
            cancel();
            return;
        }

        if (!cooldown.isInCooldown(player, itemId) || !isHoldingItem(player, itemId)) {
            TaskManager.removeTask(player.getName(), itemId);
            cancel();
            return;
        }
        Message.ActionBar.send(player, "&6Cooldown do poder do item: &f" + cooldown.getCooldownTime(player, itemId));
    }

    private boolean isHoldingItem(Player player, String itemId) {
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        String currentItemId = ItemUtils.getItemId(itemInHand);
        return currentItemId != null && currentItemId.equals(itemId);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private final static class TaskManager {
        @Getter
        private static final Map<String, Map<String, ReloadPowerTask>> POWER_TASK_MAP = new HashMap<>();

        public static void addTask(String name, String itemId, ReloadPowerTask task) {
            POWER_TASK_MAP.computeIfAbsent(name, k -> new HashMap<>()).put(itemId, task);
        }

        public static Map<String, ReloadPowerTask> getPowerTaskMap(String playerName) {
            return POWER_TASK_MAP.getOrDefault(playerName, new HashMap<>());
        }

        public static ReloadPowerTask getTask(String name, String itemId) {
            return getPowerTaskMap(name).getOrDefault(itemId, null);
        }

        public static void removeTask(String playerName, String itemId) {
            Map<String, ReloadPowerTask> taskMap = POWER_TASK_MAP.get(playerName);
            if (taskMap == null) return;
            taskMap.remove(itemId);
            if (!taskMap.isEmpty()) return;
            POWER_TASK_MAP.remove(playerName);
        }
    }

}


