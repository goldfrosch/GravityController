package com.goldfrosch.plugin.utils;

import com.goldfrosch.plugin.GravityController;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TimerUtils {
    private GravityController plugin;
    private Player player;

    public TimerUtils(GravityController plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void setGravity() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            InventoryUtils invUtils = new InventoryUtils(player);
            player.sendMessage(String.valueOf(invUtils.getInventoryItemsAmount()));
        },1, 20);
    }

    public void stopGravity() {
        Bukkit.getScheduler().cancelTasks(plugin);
    }
}
