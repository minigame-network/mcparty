package me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.loops;

import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * Created by Chase on 1/3/2018.
 */
public class Loop_ComputerPlayer extends BukkitRunnable {

    private static final long CHANCE_OCCURANCE = 5;
    private static final int SUCCESS_PERCENTAGE = 40;

    private GamePlayer gp;
    private Random random;

    public Loop_ComputerPlayer(GamePlayer gp) {
        this.gp = gp;
        this.random = new Random();

        this.runTaskTimer(Main.getInstance(), 0L, CHANCE_OCCURANCE);
    }

    @Override
    public void run() {
        if(random.nextInt(100) < SUCCESS_PERCENTAGE) {
            // Complete Action
        }
    }
}
