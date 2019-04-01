package me.chasertw123.minigames.mcparty.loops;

import me.chasertw123.minigames.core.api.misc.Title;
import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.GameState;
import me.chasertw123.minigames.mcparty.game.maps.GameBoards;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Scott Hiett on 8/24/2017.
 */
public class Loop_Lobby extends GameLoop {

    private boolean forceStart = false;

    public Loop_Lobby() {
        super(1, 20L);
    }

    @Override
    public void run() {
        if(Main.getGameManager().getGameState() == GameState.MINIGAME_TESTING)
            return;

        if(Bukkit.getServer().getOnlinePlayers().size() == 4 || forceStart) {
            this.cancel();

            //Select the correct map
            GameBoards winner = Main.getVoteManager().getWinner();
            System.out.println("Gameboard winner is " + winner);
            Main.getMapManager().loadGameWorld(winner);

            for(Player player : Bukkit.getServer().getOnlinePlayers())
                player.sendMessage(Main.PREFIX + "The selected map is " + winner.getGameBoard().getName() + "!");

            Main.getGameManager().startGame(winner);
        } else {
            for(Player player : Bukkit.getServer().getOnlinePlayers())
                Title.sendActionbar(player, "(!) Waiting for " + (4 - Bukkit.getServer().getOnlinePlayers().size())
                        + " more player" + ((4 - Bukkit.getServer().getOnlinePlayers().size()) == 1 ? "" : "s"));
        }
    }

    public boolean isForceStart() {
        return forceStart;
    }

    public void setForceStart(boolean forceStart) {
        this.forceStart = forceStart;
    }

}
