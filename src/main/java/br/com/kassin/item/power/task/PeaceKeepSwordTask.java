package br.com.kassin.item.power.task;

import br.com.kassin.MythicSwordsPlugin;
import br.com.kassin.item.power.impl.PeaceKeeperSwordPower;
import br.com.kassin.item.power.utils.MythicCache;
import br.com.kassin.utils.Message;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class PeaceKeepSwordTask extends BukkitRunnable {

    private final Particle.DustOptions dustOptionsGreen = new Particle.DustOptions(Color.GREEN, 8);
    private final Particle.DustOptions dustOptionsGray = new Particle.DustOptions(Color.GRAY, 8);
    private final List<Integer> ascendingList = new ArrayList<>();
    private final List<Integer> descendingList = new ArrayList<>();
    private final String name;
    private final int size = 15;
    private int heightDown = 9;
    private int heightUp = 0;
    private int count = 0;

    private PeaceKeepSwordTask(String name) {
        this.name = name;
    }

    public static void start(String name) {
        PeaceKeepSwordTask task = new PeaceKeepSwordTask(name);
        task.runTaskTimer(MythicSwordsPlugin.getInstance(), 0, 5);
    }

    @Override
    public void run() {

        Player player = Bukkit.getPlayerExact(name);

        if (player == null) {
            cancel();
            return;
        }

        if (count >= 40) {
            PeaceKeeperSwordPower.removeEffect(player);
            MythicCache.getINITIAL_USE_LOCATION_MAP().remove(player.getUniqueId());
            ascendingList.clear();
            descendingList.clear();
            cancel();
            Message.Chat.sendMessage(player, "&4Área Desativada!");
            return;
        }

        Location playerLocation = MythicCache.getLocation(player.getUniqueId());
        World world = player.getWorld();

        double halfSize = size / 2.0;
        double minX = playerLocation.getX() - halfSize;
        double maxX = playerLocation.getX() + halfSize;
        double minZ = playerLocation.getZ() - halfSize;
        double maxZ = playerLocation.getZ() + halfSize;

        if (!descendingList.contains(heightDown)) descendingList.add(heightDown);
        if (!ascendingList.contains(heightUp)) ascendingList.add(heightUp);

        for (int number : ascendingList) {
            for (double x = minX; x <= maxX; x++) {
                world.spawnParticle(Particle.REDSTONE, new Location(world, x, playerLocation.getY() + number, minZ), 1, dustOptionsGreen);
                world.spawnParticle(Particle.REDSTONE, new Location(world, x, playerLocation.getY() + number, maxZ), 1, dustOptionsGreen);
            }
            for (double z = minZ; z <= maxZ; z++) {
                world.spawnParticle(Particle.REDSTONE, new Location(world, minX, playerLocation.getY() + number, z), 1, dustOptionsGreen);
                world.spawnParticle(Particle.REDSTONE, new Location(world, maxX, playerLocation.getY() + number, z), 1, dustOptionsGreen);
            }
        }

        for (int number : descendingList) {
            for (double x = minX; x <= maxX; x++) {
                world.spawnParticle(Particle.REDSTONE, new Location(world, x, playerLocation.getY() + number, minZ), 1, dustOptionsGray);
                world.spawnParticle(Particle.REDSTONE, new Location(world, x, playerLocation.getY() + number, maxZ), 1, dustOptionsGray);
            }
            for (double z = minZ; z <= maxZ; z++) {
                world.spawnParticle(Particle.REDSTONE, new Location(world, minX, playerLocation.getY() + number, z), 1, dustOptionsGray);
                world.spawnParticle(Particle.REDSTONE, new Location(world, maxX, playerLocation.getY() + number, z), 1, dustOptionsGray);
            }
        }

        if (heightDown > 5) heightDown--;
        if (heightUp < 4) heightUp++;
        count++;
    }
}
