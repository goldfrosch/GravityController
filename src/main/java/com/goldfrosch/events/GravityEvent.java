package com.goldfrosch.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.goldfrosch.GravityController;
import com.goldfrosch.utils.InventoryUtils;

import lombok.RequiredArgsConstructor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class GravityEvent implements Listener {
  private final GravityController plugin;

  private Map<UUID, Integer> playerInvAmount = new HashMap<>();

  public void setPlayerLevitation(Player player, int power) {
    player.addPotionEffect(PotionEffectType.LEVITATION.createEffect(100000, power));
    player.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(100000, 5));
  }

  public void setGravity(Player player) {
    if(plugin.getStatus()) {
      InventoryUtils invUtils = new InventoryUtils(player);
      int invAmounts = playerInvAmount.getOrDefault(player.getUniqueId(), invUtils.getInventoryItemsAmount());

      if(invAmounts >= 1152) {
        player.removePotionEffect(PotionEffectType.LEVITATION);
      }

      playerInvAmount.put(player.getUniqueId(), invUtils.getInventoryItemsAmount());

      if(invAmounts < 10) {
        setPlayerLevitation(player, 5);
      } else if(invAmounts < 576) {
        setPlayerLevitation(player, 3);
      } else if(invAmounts < 864) {
        setPlayerLevitation(player, 1);
      } else if(invAmounts < 1152) {
        player.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(100000, 5));
        player.removePotionEffect(PotionEffectType.LEVITATION);
      }
    }
  }

  @EventHandler
  public void onPlayerDropEvent(PlayerDropItemEvent e) {
    setGravity(e.getPlayer());
  }

  @EventHandler
  public void onPlayerPickupEvent(PlayerAttemptPickupItemEvent e) {
    setGravity(e.getPlayer());
  }

  @EventHandler
  public void onPlayerJumpEvent(PlayerJumpEvent e) {
    InventoryUtils invUtils = new InventoryUtils(e.getPlayer());
    int invAmounts = playerInvAmount.getOrDefault(e.getPlayer().getUniqueId(), invUtils.getInventoryItemsAmount());
    if(plugin.getStatus() && invAmounts >= 1152) {
      e.setCancelled(true);
      Location floor = e.getFrom();
      floor.setY(e.getFrom().getBlockY() - 1);

      floor.getBlock().setType(Material.AIR);
    }
  }
}
