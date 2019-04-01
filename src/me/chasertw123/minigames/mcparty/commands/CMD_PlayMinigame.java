package me.chasertw123.minigames.mcparty.commands;

import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.GameState;
import me.chasertw123.minigames.mcparty.game.minigames.v2.Minigame;
import me.chasertw123.minigames.mcparty.player.GamePlayer;
import me.chasertw123.minigames.shared.rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_PlayMinigame implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use that command!");
            return true;
        }

        GamePlayer gp = Main.getGamePlayerManager().get((Player) sender);
//        if (gp.getCoreUser().getRank() != Rank.DEV) {
//            gp.sendMessage(false, ChatColor.RED + "You must be a developer to use that command!");
//            return true;
//        }

        if (Main.getGameManager().getGameState() != GameState.LOBBY) {
            gp.sendMessage(false, ChatColor.RED + "Can only test minigames during the lobby gamestate!");
            return true;
        }

        if (args.length != 1) {
            gp.sendMessage(false, ChatColor.RED + "Usage: /playmini <minigame>");
            return true;
        }

        Minigame minigame = Minigame.fromString(args[0]);
        if (minigame == null) {
            gp.sendMessage(false, ChatColor.RED + "Unknown minigame: " + args[0]);
            return true;
        }

        Main.getGameManager().setGameState(GameState.MINIGAME_TESTING);
        minigame.getGameInstance().initMinigame();

        return true;
    }
}
