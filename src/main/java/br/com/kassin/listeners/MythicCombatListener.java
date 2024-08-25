package br.com.kassin.listeners;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.MythicSwordType;
import br.com.kassin.item.power.MythicPower;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public final class MythicCombatListener implements Listener {

    private final MythicPower mythicPower;

    public MythicCombatListener() {
        mythicPower = new MythicPower();
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player attacker)) return;

        String itemKey = getItemId(attacker.getInventory().getItemInMainHand());

        if (itemKey == null) return;

        if (itemKey.equals(MythicSwordType.WORM_HAMMER.getKey())) {
            mythicPower.wormHammerPower(attacker);
        }
    }

    private String getItemId(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) return null;

        return Arrays.stream(MythicSwordType.values())
                .map(mythicItemID -> new NamespacedKey(MythicSwordsPlugin.getInstance(), mythicItemID.getKey()))
                .filter(key -> itemStack.getItemMeta().getPersistentDataContainer().has(key))
                .findFirst()
                .map(NamespacedKey::getKey)
                .orElse(null);
    }

}
