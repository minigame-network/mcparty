package me.chasertw123.minigames.mcparty.loops;

import me.chasertw123.minigames.mcparty.Main;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class GameLoop extends BukkitRunnable {

    public int interval;

    public GameLoop(int interval, long tickRate) {
        this.interval = interval;
        this.runTaskTimer(Main.getInstance(), 0L, tickRate);
    }

    public GameLoop(int interval, long startDelay, long tickRate) {
        this.interval = interval;
        this.runTaskTimer(Main.getInstance(), startDelay, tickRate);
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
