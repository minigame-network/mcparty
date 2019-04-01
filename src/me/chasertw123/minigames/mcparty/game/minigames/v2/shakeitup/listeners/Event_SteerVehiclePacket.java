package me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import me.chasertw123.minigames.core.api.misc.Title;
import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.ShakeItUp;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle;

import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * Created by Chase on 1/2/2018.
 */
public class Event_SteerVehiclePacket extends PacketAdapter {

    private ShakeItUp shakeItUp;

    public Event_SteerVehiclePacket(ShakeItUp shakeItUp) {
        super(Main.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Client.STEER_VEHICLE);
        this.shakeItUp = shakeItUp;
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {

        if (event.getPacketType() != PacketType.Play.Client.STEER_VEHICLE || !(event.getPlayer().getVehicle() instanceof ArmorStand))
            return;

        Player p = event.getPlayer();
        ArmorStand as = (ArmorStand) event.getPlayer().getVehicle();

        PacketPlayInSteerVehicle packet = (PacketPlayInSteerVehicle) event.getPacket().getHandle();

        try {
            Field field = packet.getClass().getDeclaredField("d");
            field.setAccessible(true);

            field.set(event.getPacket().getHandle(), false);
            field.setAccessible(!field.isAccessible());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (shakeItUp.getGameController() == null || !shakeItUp.getGameController().isGameActive())
            return;

        float forward = packet.b();
        float side = packet.a();

        int keyPresses = 0;
        boolean pressedCorrect = false;

        char currentKey = shakeItUp.getCurrentKey().get(event.getPlayer().getName());

        if (forward > 0) { // W
            keyPresses++;

            if (currentKey == 'W')
                pressedCorrect = true;
        }

        if (forward < 0) { // S
            keyPresses++;

            if (currentKey == 'S')
                pressedCorrect = true;
        }

        if (side > 0) { // A
            keyPresses++;

            if (currentKey == 'A')
                pressedCorrect = true;
        }

        if (side < 0) { // D
            keyPresses++;

            if (currentKey == 'D')
                pressedCorrect = true;
        }

        if (keyPresses > 1) {
            Title.sendSubtitle(p, 0, 20, 0, ChatColor.RED + "Stop pressing multiple keys!");
            return;
        }

        if (pressedCorrect) {

            int totalKeyPresses = shakeItUp.getKeyPresses().get(p.getName()) + 1;

            shakeItUp.getKeyPresses().put(p.getName(), totalKeyPresses);
            shakeItUp.getCurrentKey().put(p.getName(), shakeItUp.getKeyOrder().get(shakeItUp.getKeyPresses().get(p.getName())));

            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 1.5F);
            Title.sendTitles(p, 0, 20, 0, ChatColor.LIGHT_PURPLE + "" + shakeItUp.getCurrentKey().get(p.getName()), " ");

//            AnimationType animationToPlay = null;
//            if ((totalKeyPresses & 1) == 0)
//                animationToPlay = AnimationType.SHAKE_IT_UP_ARM_DOWN;
//
//            else
//                animationToPlay = AnimationType.SHAKE_IT_UP_ARM_UP;
//
//
//            Main.getGamePlayerManager().get(p).getCharacter().playAnimation(animationToPlay);
        }
    }

}
