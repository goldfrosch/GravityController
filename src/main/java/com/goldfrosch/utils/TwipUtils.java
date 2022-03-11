package com.goldfrosch.utils;

import com.goldfrosch.GravityController;
import com.outstandingboy.donationalert.platform.Twip;

import lombok.RequiredArgsConstructor;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

@RequiredArgsConstructor
public class TwipUtils {
  private final GravityController plugin;

  public boolean getRandomTF() {
    return Math.random() < 0.5;
  }

  public String resultTitle(String penalty) {
    return ChatColor.GRAY + "[ " + ChatColor.AQUA + penalty + " " + ChatColor.GRAY + "]";
  }

  public void donateAction() {
    Random random = new Random();
    String[] randomTwip = {
        "인벤토리 9칸 삭제",
        "돌 6세트 획득",
        "인벤토리 18칸 삭제",
        "돌 12세트 획득",
        "인벤토리 27칸 삭제",
        "돌 18세트 획득",
        "인벤토리 초기화",
    };

    try {
      Twip twip = new Twip("pDRed1bz29");
      twip.subscribeDonation((donation -> {
        if(!plugin.getStatus()) {
          return;
        }

        for(Player player: getServer().getOnlinePlayers()) {
          player.sendMessage(ChatColor.AQUA + String.valueOf(donation.getAmount()) + "원" +  ChatColor.YELLOW + "후원 감사합니다.");

          if(donation.getAmount() != 3000) return;

          player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);

          if(Math.random() < 0.55) {
            if(getRandomTF()) {
              player.sendTitle(ChatColor.GREEN + "벌칙 결과",resultTitle(randomTwip[0]),10,10,10);
              new InventoryUtils(player).setRandomListItemRemove(9);
            } else {
              player.sendTitle(ChatColor.GREEN + "벌칙 결과",resultTitle(randomTwip[1]),10,10,10);
              new InventoryUtils(player).addStonesPlayerInventory(6);
            }
          } else if(Math.random() < 0.85) {
            if(getRandomTF()) {
              player.sendTitle(ChatColor.GREEN + "벌칙 결과",resultTitle(randomTwip[2]),10,10,10);
              new InventoryUtils(player).setRandomListItemRemove(18);
            } else {
              player.sendTitle(ChatColor.GREEN + "벌칙 결과",resultTitle(randomTwip[3]),10,10,10);
              new InventoryUtils(player).addStonesPlayerInventory(12);
            }
          } else if(Math.random() < 0.99) {
            if(getRandomTF()) {
              player.sendTitle(ChatColor.GREEN + "벌칙 결과",resultTitle(randomTwip[4]),10,10,10);
              new InventoryUtils(player).setRandomListItemRemove(27);
            } else {
              player.sendTitle(ChatColor.GREEN + "벌칙 결과",resultTitle(randomTwip[5]),10,10,10);
              new InventoryUtils(player).addStonesPlayerInventory(18);
            }
          } else {
            player.sendTitle(ChatColor.GREEN + "벌칙 결과",resultTitle(randomTwip[6]),10,10,10);
            new InventoryUtils(player).clearPlayerInventory();
          }
        }
      }));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
