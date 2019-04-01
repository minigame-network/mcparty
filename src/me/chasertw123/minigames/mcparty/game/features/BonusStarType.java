package me.chasertw123.minigames.mcparty.game.features;

import me.chasertw123.minigames.mcparty.player.data.GameStat;

public enum BonusStarType {

    MINIGAME_STAR("Minigame Star", "For the player who won the most coins in minigames", GameStat.MINIGAME_TOKENS_WON),
    HAPPENING_STAR("Happening Star", "For the player who landed on the most ? Spaces", GameStat.GREEN_SPACES_LANDED_ON),
    RUNNING_STAR("Running Star", "For the player who travel the most", GameStat.SPACES_TRAVELED),
    SHOPPING_STAR("Shopping Star", "For the player who bought the most at the shop", GameStat.TOKENS_SPENT_SHOPPING),
    RED_STAR("Red Star", "For the player who landed on the most Red Spaces", GameStat.RED_SPACES_LANDED_ON),
    CANDY_STAR("Candy Star", "For the player who ate the most candy", GameStat.CANDY_USED);

    private String display, description;
    private GameStat gameStat;

    BonusStarType(String display, String description, GameStat gameStat) {
        this.display = display;
        this.description = description;
        this.gameStat = gameStat;
    }

    public String getDisplay() {
        return display;
    }

    public String getDescription() {
        return description;
    }

    public GameStat getGameStat() {
        return gameStat;
    }
}
