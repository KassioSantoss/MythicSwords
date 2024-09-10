package br.com.kassin.item.power.utils;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.MythicSword;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ItemUtils {
    public static String getItemId(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) return null;

        return Arrays.stream(MythicSword.values())
                .map(mythicItemID -> new NamespacedKey(MythicSwordsPlugin.getInstance(), mythicItemID.getData()))
                .filter(key -> itemStack.getItemMeta().getPersistentDataContainer().has(key))
                .findFirst()
                .map(NamespacedKey::getKey)
                .orElse(null);
    }
}
