package br.com.kassin.item.power.utils;

import lombok.Getter;

import java.util.*;

@Getter
public class MythicCache {
    private static final Set<UUID> powerSet = new HashSet<>();

    private MythicCache() {
    }

    public static Set<UUID> get() {
        return powerSet;
    }

}
