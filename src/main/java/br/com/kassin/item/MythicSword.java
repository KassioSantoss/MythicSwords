package br.com.kassin.item;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.power.cooldown.MythicPowerCooldown;
import br.com.kassin.item.power.impl.PeaceKeeperSwordAnimation;
import br.com.kassin.item.power.impl.PeaceKeeperSwordPower;
import br.com.kassin.item.power.impl.WormHammerAnimation;
import br.com.kassin.item.power.impl.WormHammerPower;
import br.com.kassin.item.power.interfaces.MythicSwordAnimation;
import br.com.kassin.item.power.interfaces.MythicSwordPower;
import br.com.kassin.item.power.task.ReloadPowerTask;
import br.com.kassin.utils.ItemBuilder;
import br.com.kassin.utils.Message;
import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

@Getter
public enum MythicSword {

    WORM_HAMMER(createSword(Material.DIAMOND_SWORD, "&6&lWorm &4&lHammer", "worm-hammer", 100),
            new WormHammerAnimation(5.8, Color.RED),
            new WormHammerPower()),

    PEACEKEEPER_SWORD(createSword(Material.NETHERITE_SWORD, "&a&lPea&b&lceKee&a&lper &b&lSword", "peacekeeper-sword", 101),
            new PeaceKeeperSwordAnimation(),
            new PeaceKeeperSwordPower());

    private final ItemStack item;
    private final String data;
    private final MythicSwordAnimation animation;
    private final MythicSwordPower power;

    MythicSword(ItemStack item, MythicSwordAnimation animation, MythicSwordPower power) {
        this.item = item;
        this.data = getData(item);
        this.animation = animation;
        this.power = power;
    }

    public void use(final Player player, final int cooldown) {
        if (MythicPowerCooldown.getInstance().isInCooldown(player, getData(item))) {
            Message.Chat.sendMessage(player, "&6Poder recarregando!");
            return;
        }
        power.activatePower(player);
        animation.playAttackAnimation(player);
        MythicPowerCooldown.getInstance().setCooldown(player, getData(item), cooldown);
        ReloadPowerTask.start(player.getName(), getData(item));
    }

    private static ItemStack createSword(final Material material, final String name, final String data, final int modelID) {
        return ItemBuilder.of(new ItemStack(material))
                .setName(name)
                .setCustomModelData(modelID)
                .setItemMetaData(data)
                .build();
    }

    private String getData(final ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        for (NamespacedKey keys : meta.getPersistentDataContainer().getKeys()) {
            String key = keys.getKey();
            return meta.getPersistentDataContainer().get(new NamespacedKey(MythicSwordsPlugin.getInstance(), key), PersistentDataType.STRING);
        }
        return null;
    }
}

