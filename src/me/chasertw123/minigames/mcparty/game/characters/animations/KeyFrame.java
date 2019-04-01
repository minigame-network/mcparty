package me.chasertw123.minigames.mcparty.game.characters.animations;

public class KeyFrame extends Frame {

    private int framesSinceLastKeyframe;

    public KeyFrame(int framesSinceLastKeyframe, ArmorStandLocationData armorStandLocationData) {
        super(armorStandLocationData);

        this.framesSinceLastKeyframe = framesSinceLastKeyframe;
    }

    public int getFramesSinceLastKeyframe() {
        return framesSinceLastKeyframe;
    }

}
