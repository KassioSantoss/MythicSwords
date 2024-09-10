package br.com.kassin.listeners;

import br.com.kassin.item.MythicSword;
import br.com.kassin.item.power.cooldown.MythicPowerCooldown;
import br.com.kassin.item.power.task.ReloadPowerTask;
import br.com.kassin.item.power.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;


public final class MythicItemListener implements Listener {

    @EventHandler
    public void onActive(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItem(event.getNewSlot());

        if (itemStack == null) return;

        String data = ItemUtils.getItemId(itemStack);

        if (data == null) return;

        if (!MythicPowerCooldown.getInstance().isInCooldown(player, data)) return;

        if (data.equals(MythicSword.WORM_HAMMER.getData())) {
            ReloadPowerTask.start(player.getName(), MythicSword.WORM_HAMMER.getData());
        }
        if (data.equals(MythicSword.PEACEKEEPER_SWORD.getData())) {
            ReloadPowerTask.start(player.getName(), MythicSword.PEACEKEEPER_SWORD.getData());
        }
    }

}
