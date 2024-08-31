package br.com.kassin.item.power.utils;

import lombok.Getter;
import org.bukkit.Location;
import java.util.*;

@Getter
public final class MythicCache {
    private static final Set<UUID> powerSet = new HashSet<>();
    @Getter
    private static final Map<UUID, Location> locationMap = new HashMap<>();

    private MythicCache() {
    }

    public static Set<UUID> get() {
        return powerSet;
    }

    public static Location getLocation(UUID uuid) {
        return locationMap.get(uuid);
    }

}
