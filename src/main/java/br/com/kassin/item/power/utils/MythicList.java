package br.com.kassin.item.power.utils;

import lombok.Getter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MythicList {

    @Getter
    private static final Map<UUID, List<UUID>> entityList = new HashMap<>();

    public static List<UUID> getEntityByPlayer(UUID uuid) {
        return entityList.get(uuid);
    }
}
