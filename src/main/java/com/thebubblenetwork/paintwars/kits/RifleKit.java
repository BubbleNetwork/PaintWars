package com.thebubblenetwork.paintwars.kits;

import com.thebubblenetwork.api.framework.util.mc.items.ItemStackBuilder;
import com.thebubblenetwork.api.game.kit.Kit;
import com.thebubblenetwork.api.global.java.ArrayBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class RifleKit extends Kit {

    public static ChatColor COLOR = ChatColor.GREEN;

    public static ItemStackBuilder RIFLE = new ItemStackBuilder(Material.IRON_BARDING).withName(COLOR + "Rifle");
    public static ItemStackBuilder HELM = new ItemStackBuilder(Material.LEATHER_HELMET).withName(COLOR + "Rifler's Helmet");
    public static ItemStackBuilder CHEST = new ItemStackBuilder(Material.LEATHER_CHESTPLATE).withName(COLOR + "Rifler's Chestplate");
    public static ItemStackBuilder PANTS = new ItemStackBuilder(Material.LEATHER_LEGGINGS).withName(COLOR + "Rifler's pants");
    public static ItemStackBuilder BOOTS = new ItemStackBuilder(Material.LEATHER_BOOTS).withName(COLOR + "Riflers's boots");

    public static ArrayBuilder<ItemStack> DEFAULTBUILD = newBuilder(4 * 9).withT(0, RIFLE.build()).withT(1, HELM.build()).withT(2, CHEST.build()).withT(3, PANTS.build()).withT(4, BOOTS.build());

    public static ArrayBuilder<ItemStack> newBuilder(int size) {
        return new ArrayBuilder<>(ItemStack.class, size);
    }

    public RifleKit() {
        super(Material.IRON_BARDING,
                Arrays.asList(
                    DEFAULTBUILD.build(),
                    DEFAULTBUILD.clone()
                            .withT(0, RIFLE.build())
                            .withT(1, HELM.build())
                            .withT(2, CHEST.build())
                            .withT(3, PANTS.build())
                            .withT(4, BOOTS.build())
                            .build()
                ),
                "Rifle", new String[]{"Semi-automatic paintball rifle."}, 200);
    }


}
