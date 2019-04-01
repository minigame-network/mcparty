package me.chasertw123.minigames.mcparty.player.controller;

import org.bukkit.Location;

public class ControllerPoint extends Location {

    public ControllerPoint(Location location) {
        super(location.getWorld(), location.getX(), location.getBlockY(), location.getZ());
    }

}
