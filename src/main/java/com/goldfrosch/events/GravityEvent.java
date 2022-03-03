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

@RequiredArgsConstructor
public class GravityEvent implements Listener {
  private final GravityController plugin;

  @EventHandler
  public void onPlayerDropEvent(PlayerDropItemEvent e) {
    GravityUtils gravityUtils = new GravityUtils(plugin, e.getPlayer());
    gravityUtils.setGravity();
  }

  @EventHandler
  public void onPlayerPickupEvent(PlayerAttemptPickupItemEvent e) {
    GravityUtils gravityUtils = new GravityUtils(plugin, e.getPlayer());
    gravityUtils.setGravity();
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
}
