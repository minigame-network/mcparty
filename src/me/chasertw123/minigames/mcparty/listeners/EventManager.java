package me.chasertw123.minigames.mcparty.listeners;

import me.chasertw123.minigames.core.listeners.general.Event_PlayerInteract;
import me.chasertw123.minigames.mcparty.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * Created by Scott Hiett on 8/24/2017.
 */
public class EventManager {

    public EventManager() {
        Listener[] l = {
                new Event_PlayerJoin(),
                new Event_PlayerQuit(),
                new Event_PlayerInteract()
        };

        for(Listener listener : l)
            Bukkit.getServer().getPluginManager().registerEvents(listener, Main.getInstance());
    }

}