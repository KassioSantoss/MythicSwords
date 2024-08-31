package br.com.kassin.item.power.implementations;

import br.com.kassin.item.power.cooldown.MythicPowerCooldown;
import br.com.kassin.item.power.interfaces.MythicSwordAnimation;
import br.com.kassin.item.power.task.PeaceKeepSwordTask;
import org.bukkit.entity.Player;

public final class PeaceKeeperSwordAnimation implements MythicSwordAnimation {

    @Override
    public void playAttackAnimation(Player player) {
        if (MythicPowerCooldown.getInstance().isInCooldown(player)) return;
        PeaceKeepSwordTask.start(player);
    }

}
