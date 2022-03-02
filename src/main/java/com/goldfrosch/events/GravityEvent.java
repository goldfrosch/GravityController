package com.goldfrosch.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.goldfrosch.GravityController;
import com.goldfrosch.utils.InventoryUtils;

import lombok.RequiredArgsConstructor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class GravityEvent implements Listener {
  private final GravityController plugin;

  private Map<UUID, Integer> playerInvAmount = new HashMap<UUID, Integer>();

  public void setPlayerLevitation(Player player, int power) {
    player.addPotionEffect(PotionEffectType.LEVITATION.createEffect(100000, power));
    player.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(100000, 5));
  }

  @EventHandler
  public void onInventoryDropEvent(PlayerDropItemEvent e) {
    if(plugin.getStatus()) {
      InventoryUtils invUtils = new InventoryUtils(e.getPlayer());
      int invAmounts = playerInvAmount.getOrDefault(e.getPlayer().getUniqueId(), invUtils.getInventoryItemsAmount());

      if(invAmounts >= 1152) {
        e.getPlayer().removePotionEffect(PotionEffectType.LEVITATION);
      }

      playerInvAmount.put(e.getPlayer().getUniqueId(), invUtils.getInventoryItemsAmount());

      if(invAmounts < 10) {
        setPlayerLevitation(e.getPlayer(), 5);
      } else if(invAmounts < 576) {
        setPlayerLevitation(e.getPlayer(), 3);
      } else if(invAmounts < 864) {
        setPlayerLevitation(e.getPlayer(), 1);
      } else if(invAmounts < 1152) {
        e.getPlayer().addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(100000, 5));
        e.getPlayer().removePotionEffect(PotionEffectType.LEVITATION);
      }
    }
  }

  @EventHandler
  public void onPlayerJumpEvent(PlayerJumpEvent e) {
    InventoryUtils invUtils = new InventoryUtils(e.getPlayer());
    int invAmounts = playerInvAmount.getOrDefault(e.getPlayer().getUniqueId(), invUtils.getInventoryItemsAmount());
    if(plugin.getStatus() && invAmounts >= 1152) {
      e.setCancelled(true);
    }
  }
}
