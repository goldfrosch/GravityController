package com.goldfrosch.utils;

import com.goldfrosch.GravityController;
import com.outstandingboy.donationalert.platform.Twip;

import lombok.RequiredArgsConstructor;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

import static org.bukkit.Bukkit.getServer;

@RequiredArgsConstructor
public class TwipUtils {
  private final GravityController plugin;

  public void donateAction() {
    try {
      Twip twip = new Twip("pDRed1bz29");
      twip.subscribeDonation((donation -> {
        if(!plugin.getStatus()) {
          return;
        }
        for(Player player: getServer().getOnlinePlayers()) {
          if(donation.getAmount() != 3000) return;
          player.sendMessage(ChatColor.AQUA + String.valueOf(donation.getAmount()) + "원" +  ChatColor.YELLOW + "후원 감사합니다.");

          new GravityUtils(plugin, player).addPenalty();
        }
      }));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
