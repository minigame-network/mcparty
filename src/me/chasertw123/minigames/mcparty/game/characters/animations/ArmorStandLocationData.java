package me.chasertw123.minigames.mcparty.game.characters.animations;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.Map;

public class ArmorStandLocationData {

    /**
     * This class allows for iteration over the armor stand points.
     */
    public enum ArmorStandLocationPoint {
        ITEM_IN_HAND(ArmorStandDataType.ITEM_STACK, ArmorStand::getItemInHand, (a, o) -> a.setItemInHand((ItemStack) o)),
        BOOTS(ArmorStandDataType.ITEM_STACK, ArmorStand::getBoots, (a, o) -> a.setBoots((ItemStack) o)),
        LEGGINGS(ArmorStandDataType.ITEM_STACK, ArmorStand::getLeggings, (a, o) -> a.setLeggings((ItemStack) o)),
        CHEST(ArmorStandDataType.ITEM_STACK, ArmorStand::getChestplate, (a, o) -> a.setChestplate((ItemStack) o)),
        HELMET(ArmorStandDataType.ITEM_STACK, ArmorStand::getHelmet, (a, o) -> a.setHelmet((ItemStack) o)),

        BODY_POSE(ArmorStandDataType.ANGLE, ArmorStand::getBodyPose, (a, o) -> a.setBodyPose((EulerAngle) o)),
        LEFT_ARM_POSE(ArmorStandDataType.ANGLE, ArmorStand::getLeftArmPose, (a, o) -> a.setLeftArmPose((EulerAngle) o)),
        RIGHT_ARM_POSE(ArmorStandDataType.ANGLE, ArmorStand::getRightArmPose, (a, o) -> a.setRightArmPose((EulerAngle) o)),
        LEFT_LEG_POSE(ArmorStandDataType.ANGLE, ArmorStand::getLeftLegPose, (a, o) -> a.setLeftLegPose((EulerAngle) o)),
        RIGHT_LEG_POSE(ArmorStandDataType.ANGLE, ArmorStand::getRightLegPose, (a, o) -> a.setRightLegPose((EulerAngle) o)),
        HEAD_POSE(ArmorStandDataType.ANGLE, ArmorStand::getHeadPose, (a, o) -> a.setHeadPose((EulerAngle) o)),

        BASE_PLATE(ArmorStandDataType.BOOLEAN, ArmorStand::hasBasePlate, (a, o) -> a.setBasePlate((boolean) o)),
        GRAVITY(ArmorStandDataType.BOOLEAN, ArmorStand::hasGravity, (a, o) -> a.setGravity((boolean) o)),
        VISIBLE(ArmorStandDataType.BOOLEAN, ArmorStand::isVisible, (a, o) -> a.setVisible((boolean) o)),
        ARMS(ArmorStandDataType.BOOLEAN, ArmorStand::hasArms, (a, o) -> a.setArms((boolean) o)),
        SMALL(ArmorStandDataType.BOOLEAN, ArmorStand::isSmall, (a, o) -> a.setSmall((boolean) o)),

        // Locations are relative to the stand, so add them instead of set.
        LOCATION(ArmorStandDataType.LOCATION, ArmorStand::getLocation, (a, o) -> a.teleport(a.getLocation().clone().add((Location) o)));

        private ArmorStandDataType armorStandDataType;
        private ArmorStandField armorStandField;
        private ArmorStandSetter armorStandSetter;

        ArmorStandLocationPoint(ArmorStandDataType armorStandDataType, ArmorStandField armorStandField, ArmorStandSetter armorStandSetter) {
            this.armorStandDataType = armorStandDataType;
            this.armorStandField = armorStandField;
            this.armorStandSetter = armorStandSetter;
        }

        public ArmorStandSetter getArmorStandSetter() {
            return armorStandSetter;
        }

        public ArmorStandDataType getArmorStandDataType() {
            return armorStandDataType;
        }

        public ArmorStandField getArmorStandField() {
            return armorStandField;
        }

        public Object getField(ArmorStand armorStand) {
            return armorStandField.getField(armorStand);
        }

        public EulerAngle getFieldAsAngle(ArmorStand armorStand) {
            if (this.armorStandDataType == ArmorStandDataType.ANGLE)
                return (EulerAngle) getField(armorStand);

            return null;
        }

        public boolean getFieldAsBoolean(ArmorStand armorStand) {
            if (this.armorStandDataType == ArmorStandDataType.BOOLEAN)
                return (boolean) getField(armorStand);

            return false; // Can't return null on boolean
        }

        public ItemStack getFieldAsItemStack(ArmorStand armorStand) {
            if (this.armorStandDataType == ArmorStandDataType.ITEM_STACK)
                return (ItemStack) getField(armorStand);

            return null;
        }

        public void setField(ArmorStand armorStand, Object data) {
            armorStandSetter.setField(armorStand, data);
        }
    }

    public enum ArmorStandDataType {
        BOOLEAN, ANGLE, ITEM_STACK, LOCATION
    }

    public interface ArmorStandField {
        Object getField(ArmorStand armorStand);
    }

    public interface ArmorStandSetter {
        void setField(ArmorStand a, Object o);
    }

    private Map<ArmorStandLocationPoint, Object> data;

    public ArmorStandLocationData(Map<ArmorStandLocationPoint, Object> data) {
        this.data = data;

        System.out.println("Data Map: " + this.data.toString());
    }

    public Map<ArmorStandLocationPoint, Object> getData() {
        return data;
    }

    public Object getDataAt(ArmorStandLocationPoint point) {
        return data.get(point);
    }

    public void updateAllPoints(ArmorStand armorStand) {
//        System.out.println("Data size: " + data.size());

        for(ArmorStandLocationPoint point : ArmorStandLocationPoint.values()) {
            if(!data.containsKey(point))
                continue;

//            System.out.println("Setting value: " + point + " to " + data.get(point).toString());

            if(data.get(point) instanceof EulerAngle) {
                EulerAngle angle = (EulerAngle) data.get(point);
//                System.out.println("Euler Angle: " + angle.getX() + ", " + angle.getY() + ", " + angle.getZ());
            }

            point.setField(armorStand, data.get(point));
        }
    }

}
