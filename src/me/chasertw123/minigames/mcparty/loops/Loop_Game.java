package me.chasertw123.minigames.mcparty.loops;

import me.chasertw123.minigames.mcparty.Main;

public class Loop_Game extends GameLoop {

    public Loop_Game() {
        super(60 * 30, 20L);
    }

    @Override
    public void run() {
        // This is a game tick, do updates

        if(interval-- <= 0) {
            // The game has ran out of time, end it
            Main.getGameManager().endGame();
        }
    }

}
