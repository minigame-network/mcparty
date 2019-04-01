package me.chasertw123.minigames.mcparty.game.characters;

public enum CharacterSkin {

    MARIO("", "");

    private String value, signature;

    CharacterSkin(String value, String signature) {
        this.value = value;
        this.signature = signature;
    }

    public String getValue() {
        return value;
    }

    public String getSignature() {
        return signature;
    }
}
