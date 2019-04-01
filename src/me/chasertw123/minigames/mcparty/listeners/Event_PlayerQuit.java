package me.chasertw123.minigames.mcparty.listeners;

import me.chasertw123.minigames.mcparty.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Scott Hiett on 8/24/2017.
 */
public class Event_PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage(Main.PREFIX + p.getName() + " has quit the game!");

        Main.getGamePlayerManager().remove(p.getUniqueId());
    }

}
