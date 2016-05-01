package com.thebubblenetwork.paintwars.listeners;

import com.thebubblenetwork.paintwars.PaintWars;
import com.thebubblenetwork.paintwars.map.Cord;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;

public class PaintListener implements Listener {

    public static boolean BYPASS = false;

    private PaintWars paintWars;
    private Set<Cord> loaded = new HashSet<>();

    public PaintListener(PaintWars paintWars) {
        this.paintWars = paintWars;
    }




}
