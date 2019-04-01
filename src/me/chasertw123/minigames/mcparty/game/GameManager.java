package me.chasertw123.minigames.mcparty.game;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.maps.GameBoard;
import me.chasertw123.minigames.mcparty.game.maps.GameBoards;
import me.chasertw123.minigames.mcparty.loops.GameLoop;
import me.chasertw123.minigames.mcparty.loops.Loop_Game;
import me.chasertw123.minigames.mcparty.loops.Loop_Lobby;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Chase on 8/23/2017.
 */
public class GameManager {

    private GameState gameState = GameState.LOBBY;
    private GameLoop currentGameLoop;
    private GameBoard gameBoard;
    private int turn;

    public GameManager() {
        currentGameLoop = new Loop_Lobby();
        this.turn = -1;
    }

    public GameLoop getCurrentGameLoop() {
        return currentGameLoop;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void startGame(GameBoards winner) {
        gameBoard = winner.createBoard(Main.getMapManager().getGameWorld());
        gameState = GameState.STARTING;

        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            GamePlayer gamePlayer = Main.getGamePlayerManager().get(player);

            gamePlayer.sendMessage(true, "The game has started!");

            // Spawn their entity.
            System.out.println("Map space location: " + gameBoard.getStartTile().getMapSpaceLocation(gamePlayer.getPlayerId()));
            gamePlayer.getGameCharacter().spawnEntity(gameBoard.getStartTile().getMapSpaceLocation(gamePlayer.getPlayerId())); // Spawn the armor stand for this player.

            // Teleport them to the start square.
            gameBoard.getStartTile().teleportTo(gamePlayer);
        }

        currentGameLoop = new Loop_Game();
    }

    public void runNextTurn() {
        if(++turn == 4)
            turn = 0;

        // Get the player whos turn it is, and let them hit the box thingy
        GamePlayer gamePlayer = Main.getGamePlayerManager().toArrayList().get(turn);

        Bukkit.broadcastMessage(ChatColor.GOLD + "It's " + ChatColor.AQUA + gamePlayer.getPlayer().getName() + ChatColor.GOLD + "'s turn!");

        // TODO: Move all of the players down so they can view this.
        // Give the player the dice

    }

    public void endGame() {
        gameState = GameState.ENDING;

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () ->
            CoreAPI.getOnlinePlayers().forEach(player -> player.sendToServer("hub")),
                20 * 10);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart"), 20 * 25);
    }

}
