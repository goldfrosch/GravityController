package com.goldfrosch.utils;

import com.goldfrosch.GravityController;

import lombok.RequiredArgsConstructor;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

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

  public boolean getRandomTF() {
    return Math.random() < 0.5;
  }

  public void resultTitle(String penalty) {
    player.sendTitle(ChatColor.GREEN + "벌칙 결과", ChatColor.GRAY + "[ " + ChatColor.AQUA + penalty + " " + ChatColor.GRAY + "]",10,10,10);
  }

  public void setGravity() {
    if(plugin.getStatus()) {
      int invAmounts = playerInvAmounts();

      if(invAmounts >= 1728) {
        player.removePotionEffect(PotionEffectType.LEVITATION);
      }

      if(invAmounts < 10) {
        setPlayerLevitation(5);
      } else if(invAmounts < 864) {
        setPlayerLevitation(3);
      } else if(invAmounts < 1280) {
        setPlayerLevitation(1);
      } else if(invAmounts < 1728) {
        player.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(100000, 5));
        player.removePotionEffect(PotionEffectType.LEVITATION);
      }
    }
  }

  public void addPenalty() {
    String[] randomTwip = {
        "인벤토리 9칸 삭제",
        "돌 6세트 획득",
        "인벤토리 18칸 삭제",
        "돌 12세트 획득",
        "인벤토리 27칸 삭제",
        "돌 18세트 획득",
        "인벤토리 초기화",
    };

    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);

    if(Math.random() < 0.55) {
      if(getRandomTF()) {
        resultTitle(randomTwip[0]);
        new InventoryUtils(player).setRandomListItemRemove(9);
      } else {
        resultTitle(randomTwip[1]);
        new InventoryUtils(player).addStonesPlayerInventory(6);
      }
    } else if(Math.random() < 0.85) {
      if(getRandomTF()) {
        resultTitle(randomTwip[2]);
        new InventoryUtils(player).setRandomListItemRemove(18);
      } else {
        resultTitle(randomTwip[3]);
        new InventoryUtils(player).addStonesPlayerInventory(12);
      }
    } else if(Math.random() < 0.99) {
      if(getRandomTF()) {
        resultTitle(randomTwip[4]);
        new InventoryUtils(player).setRandomListItemRemove(27);
      } else {
        resultTitle(randomTwip[5]);
        new InventoryUtils(player).addStonesPlayerInventory(18);
      }
    } else {
      resultTitle(randomTwip[6]);
      new InventoryUtils(player).clearPlayerInventory();
    }

    new BukkitRunnable() {
      @Override
      public void run() {
        setGravity();
      }
    }.runTaskTimer(plugin, 1L, 1L);
  }
}
