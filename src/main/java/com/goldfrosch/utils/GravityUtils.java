package com.goldfrosch.utils;

import com.goldfrosch.GravityController;

import lombok.RequiredArgsConstructor;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class GravityUtils {
  private final GravityController plugin;
  private final Player player;

  public int playerInvAmounts() {
    InventoryUtils invUtils = new InventoryUtils(player);
    return invUtils.getInventoryItemsAmount();
  }

  public void setPlayerLevitation(int power) {
    player.addPotionEffect(PotionEffectType.LEVITATION.createEffect(100000, power));
    player.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(100000, 5));
  }

  public void setGravity() {
    if(plugin.getStatus()) {
      int invAmounts = playerInvAmounts();

      if(invAmounts >= 1152) {
        player.removePotionEffect(PotionEffectType.LEVITATION);
      }

      if(invAmounts < 10) {
        setPlayerLevitation(5);
      } else if(invAmounts < 576) {
        setPlayerLevitation(3);
      } else if(invAmounts < 864) {
        setPlayerLevitation(1);
      } else if(invAmounts < 1152) {
        player.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(100000, 5));
        player.removePotionEffect(PotionEffectType.LEVITATION);
      }
    }
  }
}
