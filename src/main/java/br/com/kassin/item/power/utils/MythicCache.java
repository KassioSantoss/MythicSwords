package br.com.kassin.item.power.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.Location;

import java.util.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MythicCache {
    @Getter
    private static final Map<UUID, Location> INITIAL_USE_LOCATION_MAP = new HashMap<>();

    public static Location getLocation(UUID uuid) {
        return INITIAL_USE_LOCATION_MAP.get(uuid);
    }

}
