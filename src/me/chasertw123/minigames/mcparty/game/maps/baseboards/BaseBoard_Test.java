package me.chasertw123.minigames.mcparty.game.maps.baseboards;

import me.chasertw123.minigames.mcparty.game.maps.BaseBoard;

/**
 * Created by Scott Hiett on 8/24/2017.
 */
public class BaseBoard_Test implements BaseBoard {

    @Override
    public String getName() {
        return "Test";
    }

    @Override
    public String getDescription() {
        return "This is a Test!";
    }

    @Override
    public String getWorldDirName() {
        return "test";
    }

}
