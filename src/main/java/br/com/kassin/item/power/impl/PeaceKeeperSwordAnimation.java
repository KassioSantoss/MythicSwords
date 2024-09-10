package br.com.kassin.item.power.impl;

import br.com.kassin.item.power.interfaces.MythicSwordAnimation;
import br.com.kassin.item.power.task.PeaceKeepSwordTask;
import org.bukkit.entity.Player;

public final class PeaceKeeperSwordAnimation implements MythicSwordAnimation {

    @Override
    public void playAttackAnimation(Player player) {
        PeaceKeepSwordTask.start(player.getName());
    }

}
