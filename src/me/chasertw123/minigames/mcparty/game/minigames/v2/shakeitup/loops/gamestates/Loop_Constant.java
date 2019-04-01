package me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.loops.gamestates;

import me.chasertw123.minigames.core.api.misc.Title;
import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.ShakeItUp;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Loop_Constant extends BukkitRunnable {

    private ShakeItUp shakeItUp;
    private List<ArmorStand> stands;

    public Loop_Constant(ShakeItUp shakeItUp) {
        this.shakeItUp = shakeItUp;
        this.stands = new ArrayList<>();

        for(int i = 0; i < Main.getGamePlayerManager().toCollection().size(); i++) {
            ArmorStand armorStand = (ArmorStand) ShakeItUp.PLAYER_SPAWN.getWorld().spawnEntity(ShakeItUp.PLAYER_SPAWN, EntityType.ARMOR_STAND);
            armorStand.setVisible(false);
            armorStand.setGravity(false);

            stands.add(armorStand);
        }

        this.runTaskTimer(Main.getInstance(), 20L, 4); // Just to test if it's that you aren't spawned into the world yet.
    }

    @Override
    public void run() {
        for(GamePlayer gamePlayer : Main.getGamePlayerManager().toCollection()) {
            if (gamePlayer.getPlayer().getVehicle() == null) {
                for (ArmorStand stand : stands) {
                    if (stand.getPassenger() == null) {
                        gamePlayer.getPlayer().teleport(ShakeItUp.PLAYER_SPAWN);
                        stand.setPassenger(gamePlayer.getPlayer());

                        Title.sendActionbar(gamePlayer.getPlayer(), " "); // Don't show the message to get off

                        System.out.println("Setting you as the passenger");

                        break;
                    }
                }
            }
        }
    }

    @Override
    public synchronized void cancel() {
        super.cancel();

        stands.forEach(ArmorStand::remove);
    }

}
