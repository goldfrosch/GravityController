package com.goldfrosch.plugin.events;

import com.goldfrosch.plugin.GravityController;
import com.outstandingboy.donationalert.platform.Twip;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.io.IOException;

public class NewEvent implements Listener {
  private final GravityController plugin;

  public NewEvent(GravityController plugin) {
    this.plugin = plugin;

    try {
      Twip twip = new Twip("pDRed1bz29");
      twip.subscribeDonation((donation -> {
        Bukkit.broadcastMessage(String.valueOf(donation.getAmount()));
      }));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
