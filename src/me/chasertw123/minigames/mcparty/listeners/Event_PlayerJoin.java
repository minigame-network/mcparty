package me.chasertw123.minigames.mcparty.listeners;

import me.chasertw123.minigames.core.utils.items.AbstractItem;
import me.chasertw123.minigames.core.utils.items.Items;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.guis.Gui_MapVote;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Scott Hiett on 8/24/2017.
 */
public class Event_PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage(Main.PREFIX + p.getName() + " has joined the game! (" + Bukkit.getOnlinePlayers().size() + "/4)");

        GamePlayer gamePlayer = new GamePlayer(p.getUniqueId()); //TODO: Download data

        p.setHealth(20);
        p.setFoodLevel(20);

        p.teleport(Main.getMapManager().getLobbyWorld().getSpawnLocation().clone().add(0.5, 0, 0.5));

        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().clear();
        new AbstractItem(Items.MAP_VOTING.getItemStack(), gamePlayer.getCoreUser(), 0, (it) -> new Gui_MapVote(gamePlayer));
        new AbstractItem(new cItemStack(Material.NETHER_STAR).setDisplayName(ChatColor.GREEN + "Select Character "
                + ChatColor.GRAY + "(Right Click)"), gamePlayer.getCoreUser(), 1, (it) -> {});
        new AbstractItem(Items.RETURN_TO_HUB.getItemStack(), gamePlayer.getCoreUser(), 8, (it) -> gamePlayer.getCoreUser().sendToServer("hub"));

        Main.getGamePlayerManager().add(p.getUniqueId(), gamePlayer);
    }

}
