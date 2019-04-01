package me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.loops.gamestates;

import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.ShakeItUp;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Loop_WalkIn extends BukkitRunnable {

    private ShakeItUp shakeItUp;

    public Loop_WalkIn(ShakeItUp shakeItUp) {
        this.shakeItUp = shakeItUp;
//        this.runTaskTimer(Main.getInstance(), 0L, 1L);
    }

    @Override
    public void run() {

        // TODO: Check that walk in animation is completed

    }

    @Override
    public void cancel() {
        super.cancel(); // Make sure it still stops the loop!
    }
}
