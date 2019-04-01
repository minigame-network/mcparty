package me.chasertw123.minigames.mcparty.maps;

import me.chasertw123.minigames.mcparty.game.maps.GameBoards;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;

/**
 * Created by Scott Hiett on 8/24/2017.
 */
public class MapManager {

    private World lobbyWorld, gameWorld = null;

    public MapManager() {
        if(!new File("lobby").exists()) {
            new File("lobby").mkdirs();

            try {
                FileUtils.copyDirectory(new File("originalLobby"), new File("lobby"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        lobbyWorld = Bukkit.getServer().createWorld(new WorldCreator("lobby"));
    }

    public World getGameWorld() {
        return gameWorld;
    }

    public World getLobbyWorld() {
        return lobbyWorld;
    }

    public void loadGameWorld(GameBoards gameBoards) {
        File gameFolder = new File("game");

        if(gameFolder.exists()) {
            try {
                FileUtils.deleteDirectory(gameFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        gameFolder.mkdirs();

        try {
            System.out.println("Base board is " + gameBoards.getGameBoard());

            FileUtils.copyDirectory(new File("maps/" + gameBoards.getGameBoard().getWorldDirName()), gameFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameWorld = Bukkit.getServer().createWorld(new WorldCreator("game"));
    }

}
