package me.chasertw123.minigames.mcparty.game.maps;

import com.comphenix.protocol.wrappers.EnumWrappers;
import me.chasertw123.minigames.mcparty.game.maps.tiles.PlayableTile;
import me.chasertw123.minigames.mcparty.game.maps.tiles.TileLocation;
import me.chasertw123.minigames.mcparty.game.maps.tiles.UnplayableTile;
import me.chasertw123.minigames.mcparty.game.maps.tiles.general.Tile_Star;
import me.chasertw123.minigames.mcparty.game.maps.tiles.general.Tile_Start;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GameBoard {

    private BaseBoard baseBoard;
    private World world;

    public GameBoard(BaseBoard baseBoard, World world) {
        this.baseBoard = baseBoard;
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public BaseBoard getBaseBoard() {
        return baseBoard;
    }

    public abstract List<UnplayableTile> getTiles();

    public List<UnplayableTile> getUnplayableTiles() {
        List<UnplayableTile> data = new ArrayList<>();

        getTiles().stream().filter(unplayableTile -> !(unplayableTile instanceof PlayableTile)).forEach(data::add);

        return data;
    }

    public List<PlayableTile> getPlayableTiles() {
        List<PlayableTile> data = new ArrayList<>();

        getTiles().stream().filter(unplayableTile -> unplayableTile instanceof PlayableTile).forEach(playableTile -> data.add((PlayableTile) playableTile));

        return data;
    }

    public Tile_Start getStartTile() {
        return (Tile_Start) getPlayableTiles().stream().filter(t -> t instanceof Tile_Start).toArray()[0]; // There is only one!
    }

    private UnplayableTile generateUnplayableTile() {

        return null;
    }

    private PlayableTile generatePlayableTile(boolean blue, Location bottomLeft) {


        return null;
    }

}
