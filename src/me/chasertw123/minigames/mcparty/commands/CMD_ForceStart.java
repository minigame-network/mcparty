package me.chasertw123.minigames.mcparty.commands;

import me.chasertw123.minigames.mcparty.Main;
import me.chasertw123.minigames.mcparty.game.GameState;
import me.chasertw123.minigames.mcparty.loops.Loop_Lobby;
import me.chasertw123.minigames.shared.rank.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_ForceStart implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args) {
        if(!commandLabel.equalsIgnoreCase("ForceStart"))
            return true;

        if(commandSender instanceof Player && Main.getGameManager().getGameState() != GameState.LOBBY || Main.getGamePlayerManager().get((Player) commandSender).getCoreUser().getRank() != Rank.DEV) {
            commandSender.sendMessage("You can't do that! The game state is not lobby or you don't have permissions.");

            return true;
        }

        // Run the game start code
        ((Loop_Lobby) Main.getGameManager().getCurrentGameLoop()).setForceStart(true);

        return true;
    }

}
