package com.goldfrosch.shedulers;

import com.goldfrosch.GravityController;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@RequiredArgsConstructor
public class PlayerGravity implements Runnable{
  private final GravityController plugin;
  private final Player player;

  public void run() {
    if(plugin.getStatus()) {
      if(player.getLocation().getBlockY() > 256) {
        player.setHealth(0);
        player.getLocation().setY(64);
        player.sendMessage(ChatColor.BLUE + "System" + ChatColor.GRAY + ":: " + ChatColor.WHITE + "광활한 우주 그 곳에는 산소가 없습니다.");
        player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
      } else if(player.getLocation().getBlockY() > 180) {
        player.setVelocity(new Vector(0.0D, 2.0D, 0.0D));
      }
    }
  }
}
