package br.com.kassin.item.power.utils;

import lombok.Getter;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MythicList {

    @Getter
    private static final Map<UUID, List<Entity>> entityList = new HashMap<>();

    public static List<Entity> getEntityByPlayer(UUID uuid) {
        return entityList.get(uuid);
    }
}
