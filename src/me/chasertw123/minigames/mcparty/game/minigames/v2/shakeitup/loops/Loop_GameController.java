package me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.loops;

import me.chasertw123.minigames.core.api.misc.Title;
import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.ShakeItUp;
import me.chasertw123.minigames.mcparty.player.GamePlayer;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import us.myles.ViaVersion.api.Via;
import us.myles.ViaVersion.api.boss.BossBar;
import us.myles.ViaVersion.api.boss.BossColor;
import us.myles.ViaVersion.api.boss.BossStyle;

/**
 * Created by Chase on 1/3/2018.
 */
public class Loop_GameController extends BukkitRunnable {

    private static final int STARTING_COUNTDOWN = 60;
    private static final int GAME_DURATION = 200;

    private ShakeItUp shakeItUp;

    private int time = STARTING_COUNTDOWN;
    private boolean starting = true;

    private BossBar bossBar = null;

    public Loop_GameController(ShakeItUp shakeItUp) {
        this.shakeItUp = shakeItUp;
        this.runTaskTimer(Main.getInstance(), 0L, 1L);
    }

    @Override
    public void run() {

        if (starting)
            switch (time) {

                case 60:
                    for (GamePlayer gp : Main.getGamePlayerManager().toCollection()) {
                        Title.sendTitle(gp.getPlayer(), 0, 20, 0, ChatColor.RED + "" + ChatColor.BOLD + "READY");
                        gp.getPlayer().playSound(gp.getPlayer().getLocation(), Sound.NOTE_PIANO, 1F, 0.1F);
                    }
                    break;

                case 40:
                    for (GamePlayer gp : Main.getGamePlayerManager().toCollection()) {
                        Title.sendTitle(gp.getPlayer(), 0, 20, 0, ChatColor.YELLOW + "" + ChatColor.BOLD + "SET");
                        gp.getPlayer().playSound(gp.getPlayer().getLocation(), Sound.NOTE_PIANO, 1.5F, 0.1F);
                    }
                    break;

                case 20:
                    for (GamePlayer gp : Main.getGamePlayerManager().toCollection()) {
                        Title.sendTitle(gp.getPlayer(), 0, 20, 0, ChatColor.GREEN + "" + ChatColor.BOLD + "SHAKE!");
                        gp.getPlayer().playSound(gp.getPlayer().getLocation(), Sound.NOTE_PIANO, 2F, 2F);
                    }
                    break;

                case 0:
                    starting = false;
                    time = GAME_DURATION;
                    this.updateBossBar();
                    for (GamePlayer gp : Main.getGamePlayerManager().toCollection()) {
                        Title.sendTitle(gp.getPlayer(), 0, 20, 0, " ");
                        bossBar.addPlayer(gp.getUniqueId());
                    }
                    return;
            }

        else {

            if (time <= 0) {
                for (GamePlayer gp : Main.getGamePlayerManager().toCollection()) {
                    Title.sendTitles(gp.getPlayer(), 10, 80, 10, ChatColor.BLUE + "" + ChatColor.BOLD + "GAME OVER", " ");
                    gp.getPlayer().playSound(gp.getPlayer().getLocation(), Sound.ENDERDRAGON_GROWL, 1.5F, 0.8F);
                    bossBar.removePlayer(gp.getUniqueId());
                }

                // TODO: Player ending animations

                this.cancel();

                shakeItUp.stopMinigame();
                return;
            }

            this.updateBossBar();

            if (time % 20 == 0)
                for (GamePlayer gp : Main.getGamePlayerManager().toCollection()) {
                    Title.sendTitle(gp.getPlayer(), 0, 20, 0, ChatColor.LIGHT_PURPLE + shakeItUp.getCurrentKey().get(gp.getPlayer().getName()).toString());
            }
        }

        for (GamePlayer gp : Main.getGamePlayerManager().toCollection())
            gp.getPlayer().closeInventory();

        time--;
    }

    public boolean isGameActive() {
        return !starting;
    }

    private void updateBossBar() {

        ChatColor chatColor = ChatColor.GREEN;
        BossColor bossColor = BossColor.GREEN;

        if (time <= 100 && time > 20) {
            chatColor = ChatColor.YELLOW;
            bossColor = BossColor.YELLOW;
        }

        else if (time <= 20) {
            chatColor = ChatColor.RED;
            bossColor = BossColor.RED;
        }

        double timeLeft = (time * 50) / 1000.0;
        float percentage = (float) ((time / 2) * 0.01);

        if (bossBar == null)
            bossBar = Via.getAPI().createBossBar(chatColor + "" + ChatColor.BOLD + String.format("%.2f", timeLeft), percentage, bossColor, BossStyle.SOLID);

        else {
            bossBar.setTitle(chatColor + String.format("%.2f", timeLeft));
            bossBar.setHealth(percentage);
            bossBar.setColor(bossColor);
        }
    }
}
