package com.goldfrosch.utils;

import com.outstandingboy.donationalert.platform.Twip;

import lombok.NoArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.IOException;

import static org.bukkit.Bukkit.getServer;

@NoArgsConstructor
public class TwipUtils {
  public void donateAction() {
    try {
      Twip twip = new Twip("pDRed1bz29");
      twip.subscribeDonation((donation -> {
        for(Player player: getServer().getOnlinePlayers()) {
          player.sendTitle("", donation.getAmount() + "원 후원 감사합니다 :)",1,1,1);
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
              player.sendTitle("", "엄",1,1,1);
              Thread.sleep(200);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
