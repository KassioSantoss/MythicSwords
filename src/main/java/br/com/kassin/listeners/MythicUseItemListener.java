package br.com.kassin.listeners;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.MythicSword;
import br.com.kassin.item.power.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public final class MythicUseItemListener implements Listener {

    @EventHandler
    public void onDamage(PlayerInteractEvent event) {

        if (!event.getAction().isRightClick()) return;

        final Player player = event.getPlayer();

        String itemKey = ItemUtils.getItemId(player.getInventory().getItemInMainHand());

        if (itemKey == null) return;

        if (itemKey.equals(MythicSword.WORM_HAMMER.getData())) {
            MythicSword.WORM_HAMMER.use(player, 10);
        }
        if (itemKey.equals(MythicSword.PEACEKEEPER_SWORD.getData())) {
            MythicSword.PEACEKEEPER_SWORD.use(player, 15);
        }
    }

}
