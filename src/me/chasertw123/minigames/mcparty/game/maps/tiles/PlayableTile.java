package me.chasertw123.minigames.mcparty.game.maps.tiles;

import me.chasertw123.minigames.mcparty.player.GamePlayer;

/**
 * Created by Chase on 8/22/2017.
 */
public interface PlayableTile extends UnplayableTile {

    void landOnSpace(GamePlayer gp);

    void teleportTo(GamePlayer gp);

}
