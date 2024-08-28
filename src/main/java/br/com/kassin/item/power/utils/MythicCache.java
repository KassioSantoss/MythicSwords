package br.com.kassin.item.power.utils;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

@Getter
public class MythicCache {
    private static final Queue<UUID> powerQueue = new LinkedList<>();

    private MythicCache() {
    }

    public static Queue<UUID> get() {
        return powerQueue;
    }

}
