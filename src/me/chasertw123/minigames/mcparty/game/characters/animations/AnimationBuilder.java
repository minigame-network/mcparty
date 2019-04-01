package me.chasertw123.minigames.mcparty.game.characters.animations;

import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationBuilder {

    private List<KeyFrame> frames;
    private ArmorStand armorStand;
    private int delaySinceLast, fps;
    private Map<ArmorStandLocationData.ArmorStandLocationPoint, Object> data;
    private AnimationType animationType;
    private OnAnimationEnd onAnimationEnd;

    public AnimationBuilder(ArmorStand entity) {
        this.armorStand = entity;
        this.frames = new ArrayList<>();
        this.delaySinceLast = -1;
        this.data = new HashMap<>();
        this.animationType = AnimationType.SINGLE;
        this.fps = 20;
        this.onAnimationEnd = null;

        nextKeyframe(0);
    }

    public AnimationBuilder setFps(int fps) {
        this.fps = fps;

        return this;
    }

    public AnimationBuilder withOnAnimationEnd(OnAnimationEnd onAnimationEnd) {
        this.onAnimationEnd = onAnimationEnd;

        return this;
    }

    public AnimationBuilder setAnimationType(AnimationType animationType) {
        this.animationType = animationType;

        return this;
    }

    public AnimationBuilder nextKeyframe(int delaySinceLast) {

        if(delaySinceLast == -1) {
            this.delaySinceLast = delaySinceLast;

            return this;
        }

        if(data.size() == 0)
            return this; // Would be blank

        HashMap<ArmorStandLocationData.ArmorStandLocationPoint, Object> tempData = new HashMap<>(data);

        frames.add(new KeyFrame(delaySinceLast, new ArmorStandLocationData(tempData)));

        this.delaySinceLast = delaySinceLast;
        this.data = new HashMap<>();

        return this;
    }

    public AnimationBuilder setPoint(ArmorStandLocationData.ArmorStandLocationPoint point, EulerAngle newValue) {
        data.put(point, newValue);

        return this;
    }

    public AnimationBuilder setBoolean(ArmorStandLocationData.ArmorStandLocationPoint point, boolean newValue) {
        data.put(point, newValue);

        return this;
    }

    public AnimationBuilder setItemStack(ArmorStandLocationData.ArmorStandLocationPoint point, ItemStack newValue) {
        data.put(point, newValue);

        return this;
    }

    public Animation build() {
        return new Animation(armorStand, frames, fps, animationType, onAnimationEnd);
    }

    // A Method for every single one of the ArmorStandLocationPoint

    public AnimationBuilder setLeftArm(EulerAngle angle) {
        return setPoint(ArmorStandLocationData.ArmorStandLocationPoint.LEFT_ARM_POSE, angle);
    }

    public AnimationBuilder setRightArm(EulerAngle angle) {
        return setPoint(ArmorStandLocationData.ArmorStandLocationPoint.RIGHT_ARM_POSE, angle);
    }

    public AnimationBuilder setLeftLeg(EulerAngle angle) {
        return setPoint(ArmorStandLocationData.ArmorStandLocationPoint.LEFT_LEG_POSE, angle);
    }

    public AnimationBuilder setRightLeg(EulerAngle angle) {
        return setPoint(ArmorStandLocationData.ArmorStandLocationPoint.RIGHT_LEG_POSE, angle);
    }

    public AnimationBuilder setBodyPose(EulerAngle angle) {
        return setPoint(ArmorStandLocationData.ArmorStandLocationPoint.BODY_POSE, angle);
    }

    public AnimationBuilder setHeadPose(EulerAngle angle) {
        return setPoint(ArmorStandLocationData.ArmorStandLocationPoint.HEAD_POSE, angle);
    }

    // Booleans

    public AnimationBuilder setUseBasePlate(boolean state) {
        return setBoolean(ArmorStandLocationData.ArmorStandLocationPoint.BASE_PLATE, state);
    }

    public AnimationBuilder setUseGravity(boolean state) {
        return setBoolean(ArmorStandLocationData.ArmorStandLocationPoint.GRAVITY, state);
    }

    public AnimationBuilder setUseArms(boolean state) {
        return setBoolean(ArmorStandLocationData.ArmorStandLocationPoint.ARMS, state);
    }

    public AnimationBuilder setSmall(boolean state) {
        return setBoolean(ArmorStandLocationData.ArmorStandLocationPoint.SMALL, state);
    }

    public AnimationBuilder setVisible(boolean state) {
        return setBoolean(ArmorStandLocationData.ArmorStandLocationPoint.VISIBLE, state);
    }

    // Items

    public AnimationBuilder setItemInHand(ItemStack item) {
        return setItemStack(ArmorStandLocationData.ArmorStandLocationPoint.ITEM_IN_HAND, item);
    }

    public AnimationBuilder setBoots(ItemStack item) {
        return setItemStack(ArmorStandLocationData.ArmorStandLocationPoint.BOOTS, item);
    }

    public AnimationBuilder setLeggings(ItemStack item) {
        return setItemStack(ArmorStandLocationData.ArmorStandLocationPoint.LEGGINGS, item);
    }

    public AnimationBuilder setChestplate(ItemStack item) {
        return setItemStack(ArmorStandLocationData.ArmorStandLocationPoint.CHEST, item);
    }

    public AnimationBuilder setHelmet(ItemStack item) {
        return setItemStack(ArmorStandLocationData.ArmorStandLocationPoint.HELMET, item);
    }

}
