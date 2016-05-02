package com.thebubblenetwork.paintwars.teams;


import com.thebubblenetwork.api.framework.player.BukkitBubblePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamManager {

    private static List<String> redTeam = new ArrayList<>();
    private static List<String> blueTeam = new ArrayList<>();

    public TeamManager() {

    }

    public boolean isInTeam(Player player) {
        return redTeam.contains(player.getName()) || blueTeam.contains(player.getName());
    }

    public void addToTeam(TeamType type, Player player) {
        if (isInTeam(player)) {
            return;
        }
        switch (type) {
            case RED:
                redTeam.add(player.getName());
                break;
            case BLUE:
                blueTeam.add(player.getName());
                break;
        }
        player.sendMessage("You have joined the " + type.toString() + " team");

    }

    public void removeFromTeam(TeamType type, Player player) {
        if (!isInTeam(player)) {
            return;
        }
        switch (type) {
            case RED:
                if (redTeam.contains(player.getName())) {
                    redTeam.remove(player.getName());
                }
                break;
            case BLUE:
                if (blueTeam.contains(player.getName())) {
                    blueTeam.remove(player.getName());
                }
                break;
        }
    }

    public void assignTeams() {
        int i = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (i < Bukkit.getOnlinePlayers().size() / 2) {

                //add player to blue team
                addToTeam(TeamType.BLUE, player);

            } else {

                //add player to red team
                addToTeam(TeamType.RED, player);

            }
            i++;
        }
    }

    public void clearTeams() {
        redTeam.clear();
        blueTeam.clear();
    }

    public List<String> getRedTeam() {
        return redTeam;
    }

    public List<String> getBlueTeam() {
        return blueTeam;
    }

    public TeamType getTeamType(Player player) {
        if (!isInTeam(player)){
            return null;
        }

        return (redTeam.contains(player.getName()) ? TeamType.RED : TeamType.BLUE);

    }
}
