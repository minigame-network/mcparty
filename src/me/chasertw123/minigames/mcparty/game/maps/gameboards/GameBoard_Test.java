package me.chasertw123.minigames.mcparty.game.maps.gameboards;

import me.chasertw123.minigames.mcparty.game.maps.GameBoard;
import me.chasertw123.minigames.mcparty.game.maps.GameBoards;
import me.chasertw123.minigames.mcparty.game.maps.tiles.TileLocation;
import me.chasertw123.minigames.mcparty.game.maps.tiles.UnplayableTile;
import me.chasertw123.minigames.mcparty.game.maps.tiles.general.Tile_Blue;
import me.chasertw123.minigames.mcparty.game.maps.tiles.general.Tile_Red;
import me.chasertw123.minigames.mcparty.game.maps.tiles.general.Tile_Start;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameBoard_Test extends GameBoard {

    private List<UnplayableTile> tiles;

    public GameBoard_Test(World world) {
        super(GameBoards.TEST.getGameBoard(), world);

        tiles = new ArrayList<>();

        tiles.add(new Tile_Start(new Location(getWorld(), 141, 10, -861, -90, 45),
                generateLocations(new Location(getWorld(), 145, 4, -865))));

        tiles.add(new Tile_Blue(new Location(getWorld(), 146, 10, -861, -90, 45),
                generateLocations(new Location(getWorld(), 151, 4, -865)), null));

        tiles.add(new Tile_Red(new Location(getWorld(), 151, 10, -861, -90, 45),
                generateLocations(new Location(getWorld(), 156, 4, -865)), null));

    }

    @Override
    public List<UnplayableTile> getTiles() {
        return tiles;
    }

    private HashMap<TileLocation, Location> generateLocations(Location start) {
        HashMap<TileLocation, Location> locs = new HashMap<>();

        locs.put(TileLocation.CENTER, start.clone().add(3, 0, 3));
        locs.put(TileLocation.PLAYER1, start.clone().add(2, 0, 2));
        locs.put(TileLocation.PLAYER2, start.clone().add(2, 0, 4));
        locs.put(TileLocation.PLAYER3, start.clone().add(4, 0, 2));
        locs.put(TileLocation.PLAYER4, start.clone().add(4, 0, 4));

        return locs;
    }

}
