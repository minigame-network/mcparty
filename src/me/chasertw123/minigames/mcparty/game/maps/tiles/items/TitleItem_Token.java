//package net.mcparadise.mcparty.game.maps.tiles.items;
//
//import net.mcparadise.core.api.libraries.cItemStack;
//import net.mcparadise.mcparty.Main;
//import net.mcparadise.mcparty.game.maps.tiles.TileLocation;
//import net.mcparadise.mcparty.game.maps.tiles.UnplayableTile;
//import net.mcparadise.mcparty.player.GamePlayer;
//
//import net.mcparadise.mcparty.player.data.GameStat;
//import org.bukkit.*;
//import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.ArmorStand;
//import org.bukkit.entity.EntityType;
//import org.bukkit.entity.Player;
//import org.bukkit.util.EulerAngle;
//
///**
// * Created by Chase on 8/23/2017.
// */
//public class TitleItem_Token implements TileItem {
//
//    private double interval = 1, yOffset = 0;
//    private boolean goingUp = true;
//
//    private ArmorStand armorStand;
//    private Location baseLocation;
//
//    @Override
//    public void spawnOnMapSpace(UnplayableTile mapSpace) {
//        this.baseLocation = mapSpace.getMapSpaceLocation(TileLocation.CENTER).clone().add(0D, 2.045D, 0D);
//        this.armorStand = (ArmorStand) baseLocation.getWorld().spawnEntity(baseLocation, EntityType.ARMOR_STAND);
//
//        armorStand.setGravity(false);
//        armorStand.setVisible(false);
//        armorStand.setCanPickupItems(false);
//        armorStand.setSmall(true);
//        armorStand.setHeadPose(new EulerAngle(0, 0, 0));
//        armorStand.getEquipment().setHelmet(new cItemStack(Material.GOLD_BLOCK).addEnchant(Enchantment.LUCK, 1));
//        armorStand.setCustomName(ChatColor.GOLD + "" + ChatColor.BOLD + "+1 TOKEN");
//        armorStand.setCustomNameVisible(true);
//
//        Main.getInstance().getGameManager().getAnimationPlayer().addAnimation(this);
//    }
//
//    @Override
//    public void playAnimationFrame() {
//
//        if (goingUp && yOffset >= 1.5)
//            goingUp = false;
//
//        else if (!goingUp && yOffset <= 0)
//            goingUp = true;
//
//        yOffset += goingUp ? 0.05 : -0.05;
//
//        armorStand.teleport(baseLocation.clone().add(0D, yOffset, 0D));
//        armorStand.setHeadPose(new EulerAngle(0, Math.toRadians(interval++ * 1.8), 0));
//    }
//
//    @Override
//    public void giveSpaceItemToPlayer(GamePlayer gp) {
//
//        gp.incrementStat(GameStat.TOKEN);
//        Main.getInstance().getGameManager().getAnimationPlayer().removeAnimation(this);
//        armorStand.remove();
//
//        for (Player player : Bukkit.getOnlinePlayers()) {
//            player.playSound(player.getLocation(), Sound.NOTE_PLING, 2F, 2F);
//            player.playSound(player.getLocation(), Sound.NOTE_BASS, 2F, 2F);
//        }
//    }
//}
