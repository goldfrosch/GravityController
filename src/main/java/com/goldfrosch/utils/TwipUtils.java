package com.goldfrosch.utils;

import com.goldfrosch.GravityController;
import com.outstandingboy.donationalert.platform.Twip;

import lombok.RequiredArgsConstructor;

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
          player.sendTitle("", donation.getAmount() + "원 후원 감사합니다 :)",0,10,0);
          player.sendMessage(donation.getAmount() + "원");
          Thread.sleep(1000);
          double[] pitchList = new double[] {
              0.5, 0.6, 0.7, 0.9,
              0.6, 0.7, 0.8, 1.0,
              0.7, 0.8, 0.9, 1.1,
              0.8, 0.9, 1.0, 1.2
          };

          for(double pitch: pitchList) {
            try {
              player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 10, (float) pitch);
              player.sendTitle("룰렛 추첨중",randomTwip[random.nextInt(randomTwip.length)],0,10,0);
              Thread.sleep(200);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }

          if(Math.random() < 0.55) {
            if(getRandomTF()) {
              player.sendTitle("룰렛 결과",randomTwip[0],0,10,0);
              new InventoryUtils(player).setRandomListItemRemove(9);
            } else {
              player.sendTitle("룰렛 결과",randomTwip[1],0,10,0);
              new InventoryUtils(player).addStonesPlayerInventory(6);
            }
          } else if(Math.random() < 0.75) {
            if(getRandomTF()) {
              player.sendTitle("룰렛 결과",randomTwip[2],0,10,0);
              new InventoryUtils(player).setRandomListItemRemove(18);
            } else {
              player.sendTitle("룰렛 결과",randomTwip[3],0,10,0);
              new InventoryUtils(player).addStonesPlayerInventory(12);
            }
          } else if(Math.random() < 0.95) {
            if(getRandomTF()) {
              player.sendTitle("룰렛 결과",randomTwip[4],0,10,0);
              new InventoryUtils(player).setRandomListItemRemove(27);
            } else {
              player.sendTitle("룰렛 결과",randomTwip[5],0,10,0);
              new InventoryUtils(player).addStonesPlayerInventory(18);
            }
          } else {
            player.sendTitle("룰렛 결과",randomTwip[6],0,10,0);
            new InventoryUtils(player).clearPlayerInventory();
          }
        }
      }));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
