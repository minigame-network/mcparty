package me.chasertw123.minigames.mcparty;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.mcparty.commands.CMD_ForceStart;
import me.chasertw123.minigames.mcparty.commands.CMD_PlayMinigame;
import me.chasertw123.minigames.mcparty.game.GameManager;
import me.chasertw123.minigames.mcparty.listeners.EventManager;

import me.chasertw123.minigames.mcparty.maps.MapManager;
import me.chasertw123.minigames.mcparty.maps.VoteManager;
import me.chasertw123.minigames.mcparty.player.GamePlayerManager;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import me.chasertw123.minigames.shared.framework.ServerType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Chase on 8/22/2017.
 */
public class Main extends JavaPlugin {

    public static final String PREFIX = "MCParty > ";

    private static Main plugin;
    private static GameManager gameManager;
    private static GamePlayerManager gamePlayerManager;
    private static MapManager mapManager;
    private static VoteManager voteManager;

    @Override
    public void onLoad() {
        CoreAPI.getServerDataManager().setServerType(ServerType.MINIGAME);
        CoreAPI.getServerDataManager().setServerGameType(ServerGameType.MCPARTY);
    }

    @Override
    public void onEnable() {
        plugin = this;

        mapManager = new MapManager();
        gameManager = new GameManager();
        gamePlayerManager = new GamePlayerManager();
        voteManager = new VoteManager();

        this.getCommand("PlayMinigame").setExecutor(new CMD_PlayMinigame());
        this.getCommand("ForceStart").setExecutor(new CMD_ForceStart());

        new EventManager();
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public static VoteManager getVoteManager() {
        return voteManager;
    }

    public static MapManager getMapManager() {
        return mapManager;
    }

    public static GamePlayerManager getGamePlayerManager() {
        return gamePlayerManager;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static Main getInstance() {
        return plugin;
    }

}
