package me.chasertw123.minigames.mcparty.player;

import org.bukkit.entity.Player;

import java.util.*;

/**
 * Created by Scott Hiett on 8/24/2017.
 */
public class GamePlayerManager {

    private Map<UUID, GamePlayer> gamePlayerMap = new HashMap<>();

    public void add(UUID uuid, GamePlayer gamePlayer) {
        gamePlayerMap.put(uuid, gamePlayer);
    }

    public Collection<GamePlayer> toCollection() {
        return gamePlayerMap.values();
    }

    public List<GamePlayer> toArrayList() {
        return new ArrayList<>(toCollection());
    }

    public GamePlayer get(Player player) {
        return get(player.getUniqueId());
    }

    public GamePlayer get(UUID uuid) {
        return gamePlayerMap.get(uuid);
    }

    public void remove(UUID uuid) {
        gamePlayerMap.remove(uuid);
    }

}
