package br.com.kassin.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Getter
@Setter
public class Cuboid implements Iterable<Block>, Cloneable, ConfigurationSerializable {

    private World world;
    private int xmax;
    private int xmin;
    private int ymax;
    private int ymin;
    private int zmax;
    private int zmin;

    //new
    public Cuboid(Cuboid cuboid) {
        this.world = cuboid.getWorld();
        this.xmax = cuboid.getXmax();
        this.xmin = cuboid.getXmin();
        this.ymax = cuboid.getYmax();
        this.ymin = cuboid.getYmin();
        this.zmax = cuboid.getZmax();
        this.zmin = cuboid.getZmin();
    }

    public Cuboid(Location l1, Location l2) {
        if (l1.getWorld().getName().equals(l2.getWorld().getName())) {
            this.world = l1.getWorld();
            this.xmax = Math.max(l1.getBlockX(), l2.getBlockX());
            this.xmin = Math.min(l1.getBlockX(), l2.getBlockX());
            this.ymax = Math.max(l1.getBlockY(), l2.getBlockY());
            this.ymin = Math.min(l1.getBlockY(), l2.getBlockY());
            this.zmax = Math.max(l1.getBlockZ(), l2.getBlockZ());
            this.zmin = Math.min(l1.getBlockZ(), l2.getBlockZ());
        }
    }

    public Cuboid(int xmax, int xmin, int ymax, int ymin, int zmax, int zmin, World world) {
        this.world = world;
        this.xmax = xmax;
        this.xmin = xmin;
        this.ymax = ymax;
        this.ymin = ymin;
        this.zmax = zmax;
        this.zmin = zmin;
    }

    public Cuboid(Map<String, Object> map) {
        this.xmax = (Integer) map.get("xmax");
        this.xmin = (Integer) map.get("xmin");
        this.ymax = (Integer) map.get("ymax");
        this.ymin = (Integer) map.get("ymin");
        this.zmax = (Integer) map.get("zmax");
        this.zmin = (Integer) map.get("zmin");
        this.world = Bukkit.getServer().getWorld(map.get("world").toString());
    }

    @Override
    public @NotNull Iterator<Block> iterator() {
        return new CuboidIterator(new Cuboid(xmax, xmin, ymax, ymin, zmax, zmin, world));
    }


    public boolean hasPlayerInside(Player player) {
        Location loc = player.getLocation();

        if (!loc.getWorld().equals(world)) {
            return false;
        }

        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();

        return x >= xmin && x <= xmax && y >= ymin && y <= ymax && z >= zmin && z <= zmax;
    }

    public boolean hasBlockInside(Block block) {
        Location loc = block.getLocation();
        if (xmin <= loc.getX() && xmax >= loc.getX() && ymin <= loc.getY() && ymax >= loc.getY() && zmin <= loc.getZ() && zmax >= loc.getX() && world.getName().equals(loc.getWorld().getName())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("world", getWorld().toString());
        map.put("xmax", getXmax());
        map.put("xmin", getXmin());
        map.put("ymax", getYmax());
        map.put("ymin", getYmin());
        map.put("zmax", getZmax());
        map.put("zmin", getZmin());
        return map;
    }

    //-----------------------------------------------------

    public class CuboidIterator implements Iterator<Block> {

        Cuboid cci;
        World wci;
        int baseX;
        int baseY;
        int baseZ;
        int sizeX;
        int sizeY;
        int sizeZ;
        private int x, y, z;
        ArrayList<Block> blocks;
        Map<Location, Material> blocks2;
        ArrayList<Location> blocks3;

        public CuboidIterator(Cuboid c) {
            this.cci = c;
            this.wci = c.getWorld();
            baseX = getXmin();
            baseY = getYmin();
            baseZ = getZmin();
            sizeX = Math.abs(getXmax() - getXmin()) + 1;
            sizeY = Math.abs(getYmax() - getYmin()) + 1;
            sizeZ = Math.abs(getZmax() - getZmin()) + 1;
            x = y = z = 0;
        }

        public Cuboid getCuboid() {
            return cci;
        }

        public boolean hasNext() {
            return x < sizeX && y < sizeY && z < sizeZ;
        }

        public Block next() {
            Block b = world.getBlockAt(baseX + x, baseY + y, baseZ + z);
            if (++x >= sizeX) {
                x = 0;
                if (++y >= sizeY) {
                    y = 0;
                    ++z;
                }
            }
            return b;
        }

        public void remove() {
            // nop
        }

        public Map<Location, Material> getBlockAtLocations() {
            blocks2 = new HashMap<Location, Material>();
            for (int x = cci.getXmin(); x <= cci.getXmax(); x++) {
                for (int y = cci.getYmin(); y <= cci.getYmax(); y++) {
                    for (int z = cci.getZmin(); z <= cci.getZmax(); z++) {
                        blocks2.put(new Location(cci.getWorld(), x, y, z), getWorld().getBlockAt(x, y, z).getType());
                    }
                }
            }
            return blocks2;
        }

        public Collection<Location> getLocations() {
            blocks3 = new ArrayList<Location>();
            for (int x = cci.getXmin(); x <= cci.getXmax(); x++) {
                for (int y = cci.getYmin(); y <= cci.getYmax(); y++) {
                    for (int z = cci.getZmin(); z <= cci.getZmax(); z++) {
                        blocks3.add(new Location(wci, x, y, z));
                    }
                }
            }
            return blocks3;
        }

        public Collection<Block> iterateBlocks() {
            blocks = new ArrayList<Block>();
            for (int x = cci.getXmin(); x <= cci.getXmax(); x++) {
                for (int y = cci.getYmin(); y <= cci.getYmax(); y++) {
                    for (int z = cci.getZmin(); z <= cci.getZmax(); z++) {
                        blocks.add(cci.getWorld().getBlockAt(x, y, z));
                    }
                }
            }
            return blocks;
        }

    }

    //-----------------------------------------------------

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public Cuboid clone() throws CloneNotSupportedException {
        return new Cuboid(this);
    }
}