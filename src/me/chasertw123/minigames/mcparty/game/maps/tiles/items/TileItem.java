package me.chasertw123.minigames.mcparty.game.maps.tiles.items;

import me.chasertw123.minigames.mcparty.game.maps.tiles.UnplayableTile;
import me.chasertw123.minigames.mcparty.player.GamePlayer;

/**
 * Created by Chase on 8/23/2017.
 */
public interface TileItem /*extends Loop_AnimationPlayer.Animation*/ {

    void spawnOnMapSpace(UnplayableTile mapSpace);

    void giveSpaceItemToPlayer(GamePlayer gp);

}
