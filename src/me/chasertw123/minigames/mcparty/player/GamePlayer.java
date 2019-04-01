package me.chasertw123.minigames.mcparty.player;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.characters.v2.GameCharacter;
import me.chasertw123.minigames.mcparty.game.maps.GameBoards;
import me.chasertw123.minigames.mcparty.game.maps.tiles.TileLocation;
import me.chasertw123.minigames.mcparty.player.data.GameStat;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by Chase on 8/22/2017.
 */
public class GamePlayer {

    private static final Random random = new Random();

    private HashMap<GameStat, Integer> gameStats = new HashMap<>();
    private UUID uuid;
    private GameCharacter character;
    private GameBoards votedMap = null;
    private TileLocation tileLocation;

    public GamePlayer(UUID uuid) {
        this.uuid = uuid;
        Arrays.stream(GameStat.values()).forEach(gs -> gameStats.put(gs, 0));

        // Give them a random character for now
        int playerIndex = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers()).indexOf(getPlayer());

        character = GameCharacter.values()[playerIndex];
        tileLocation = TileLocation.values()[playerIndex + 1]; //Ignore center
    }


    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.getUuid());
    }

    public me.chasertw123.minigames.core.user.User getCoreUser() {
        return CoreAPI.getUser(getPlayer());
    }

    public GameCharacter getGameCharacter() {
        return character;
    }

    public void setGameCharacter(GameCharacter character) {
        this.character = character;
    }

    public boolean hasVoted() {
        return votedMap != null;
    }

    public GameBoards getVotedMap() {
        return votedMap;
    }

    public void setVotedMap(GameBoards votedMap) {
        this.votedMap = votedMap;
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public void sendMessage(boolean prefix, String message) {
        getPlayer().sendMessage((prefix ? Main.PREFIX : "") + message);
    }

    public int getStat(GameStat gameStat) {
        return gameStats.get(gameStat);
    }

    public void setStat(GameStat gameStat, int amount) {
        gameStats.replace(gameStat, amount);
    }

    public void incrementStat(GameStat gameStat, int amount) {
        this.setStat(gameStat, getStat(gameStat) + amount);
    }

    public void incrementStat(GameStat gameStat) {
        this.incrementStat(gameStat, 1);
    }

    public void decrementStat(GameStat gameStat, int amount) {
        this.setStat(gameStat, getStat(gameStat) - amount);
    }

    public void decrementStat(GameStat gameStat) {
        this.decrementStat(gameStat, 1);
    }

    public ArmorStand getArmorStand() {
        return character.getArmorStand();
    }

    public TileLocation getPlayerId() {
        return tileLocation;
    }

}
