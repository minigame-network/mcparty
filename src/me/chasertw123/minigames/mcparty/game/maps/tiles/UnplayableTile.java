package me.chasertw123.minigames.mcparty.game.maps.tiles;

import me.chasertw123.minigames.mcparty.game.maps.tiles.items.TileItem;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import org.bukkit.Location;

/**
 * Created by Chase on 8/22/2017.
 */
public interface UnplayableTile {

    Location getPerspectiveLocation();

    Location getMapSpaceLocation(TileLocation tileLocation);

    void onWalkOverSpace(GamePlayer gp);

    void setTileItem(TileItem tileItem);
}
