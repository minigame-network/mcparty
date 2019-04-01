package me.chasertw123.minigames.mcparty.game.characters.v2;

import me.chasertw123.minigames.mcparty.game.characters.animations.*;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.util.EulerAngle;

import java.util.HashMap;
import java.util.Map;

public enum GameCharacter {

    RED("Red"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    GREEN("Green");

    private String id;
    private ArmorStand armorStand;
    private Map<CharacterAnimationType, Animation> animations;
    private Animation currentAnimation = null;

    GameCharacter(String id) {
        this.id = id;
    }

    public void spawnEntity(Location location) {
        this.armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        animations = new HashMap<>();

//        animations.put(CharacterAnimationType.RUNNING, new AnimationBuilder(this.armorStand)
//                .setAnimationType(AnimationType.SINGLE)
//                .setPoint(ArmorStandLocationData.ArmorStandLocationPoint.LEFT_ARM_POSE, new EulerAngle(0, 0, 0))
//                .nextKeyframe(20)
//                .setPoint(ArmorStandLocationData.ArmorStandLocationPoint.LEFT_ARM_POSE, new EulerAngle(1, 0, 0))
//                .nextKeyframe(20)
//                .setPoint(ArmorStandLocationData.ArmorStandLocationPoint.LEFT_ARM_POSE, new EulerAngle(0, 0, 0))
//                .nextKeyframe(20)
//                .build());
//        animations.put(CharacterAnimationType.WALKING, new AnimationBuilder(this.armorStand).build()); TODO: Create animation
    }

    public String getId() {
        return id;
    }

    public void playAnimation(CharacterAnimationType type, OnAnimationEnd onAnimationEnd) {
        if(type == CharacterAnimationType.NONE && currentAnimation != null) {
            currentAnimation.stop();
            currentAnimation = null;

            return;
        }

        currentAnimation = animations.get(type);

        if(onAnimationEnd != null && currentAnimation.getAnimationType() == AnimationType.SINGLE)
            currentAnimation.setOnAnimationEnd(onAnimationEnd);

        currentAnimation.start();
    }

    public void playAnimation(CharacterAnimationType type) {
        playAnimation(type, null);
    }

    public ArmorStand getArmorStand() {
        return this.armorStand;
    }

    public enum CharacterAnimationType {
        WALKING, RUNNING, NONE
    }

}
