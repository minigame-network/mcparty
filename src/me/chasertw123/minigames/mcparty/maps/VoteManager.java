package me.chasertw123.minigames.mcparty.maps;

import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.maps.GameBoards;
import me.chasertw123.minigames.mcparty.player.GamePlayer;

/**
 * Created by Scott Hiett on 8/24/2017.
 */
public class VoteManager {

    private boolean votingActive = true;

    public int getVotes(GameBoards map) {
        int count = 0;

        for(GamePlayer gamePlayer : Main.getGamePlayerManager().toCollection())
            if(gamePlayer.hasVoted() && gamePlayer.getVotedMap() == map)
                count++;

        return count;
    }

    public GameBoards getWinner() {
        votingActive = false;

        int max = -1;
        GameBoards gameBoards = null;

        for(GameBoards board : GameBoards.values()) {
            if (getVotes(board) > max) {
                max = getVotes(board);
                gameBoards = board;
            }
        }

        return gameBoards;
    }

    public boolean isVotingActive() {
        return votingActive;
    }

    public void setVotingActive(boolean votingActive) {
        this.votingActive = votingActive;
    }

}
