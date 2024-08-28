package br.com.kassin.item.power.task;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.power.utils.MythicCache;
import br.com.kassin.item.power.cooldown.MythicPowerCooldown;
import br.com.kassin.utils.Message;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Set;
import java.util.UUID;

@Getter
public class ReloadPowerTask extends BukkitRunnable {

    private final Set<UUID> powerSet = MythicCache.get();
    private final MythicPowerCooldown cooldown = MythicPowerCooldown.getInstance();
    private final Player player;

    private ReloadPowerTask(final Player player) {
        this.player = player;
    }

    public static void start(final Player player) {
        ReloadPowerTask reloadPowerTask = new ReloadPowerTask(player);
        reloadPowerTask.runTaskTimer(MythicSwordsPlugin.getInstance(), 20, 20);
    }

    @Override
    public void run() {
        UUID uuid = player.getUniqueId();

        if (powerSet.contains(uuid)) {
            loadCount();
            return;
        }
        powerSet.remove(uuid);
        cancel();
    }

    private void loadCount() {
        if (cooldown.isInCooldown(player)) {
            Message.ActionBar.send(player, "&6&lPoder recarregando!&6 Faltam "
                    + cooldown.getCooldownTime(player) + "&6 segundos.");
        }
    }

}

