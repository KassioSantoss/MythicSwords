package br.com.kassin.item.power.interfaces;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface MythicSwordPower {
    default void activatePower(final Player player) {}
    default void activatePower(final Entity entity) {}
}
