package com.thebubblenetwork.paintwars;

import com.thebubblenetwork.api.framework.BubbleNetwork;
import com.thebubblenetwork.api.framework.player.BukkitBubblePlayer;
import com.thebubblenetwork.api.game.BubbleGameAPI;
import com.thebubblenetwork.api.game.kit.KitManager;
import com.thebubblenetwork.api.game.maps.GameMap;
import com.thebubblenetwork.api.game.maps.MapData;
import com.thebubblenetwork.api.global.bubblepackets.messaging.messages.handshake.JoinableUpdate;
import com.thebubblenetwork.api.global.player.BubblePlayer;
import com.thebubblenetwork.paintwars.kits.RifleKit;
import com.thebubblenetwork.paintwars.listeners.PaintListener;
import com.thebubblenetwork.paintwars.map.PaintWarsMap;
import com.thebubblenetwork.paintwars.map.TeamsMapData;
import com.thebubblenetwork.paintwars.scoreboard.PaintWarsBoard;
import com.thebubblenetwork.paintwars.teams.TeamManager;
import com.thebubblenetwork.paintwars.teams.TeamType;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;


public class PaintWars extends BubbleGameAPI {

    private static final int VERSION = 1;

    @Getter
    private static PaintWars instance;

    @Getter
    private PaintWarsBoard board;

    private PaintListener listener = new PaintListener(this);

    @Getter
    private TeamManager teamManager;

    public PaintWars() {
        super("PaintWars", GameMode.SURVIVAL, "Rifle", 2);
        instance = this;
        board = new PaintWarsBoard();
        listener = new PaintListener(this);

        //clear the teams
        teamManager = new TeamManager();
        teamManager.clearTeams();
    }

    public void cleanup() {
        //todo
        BubbleNetwork.getInstance().getPlugin().getLogger().log(Level.INFO, "PaintWars cleanup task");
    }

    public void onStateChange(State oldstate, State newstate) {
        try {
            BubbleNetwork.getInstance().getPacketHub().sendMessage(BubbleNetwork.getInstance().getProxy(), new JoinableUpdate(newstate == State.LOBBY));
        } catch (IOException e) {
            BubbleNetwork.getInstance().getPlugin().getLogger().log(Level.WARNING, "Could not send joinable update for PaintWars", e);
        }

        //various game states
        if(newstate == State.INGAME){
            BubbleNetwork.getInstance().getPlugin().getLogger().log(Level.INFO, "A game of PaintWars is currently in progress");
        }
        else if(newstate == State.ENDGAME){
            for(BubblePlayer player: BukkitBubblePlayer.getPlayerObjectMap().values()){
                Player p = (Player)player.getPlayer();
                p.sendMessage("");
                p.sendMessage(ChatColor.GRAY + "                <----------------Stats---------------->");
                p.sendMessage(ChatColor.GREEN + "Thank you for playing, stats for PaintWars is coming soon.");
                /*p.sendMessage(ChatColor.GREEN + "You have won " + ChatColor.GRAY + (int)player.getStats(getType().getName(), "win") + ChatColor.GREEN + " SkyFortress games");
                p.sendMessage(ChatColor.GREEN + "You have killed " + ChatColor.GRAY + (int)player.getStats(getType().getName(), "kill") + ChatColor.GREEN + " players");
                p.sendMessage(ChatColor.GREEN + "You have assassinated the king " + ChatColor.GRAY + (int)player.getStats(getType().getName(), "kingkill") + ChatColor.GREEN + " times");
                p.sendMessage(ChatColor.GREEN + "You have slain " + ChatColor.GRAY + (int)player.getStats(getType().getName(), "guardkill") + ChatColor.GREEN + " guards");
                p.sendMessage(ChatColor.GREEN + "You have died " + ChatColor.GRAY + (int)player.getStats(getType().getName(), "death") + ChatColor.GREEN + " times");*/
                p.sendMessage(ChatColor.GRAY + "                <----------------Stats---------------->");
            }
        }
        else if (newstate == State.LOBBY) {
            if(oldstate == State.RESTARTING){
                BubbleNetwork.getInstance().getLogger().log(Level.INFO, "PaintWars is currently restarting.");
                //clear out the teams
                teamManager.clearTeams();
            }
        }

    }

    public PaintWarsBoard getScorePreset() {
        return board;
    }

    public GameMap loadMap(String s, MapData mapData, File file, File file1) {
        return new PaintWarsMap(s, mapData, file, file1);
    }

    public void teleportPlayers(GameMap gameMap, World world) {
        if (!(gameMap instanceof PaintWarsMap)) {
            throw new IllegalArgumentException("Invalid map");
        }

        PaintWarsMap map = (PaintWarsMap) gameMap;
        Iterator<? extends Player> playerIterator = Bukkit.getOnlinePlayers().iterator();
        for (TeamsMapData teamMapData : map.getTeamsMapData() ) {
            if (playerIterator.hasNext()) {
                Player p = playerIterator.next();

                //set players spawn to their teams spawn
                if (teamManager.getTeamType(p) == TeamType.RED) {

                    p.teleport(teamMapData.getRedTeamSpawn().toLocation(world));

                } else if (teamManager.getTeamType(p) == TeamType.BLUE){

                    p.teleport(teamMapData.getBlueTeamSpawn().toLocation(world));

                }

            }
        }

    }

    public long finishUp() {
        return Long.MAX_VALUE;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        registerListener(getListener());
        KitManager.getKits().add(new RifleKit());
    }


    public PaintListener getListener() {
        return listener;
    }

    public int getVersion() {
        return VERSION;
    }


}
