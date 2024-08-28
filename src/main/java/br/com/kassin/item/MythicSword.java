package br.com.kassin.item;

import br.com.kassin.item.power.cooldown.MythicPowerCooldown;
import br.com.kassin.item.power.implementations.WormHammerAnimation;
import br.com.kassin.item.power.implementations.WormHammerPower;
import br.com.kassin.item.power.interfaces.MythicSwordAnimation;
import br.com.kassin.item.power.interfaces.MythicSwordPower;
import br.com.kassin.utils.Message;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public enum MythicSword {

    WORM_HAMMER("&6&lWorm &4&lHammer", "worm-hammer", new ItemStack(Material.DIAMOND_SWORD), 100, new WormHammerAnimation(5, Color.RED), new WormHammerPower());

    private final String name;
    private final ItemStack item;
    private final int modelID;
    private final String data;
    private final MythicSwordAnimation animation;
    private final MythicSwordPower power;

    MythicSword(String name, String data, ItemStack item, int modelID, MythicSwordAnimation animation, MythicSwordPower power) {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        this.data = data;
        this.item = item;
        this.modelID = modelID;
        this.animation = animation;
        this.power = power;
    }

    public void use(Player player) {
        if (MythicPowerCooldown.getInstance().isInCooldown(player)) {
            Message.Chat.sendMessage(player, "&6Poder recarregando!");
            return;
        }
        power.activatePower(player);
        animation.playAttackAnimation(player);
        MythicPowerCooldown.getInstance().setCooldown(player, 10);
    }

}
