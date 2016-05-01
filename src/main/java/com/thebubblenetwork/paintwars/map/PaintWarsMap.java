package com.thebubblenetwork.paintwars.map;

import com.thebubblenetwork.api.framework.util.mc.config.LocationUtil;
import com.thebubblenetwork.api.framework.util.mc.world.LocationObject;
import com.thebubblenetwork.api.game.maps.GameMap;
import com.thebubblenetwork.api.game.maps.MapData;
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

        Set<Cord> cords = new HashSet<>();
        List<TeamsMapData> teams = new ArrayList<>();

        for (String team : configurationSection.getConfigurationSection("teams").getKeys(false)) {
            String section = "teams." + team;
            LocationObject spawn = LocationUtil.fromConfig(configurationSection.getConfigurationSection(section + ".spawn"));
            Set<LocationObject> flags = new HashSet<>();
            for (String flag : configurationSection.getConfigurationSection(section + ".flags").getKeys(false)) {
                LocationObject object = LocationUtil.fromConfig(configurationSection.getConfigurationSection(section + ".flags." + flag));
                flags.add(object);
                cords.add(Cord.fromLocation(object));
            }
            teams.add(new TeamsMapData(flags, spawn));
        }

        map.put("teams", teams);
        map.put("cords", cords);

        return map;
    }

    @SuppressWarnings("unchecked")
    public List<TeamsMapData> getTeamsMapData() {
        return ( List<TeamsMapData>) getSettings().get("teams");
    }

    @SuppressWarnings("unchecked")
    public Set<Cord> getCordSet() {
        return (Set<Cord>) getSettings().get("cords");
    }

    @SuppressWarnings("unchecked")
    public LocationObject getCrownLocation() {
        return (LocationObject) getSettings().get("crown");
    }
}
