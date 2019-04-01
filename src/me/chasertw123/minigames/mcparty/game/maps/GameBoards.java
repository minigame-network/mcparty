package me.chasertw123.minigames.mcparty.game.maps;

import me.chasertw123.minigames.mcparty.game.maps.baseboards.BaseBoard_Test;
import me.chasertw123.minigames.mcparty.game.maps.gameboards.GameBoard_Test;
import org.bukkit.World;

/**
 * Created by Scott Hiett on 8/24/2017.
 */
public enum GameBoards {

    TEST (new BaseBoard_Test()) {
        @Override
        public GameBoard createBoard(World world) {
            return new GameBoard_Test(world);
        }
    };

    private BaseBoard gameBoard;

    GameBoards(BaseBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public BaseBoard getGameBoard() {
        return gameBoard;
    }

    public abstract GameBoard createBoard(World world);

}
