package me.chasertw123.minigames.mcparty.game.maps.tiles.general;

import me.chasertw123.minigames.mcparty.game.maps.tiles.TileLocation;
import me.chasertw123.minigames.mcparty.game.maps.tiles.items.TileItem;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import me.chasertw123.minigames.mcparty.player.data.GameStat;
import org.bukkit.Location;

import java.util.HashMap;

/**
 * Created by Chase on 8/24/2017.
 */
public class Tile_Red extends StandardColorTile {

    public Tile_Red(Location viewPointLocation, HashMap<TileLocation, Location> mapSpaceLocations, TileItem tileItem) {
        super(mapSpaceLocations, tileItem, viewPointLocation);
    }

    @Override
    public void landOnSpace(GamePlayer gp) {
        gp.decrementStat(GameStat.TOKEN, 3);
    }

}