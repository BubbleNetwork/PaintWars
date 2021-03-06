package com.thebubblenetwork.paintwars.map;

import com.thebubblenetwork.api.framework.util.mc.world.LocationObject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.logging.Level;

public class TeamsMapData {
    private LocationObject blueTeamSpawn;
    private LocationObject redTeamSpawn;
    private Set<LocationObject> blueTeamFlags;
    private Set<LocationObject> redTeamFlags;

    private Player ifassigned;


    public TeamsMapData(LocationObject redTeamSpawn, LocationObject blueTeamSpawn, Set<LocationObject> redTeamFlags, Set<LocationObject> blueTeamFlags) {
        this.redTeamSpawn = redTeamSpawn;
        this.blueTeamSpawn = blueTeamSpawn;
        this.redTeamFlags = redTeamFlags;
        this.blueTeamFlags = blueTeamFlags;
    }


    public Player getIfassigned() {
        return ifassigned;
    }

    public void setIfassigned(Player ifassigned) {
        this.ifassigned = ifassigned;
    }

    public Set<LocationObject> getBlueTeamFlags() { return blueTeamFlags; }

    public Set<LocationObject> getRedTeamFlags() {
        return redTeamFlags;
    }

    public LocationObject getBlueTeamSpawn() {
        return blueTeamSpawn;
    }

    public LocationObject getRedTeamSpawn() {
        return redTeamSpawn;
    }

    /*public void fillChests(World world, PregeneratedChest generation) {
        for (LocationObject object : getChests()) {
            try {
                Block b = object.toLocation(world).getBlock();
                if (b != null && b.getType() == Material.CHEST) {
                    Chest c = (Chest) b.getState();
                    generation.apply(c);
                } else {
                    throw new IllegalArgumentException("An island was missing a chest at " + object.toString());
                }
            } catch (Throwable throwable) {
                Bukkit.getLogger().log(Level.SEVERE, "An error occurred whilst filling chests", throwable);
            }
        }
    }*/
}
