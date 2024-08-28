package br.com.kassin.listeners;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.MythicSword;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public final class MythicCombatListener implements Listener {

    public MythicCombatListener() {
    }

    @EventHandler
    public void onDamage(PlayerInteractEvent event) {

        if (!event.getAction().isRightClick()) return;

        final Player player = event.getPlayer();

        String itemKey = getItemId(player.getInventory().getItemInMainHand());

        if (itemKey == null) return;

        if (itemKey.equals(MythicSword.WORM_HAMMER.getData())) {
            MythicSword.WORM_HAMMER.use(player);
        }
    }


    private String getItemId(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) return null;

        return Arrays.stream(MythicSword.values())
                .map(mythicItemID -> new NamespacedKey(MythicSwordsPlugin.getInstance(), mythicItemID.getData()))
                .filter(key -> itemStack.getItemMeta().getPersistentDataContainer().has(key))
                .findFirst()
                .map(NamespacedKey::getKey)
                .orElse(null);
    }

}
