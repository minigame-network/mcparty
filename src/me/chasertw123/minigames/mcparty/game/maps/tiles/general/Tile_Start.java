package me.chasertw123.minigames.mcparty.game.maps.tiles.general;

import me.chasertw123.minigames.mcparty.game.maps.tiles.PlayableTile;
import me.chasertw123.minigames.mcparty.game.maps.tiles.TileLocation;
import me.chasertw123.minigames.mcparty.game.maps.tiles.items.TileItem;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import org.bukkit.Location;

import java.util.Map;

public class Tile_Start implements PlayableTile {

    private Location perspectiveLocation;
    private Map<TileLocation, Location> mapSpaceLocations;

    public Tile_Start(Location perspectiveLocation, Map<TileLocation, Location> mapSpaceLocations) {
        this.perspectiveLocation = perspectiveLocation;
        this.mapSpaceLocations = mapSpaceLocations;
    }

    @Override
    public void landOnSpace(GamePlayer gp) {
        // Do nothing, this is the start
    }

    @Override
    public void teleportTo(GamePlayer gp) {
        gp.getArmorStand().teleport(getMapSpaceLocation(gp.getPlayerId())); //TODO: Show animation
        gp.getPlayer().teleport(getPerspectiveLocation());
    }

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

    }

    @Override
    public void setTileItem(TileItem tileItem) {

    }

}
