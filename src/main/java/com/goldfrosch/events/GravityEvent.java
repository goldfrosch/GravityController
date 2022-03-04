package com.goldfrosch.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.goldfrosch.GravityController;
import com.goldfrosch.utils.GravityUtils;

import lombok.RequiredArgsConstructor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

@RequiredArgsConstructor
public class GravityEvent implements Listener {
  private final GravityController plugin;

  @EventHandler
  public void onPlayerDropEvent(PlayerDropItemEvent e) {
    new GravityUtils(plugin, e.getPlayer()).setGravity();
  }

  @EventHandler
  public void onPlayerPickupEvent(PlayerAttemptPickupItemEvent e) {
    new GravityUtils(plugin, e.getPlayer()).setGravity();
  }

  @EventHandler
  public void onPlayerJumpEvent(PlayerJumpEvent e) {
    GravityUtils gravityUtils = new GravityUtils(plugin, e.getPlayer());
    int invAmounts = gravityUtils.playerInvAmounts();

    if(plugin.getStatus() && invAmounts >= 1152) {
      e.setCancelled(true);
      Location floor = e.getFrom();
      floor.setY(e.getFrom().getBlockY() - 1);

      floor.getBlock().setType(Material.AIR);
    }
  }

  @EventHandler
  public void onPlayerConsumeEvent(PlayerItemConsumeEvent e) {
    if(e.getItem().getType().equals(Material.MILK_BUCKET)) {
      new GravityUtils(plugin, e.getPlayer()).setGravity();
    }
  }
}
