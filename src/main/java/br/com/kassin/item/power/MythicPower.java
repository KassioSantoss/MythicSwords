package br.com.kassin.item.power;

import br.com.kassin.effects.EffectsManager;
import org.bukkit.Color;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class MythicPower implements Power {

    @Override
    public void wormHammerPower(Entity entity) {
        EffectsManager.createCircle(entity, 4, Color.RED);
        if (!(entity instanceof Player player)) return;
        EffectsManager.impulse(player);
    }

}
