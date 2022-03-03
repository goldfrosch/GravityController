package com.goldfrosch.shedulers;

import com.goldfrosch.GravityController;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.util.Vector;

import java.util.Iterator;
import java.util.List;

public class ItemGravity implements Runnable{
  GravityController plugin;

  public ItemGravity(GravityController plugin) {
    this.plugin = plugin;
  }

  public void run() {
    Iterator<World> itemList = Bukkit.getWorlds().iterator();

    event:
      while(itemList.hasNext()) {
        World world = itemList.next();
        List<Entity> entities = world.getEntities();
        Iterator<Entity> droppedItemList = entities.iterator();

        while(true) {
          while(true) {
            Entity item;
            do {
              if (!droppedItemList.hasNext()) {
                continue event;
              }
              item = droppedItemList.next();
            } while(!(item instanceof Item));
            if (plugin.getStatus()) {
              if(item.getLocation().getBlockY() < 256) {
                item.setGravity(false);
                item.setVelocity(new Vector(0.0D, 0.3D, 0.0D));
                item.setGravity(true);
              } else {
               item.remove();
              }
            }
          }
        }
      }
  }
}
