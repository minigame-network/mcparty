package me.chasertw123.minigames.mcparty.game.minigames.v2;

import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.mcparty.game.minigames.v2.cranktorank.CrankToRank;
import me.chasertw123.minigames.mcparty.game.minigames.v2.shakeitup.ShakeItUp;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Minigame {

    SHAKE_IT_UP("Shake It Up", MinigameType.FOUR_PLAYER, Material.POTION, ShakeItUp.class),
    CRANK_TO_RANK("Crank To Rank", MinigameType.FOUR_PLAYER, Material.BANNER, CrankToRank.class);

    private String name;
    private MinigameType type;
    private ItemStack item;
    private Class<? extends Game> clazz;

    Minigame(String name, MinigameType type, ItemStack item, Class<? extends Game> clazz) {
        this.name = name;
        this.type = type;
        this.item = item;
        this.clazz = clazz;
    }

    Minigame(String name, MinigameType type, Material material, Class<? extends Game> clazz) {
        this.name = name;
        this.type = type;
        this.item = new cItemStack(material);
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public MinigameType getType() {
        return type;
    }

    public Game getGameInstance() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Minigame fromString(String s) {
        for (Minigame minigame : Minigame.values())
            if (minigame.toString().equalsIgnoreCase(s) || minigame.getName().equalsIgnoreCase(s))
                return minigame;

        return null;
    }
}
