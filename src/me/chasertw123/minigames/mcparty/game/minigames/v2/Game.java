package me.chasertw123.minigames.mcparty.game.minigames.v2;

import me.chasertw123.minigames.core.api.misc.Title;
import me.chasertw123.minigames.core.utils.items.AbstractItem;
import me.chasertw123.minigames.core.utils.items.Items;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.GameState;
import me.chasertw123.minigames.mcparty.game.guis.Gui_MapVote;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import me.chasertw123.minigames.mcparty.player.data.GameStat;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;

import java.util.Arrays;

public abstract class Game {

    private String name, rules, hint;
    private MinigameType minigameType;

    public Game(String name, String rules, String hint, MinigameType minigameType) {
        this.name = name;
        this.rules = rules;
        this.hint = hint;
        this.minigameType = minigameType;
    }

    public String getName() {
        return name;
    }

    public String getRules() {
        return rules;
    }

    public String getHint() {
        return hint;
    }

    public MinigameType getMinigameType() {
        return minigameType;
    }

    protected abstract void onInit();

    protected abstract void onEnd();

    public void initMinigame() {

        onInit();

        Main.getGamePlayerManager().toCollection().forEach(gp -> {
            Title.sendTitle(gp.getPlayer(), 0, 20, 0, ChatColor.GREEN + this.getName());
            gp.sendMessage(false, ChatColor.YELLOW + "" + ChatColor.BOLD + "Rules: " + ChatColor.RESET + this.getRules());
            gp.sendMessage(false, ChatColor.YELLOW + "" + ChatColor.BOLD + "Hint: " + ChatColor.RESET + this.getHint());
        });
    }

    public void stopMinigame() {

        onEnd();

        if (Main.getGameManager().getGameState() == GameState.MINIGAME_TESTING) {
            Main.getGamePlayerManager().toCollection().forEach(gp -> {

                Arrays.stream(GameStat.values()).forEach(stat -> gp.setStat(stat, 0));

                gp.getPlayer().setHealth(20);
                gp.getPlayer().setFoodLevel(20);
                gp.getPlayer().teleport(Main.getMapManager().getLobbyWorld().getSpawnLocation().clone().add(0.5, 0, 0.5));
                gp.getPlayer().setGameMode(GameMode.ADVENTURE);
                gp.getPlayer().getInventory().clear();

                new AbstractItem(Items.MAP_VOTING.getItemStack(), gp.getCoreUser(), 0, (it) -> new Gui_MapVote(gp));
                new AbstractItem(new cItemStack(Material.NETHER_STAR).setDisplayName(ChatColor.GREEN + "Select Character "
                        + ChatColor.GRAY + "(Right Click)"), gp.getCoreUser(), 1, (it) -> {});
                new AbstractItem(Items.RETURN_TO_HUB.getItemStack(), gp.getCoreUser(), 8, (it) -> gp.getCoreUser().sendToServer("hub"));
            });

            Main.getGameManager().setGameState(GameState.LOBBY);
            return;
        }

        // TODO: Teleport back to gameboard

    }

}
