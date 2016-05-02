package com.thebubblenetwork.paintwars.map;

import com.thebubblenetwork.api.framework.util.mc.config.LocationUtil;
import com.thebubblenetwork.api.framework.util.mc.world.LocationObject;
import com.thebubblenetwork.api.game.maps.GameMap;
import com.thebubblenetwork.api.game.maps.MapData;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.*;

/**
 * The Bubble Network 2016
 * SkyFortress
 * 20/02/2016 {15:30}
 * Created February 2016
 */
public class PaintWarsMap extends GameMap {
    public PaintWarsMap(String name, MapData data, File yml, File zip) {
        super(name, data, yml, zip);
    }

    @SuppressWarnings("unchecked")
    public Map loadSetting(ConfigurationSection configurationSection) {
        Map map = new HashMap<>();

        Set<Cord> blueTeamFlagCords = new HashSet<>();
        Set<Cord> redTeamFlagCords = new HashSet<>();
        List<TeamsMapData> teamData = new ArrayList<>();
        Set<LocationObject> blueTeamFlags = new HashSet<>();
        Set<LocationObject> redTeamFlags = new HashSet<>();

        LocationObject blueTeamSpawn = LocationUtil.fromConfig(configurationSection.getConfigurationSection("teams.blue.spawn"));
        LocationObject redTeamSpawn = LocationUtil.fromConfig(configurationSection.getConfigurationSection("teams.red.spawn"));


        for (String blueFlag : configurationSection.getConfigurationSection("teams.blue.flags").getKeys(false)) {
            LocationObject object = LocationUtil.fromConfig(configurationSection.getConfigurationSection("teams.blue.flags." + blueFlag));
            blueTeamFlags.add(object);
            blueTeamFlagCords.add(Cord.fromLocation(object));
        }

        for (String redFlag : configurationSection.getConfigurationSection("teams.red.flags").getKeys(false)) {
            LocationObject object = LocationUtil.fromConfig(configurationSection.getConfigurationSection("teams.red.flags." + redFlag));
            redTeamFlags.add(object);
            redTeamFlagCords.add(Cord.fromLocation(object));
        }

        teamData.add(new TeamsMapData(redTeamSpawn, blueTeamSpawn, redTeamFlags, blueTeamFlags));

        map.put("teams", teamData);
        map.put("blueFlagCords", blueTeamFlagCords);
        map.put("redFlagCords", redTeamFlagCords);

        return map;
    }

    @SuppressWarnings("unchecked")
    public List<TeamsMapData> getTeamsMapData() {
        return ( List<TeamsMapData>) getSettings().get("teams");
    }


}
