package br.com.kassin.item;

import br.com.kassin.utils.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum MythicSwordType {

    WORM_HAMMER(ItemBuilder.of(new ItemStack(Material.DIAMOND_SWORD))
            .setName("&6&lWorm &4&lHammer")
            .setLore(" ")
            .setCustomModelData(100)
            .setItemMetaData("worm-hammer")
            .build(),
            "worm-hammer",
            100
    );

    private final ItemStack item;
    private final int modelID;
    private final String key;

    MythicSwordType(final ItemStack item, final String key, final int modelID) {
        this.item = item;
        this.key = key;
        this.modelID = modelID;
    }

}
