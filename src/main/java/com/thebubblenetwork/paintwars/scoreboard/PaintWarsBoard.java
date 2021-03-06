package com.thebubblenetwork.paintwars.scoreboard;

import com.thebubblenetwork.api.framework.BubbleNetwork;
import com.thebubblenetwork.api.framework.util.mc.scoreboard.api.BoardPreset;
import com.thebubblenetwork.api.framework.util.mc.scoreboard.api.BubbleBoardAPI;
import com.thebubblenetwork.api.framework.util.mc.scoreboard.util.BoardModuleBuilder;
import com.thebubblenetwork.paintwars.PaintWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;

/**
 * The Bubble Network 2016
 * SkyFortress
 * 20/02/2016 {17:40}
 * Created February 2016
 */
public class PaintWarsBoard extends BoardPreset {
    public PaintWarsBoard() {
        super("PaintWars", new BoardModuleBuilder("Server", 11).withDisplay(ChatColor.BLUE + ChatColor.BOLD.toString() + "Server").build(),
                new BoardModuleBuilder("ServerValue", 10).withRandomDisplay().build(),
                new BoardModuleBuilder("Spacer1", 9).withRandomDisplay().build(),
                new BoardModuleBuilder("Players", 8).withDisplay(ChatColor.BLUE + ChatColor.BOLD.toString() + "Players").build(),
                new BoardModuleBuilder("AliveValue", 7).withDisplay(ChatColor.AQUA + "Alive: " + ChatColor.RESET).build(),
                new BoardModuleBuilder("WatchingValue", 6).withDisplay(ChatColor.AQUA + "Watching: " + ChatColor.RESET).build(),
                new BoardModuleBuilder("Spacer2", 5).withRandomDisplay().build(),
                new BoardModuleBuilder("CurrentCap", 4).withDisplay(ChatColor.BLUE + ChatColor.BOLD.toString() + "Flag Captures").build(),
                new BoardModuleBuilder("CapValue1", 3).withDisplay(ChatColor.AQUA + "Last Flag Capture: " + ChatColor.RESET).build(),
                new BoardModuleBuilder("CapValue2", 2).withDisplay(ChatColor.AQUA + "Time Left: " + ChatColor.RESET).build(),
                new BoardModuleBuilder("Spacer3", 1).withRandomDisplay().build(),
                new BoardModuleBuilder("address", 0).withDisplay("thebubblenetwork").build());
    }

    public void onEnable(BubbleBoardAPI api) {
        int spectators = PaintWars.getInstance().getGame().getSpectatorList().size();
        int players = Bukkit.getOnlinePlayers().size() - spectators;
        Team address = api.getScore(this, getModule("address")).getTeam();
        address.setPrefix(ChatColor.GRAY + "play.");
        address.setSuffix(".com");
        api.getScore(this, getModule("ServerValue")).getTeam().setSuffix(BubbleNetwork.getInstance().getType().getName() + "-" + String.valueOf(BubbleNetwork.getInstance().getId()));
        api.getScore(this, getModule("AliveValue")).getTeam().setSuffix(String.valueOf(players));
        api.getScore(this, getModule("WatchingValue")).getTeam().setSuffix(String.valueOf(spectators));
    }

    public void updateSpectators(BubbleBoardAPI api,int spectators){
        api.getScore(this, getModule("WatchingValue")).getTeam().setSuffix(String.valueOf(spectators));
    }

    public void updateLiving(BubbleBoardAPI api, int alive){
        api.getScore(this, getModule("AliveValue")).getTeam().setSuffix(String.valueOf(alive));
    }
}
