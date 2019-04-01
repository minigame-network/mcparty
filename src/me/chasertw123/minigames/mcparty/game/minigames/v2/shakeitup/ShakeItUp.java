package me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketListener;
import me.chasertw123.minigames.core.api.misc.Title;
import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.minigames.v2.Game;
import me.chasertw123.minigames.mcparty.game.minigames.v2.MinigameType;
import me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.listeners.Event_SteerVehiclePacket;
import me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.loops.Loop_GameController;
import me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.loops.gamestates.*;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Chase on 12/30/2017.
 */
public class ShakeItUp extends Game {

    private static final String NAME = "Shake It Up";
    private static final String RULES = "Shake your bottle of soda pop. After 10 seconds, everyone will open their bottles. Make your bottle spew the highest.";
    private static final String HINT = "Using both your hands on WASD will yield the highest score. Don't press multiple keys, that's cheating!";

    public static final Location PLAYER_SPAWN = new Location(Bukkit.getWorld("world"), 8.5D, 64.0D, 68.5D, 0F, 0F);
    private static final Location[] CHARACTER_SPAWN = {
            new Location(Bukkit.getWorld("world"), 14.5D, 63.0D, 74.5D, 180.0F, 0.0F),
            new Location(Bukkit.getWorld("world"), 10.5D, 63.0D, 74.5D, 180.0F, 0.0F),
            new Location(Bukkit.getWorld("world"), 6.5D, 63.0D, 74.5D, 180.0F, 0.0F),
            new Location(Bukkit.getWorld("world"), 2.5D, 63.0D, 74.5D, 180.0F, 0.0F)};
    private static final Location[] WINNER_SPAWN = { };

    private Loop_GameController gameController = null;

    private List<PacketListener> packetListeners = null;
    private List<Character> keyOrder = null;

    private HashMap<String, Character> currentKey = null;
    private HashMap<String, Integer> keyPresses = null;

    private GameState gameState = null;
    private BukkitRunnable constantLoop = null;

    public enum GameState {
        WALK_IN(null), STARTING(null), INPROGRESS(null), ENDING(null);

        private BukkitRunnable runnable;

        GameState(BukkitRunnable runnable) {
            this.runnable = runnable;
        }

        public BukkitRunnable getRunnable() {
            return runnable;
        }

        public void setRunnable(BukkitRunnable runnable) {
            this.runnable = runnable;
        }
    }

    public ShakeItUp() {
        super(NAME, RULES, HINT, MinigameType.FOUR_PLAYER);
        GameState.WALK_IN.setRunnable(new Loop_WalkIn(this));
        GameState.STARTING.setRunnable(new Loop_Starting(this));
        GameState.INPROGRESS.setRunnable(new Loop_InGame(this));
        GameState.ENDING.setRunnable(new Loop_Ending(this));
    }

    @Override
    protected void onInit() {

        packetListeners = new ArrayList<>();
        packetListeners.add(new Event_SteerVehiclePacket(this));

        for (PacketListener packetListener : packetListeners)
            ProtocolLibrary.getProtocolManager().addPacketListener(packetListener);

        Random random = new Random();
        Character[] WASD = {'W', 'A', 'S', 'D'};

        keyOrder = new ArrayList<>();

        int index = 0;
        while (keyOrder.size() < 200) {

            char nextIndex = WASD[random.nextInt(WASD.length)];
            if (keyOrder.size() == 0) {
                keyOrder.add(nextIndex);
                continue;
            }

            char lastIndex = keyOrder.get(index);
            if (lastIndex == nextIndex)
                continue;

            keyOrder.add(nextIndex);
            index++;
        }

        keyPresses = new HashMap<>();
        currentKey = new HashMap<>();

        int characterSpawn = 0;
        for (GamePlayer gp : Main.getGamePlayerManager().toCollection()) {

            gp.getPlayer().setGameMode(GameMode.ADVENTURE);
            gp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 100, false, false));

//            gp.getCharacter().spawnCharacterModel(CHARACTER_SPAWN[characterSpawn++]);
//            gp.getCharacter().getArmorStand().setItemInHand(new ItemStack(Material.EXP_BOTTLE));
//
//            gp.getCharacter().playAnimation(AnimationType.SHAKE_IT_UP_ARM_DOWN);

            gp.getPlayer().teleport(PLAYER_SPAWN);

            keyPresses.put(gp.getPlayer().getName(), 0);
            currentKey.put(gp.getPlayer().getName(), keyOrder.get(0));

            // Remove the player from whatever they're riding. This is to fix the bug that causes people to appear to be riding something while they aren't actually riding them ingame.
            gp.getPlayer().leaveVehicle();
        }

        constantLoop = new Loop_Constant(this);

        GameState.WALK_IN.getRunnable().runTaskTimer(Main.getInstance(), 0, 1L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> gameController = new Loop_GameController(this), 80L);
    }

    @Override
    protected void onEnd() {

        for (PacketListener packetListener : packetListeners)
            ProtocolLibrary.getProtocolManager().removePacketListener(packetListener);

        packetListeners = null;
        gameController = null;

        constantLoop.cancel();

        for (GamePlayer gp : Main.getGamePlayerManager().toCollection()) {
            gp.getPlayer().getVehicle().remove();
            gp.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);

            for (String s : keyPresses.keySet())
                gp.sendMessage(false, s + ": " + keyPresses.get(s));

//            gp.getCharacter().getArmorStand().remove(); // DEBUG
        }
    }

    public Loop_GameController getGameController() {
        return gameController;
    }

    public List<Character> getKeyOrder() {
        return keyOrder;
    }

    public HashMap<String, Character> getCurrentKey() {
        return currentKey;
    }

    public HashMap<String, Integer> getKeyPresses() {
        return keyPresses;
    }
}
