package me.chasertw123.minigames.mcparty.game.maps.tiles.general;

import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.maps.tiles.TileLocation;
import me.chasertw123.minigames.mcparty.game.maps.tiles.UnplayableTile;
import me.chasertw123.minigames.mcparty.game.maps.tiles.items.TileItem;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import me.chasertw123.minigames.mcparty.player.data.GameStat;
import org.bukkit.Location;

import java.util.Map;

public class Tile_Star implements UnplayableTile {

    private Location perspectiveLocation;
    private Map<TileLocation, Location> mapSpaceLocations;

    @Override
    public Location getPerspectiveLocation() {
        return perspectiveLocation;
    }

    @Override
    public Location getMapSpaceLocation(TileLocation tileLocation) {
        return mapSpaceLocations.get(tileLocation);
    }

    @Override
    public void onWalkOverSpace(GamePlayer gp) {
        // Give them a star
        gp.incrementStat(GameStat.STAR);

        // Do the animation thing
        gp.sendMessage(true, "You got a star!");

        // Teleport them back to the start
        Main.getGameManager().getGameBoard().getStartTile().teleportTo(gp);
    }

    @Override
    public void setTileItem(TileItem tileItem) {

    }

}
