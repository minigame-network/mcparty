package me.chasertw123.minigames.mcparty.game.guis;

import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.maps.GameBoards;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

/**
 * Created by Scott Hiett on 8/24/2017.
 */
public class Gui_MapVote extends AbstractGui {

    public Gui_MapVote(GamePlayer gp) {
        super(1, "Vote for a Map!", gp.getCoreUser());

        int count = 0;
        for (GameBoards map : GameBoards.values()) {
            setItem(new cItemStack(Material.EMPTY_MAP, ChatColor.YELLOW + map.getGameBoard().getName() + ChatColor.WHITE + " [" + ChatColor.GREEN
                    + Main.getVoteManager().getVotes(map) + ChatColor.WHITE + "]").addFancyLore(ChatColor.YELLOW
                    + "" + ChatColor.ITALIC + map.getGameBoard().getDescription(), ChatColor.YELLOW.toString()), count, (s, c, p) -> {

                if (!Main.getVoteManager().isVotingActive()) {
                    gp.getPlayer().sendMessage(Main.PREFIX + ChatColor.RED + "Voting has already ended!");
                    gp.getPlayer().playSound(gp.getPlayer().getLocation(), Sound.VILLAGER_NO, 1F, 1F);
                    gp.getPlayer().closeInventory();
                    return;
                }

                gp.setVotedMap(map);
                gp.getPlayer().sendMessage(Main.PREFIX + ChatColor.WHITE + "You voted for the map " + ChatColor.BOLD + map.getGameBoard().getName().toUpperCase() + ChatColor.WHITE + ".");
                gp.getPlayer().playSound(gp.getPlayer().getLocation(), Sound.VILLAGER_YES, 1F, 1F);
                gp.getPlayer().closeInventory();
            });

            count++;
        }
    }

}
