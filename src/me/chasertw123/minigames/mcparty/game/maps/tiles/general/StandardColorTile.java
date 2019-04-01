package me.chasertw123.minigames.mcparty.game.maps.tiles.general;

import me.chasertw123.minigames.mcparty.game.maps.tiles.PlayableTile;
import me.chasertw123.minigames.mcparty.game.maps.tiles.TileLocation;
import me.chasertw123.minigames.mcparty.game.maps.tiles.items.TileItem;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import org.bukkit.Location;

import java.util.HashMap;

public abstract class StandardColorTile implements PlayableTile {

    private HashMap<TileLocation, Location> mapSpaceLocations;
    private TileItem tileItem;
    private Location viewPointLocation;

    public StandardColorTile(HashMap<TileLocation, Location> mapSpaceLocations, TileItem tileItem, Location viewPointLocation) {
        this.mapSpaceLocations = mapSpaceLocations;
        this.tileItem = tileItem;
        this.viewPointLocation = viewPointLocation;
    }

    @Override
    public Location getMapSpaceLocation(TileLocation tileLocation) {
        return mapSpaceLocations.get(tileLocation);
    }

    @Override
    public void onWalkOverSpace(GamePlayer gp) {
        if (tileItem != null)
            tileItem.giveSpaceItemToPlayer(gp);
    }

    public void setTileItem(TileItem tileItem) {
        this.tileItem = tileItem;
    }

    @Override
    public Location getPerspectiveLocation() {
        return this.viewPointLocation;
    }

    @Override
    public void teleportTo(GamePlayer gp) {
        gp.getArmorStand().teleport(getMapSpaceLocation(gp.getPlayerId())); //TODO: Show animation
        gp.getPlayer().teleport(getPerspectiveLocation());
    }

}
