package com.goldfrosch.plugin.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {
    private Player player;

    public InventoryUtils(Player player) {
        this.player = player;
    }

    public int getInventoryItemsAmount() {
        int amount = 0;
        for(ItemStack item: player.getInventory().getContents()) {
            if(item != null) {
                amount += item.getAmount();
            }
        }

        return amount;
    }
}
