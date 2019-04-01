package me.chasertw123.minigames.mcparty.player.data;

/**
 * Created by Chase on 8/22/2017.
 */
public enum GameStat {

    TOKEN("Tokens"),
    STAR("Stars"),
    BLUE_SPACE_LANDED_ON("Blue Spaces Landed On"),
    RED_SPACES_LANDED_ON("Red Spaces Landed On"),
    GREEN_SPACES_LANDED_ON("Green Spaces Landed On"),
    LUCKY_SPACES_LANDED_ON("Lucky Spaces Landed On"),
    DONKEY_KONG_SPACES_LANDED_ON("[PH] Donkey Kong Spaces Landed On"),
    BOWSER_SPACES_LANDED_ON("[PH] Bowser Spaces Landed On"),
    MINIGAME_TOKENS_WON("Minigame Points"),
    SPACES_TRAVELED("Spaces Traveled"),
    CANDY_USED("Candies Eaten"),
    CANDY_COLLECTED("Candies Obtained"),
    TOKENS_SPENT_SHOPPING("Tokens Spent Shopping");

    private String display;

    GameStat(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
