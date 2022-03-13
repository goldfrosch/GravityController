package com.goldfrosch.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryUtils {
    private final Player player;

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

    public List<Integer> getInventoryNotNullIgnoreSlot() {
        List<Integer> nowInvNum = new ArrayList<>();

        for(int i = 9; i < player.getInventory().getContents().length; i++ ) {
            if(player.getInventory().getContents()[i] != null) {
                nowInvNum.add(i);
            }
        }

        return nowInvNum;
    }

    public void setRandomListItemRemove(int count) {
        List<Integer> list = getInventoryNotNullIgnoreSlot();
        Collections.shuffle(list);

        Object[] itemList = list.toArray();

        for(int i = 0;i < count; i++) {
            try {
                player.getInventory().getContents()[(int) itemList[i]].setType(Material.AIR);
            }  catch(ArrayIndexOutOfBoundsException e) {
                return;
            }
        }
    }

    public void addStonesPlayerInventory(int count) {
        player.getInventory().addItem(new ItemStack(Material.STONE, 64 * count));
    }

    public void clearPlayerInventory() {
        player.getInventory().clear();
    }
}
