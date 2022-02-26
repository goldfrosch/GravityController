package com.goldfrosch.plugin;

import com.goldfrosch.plugin.commands.Commands;
import com.goldfrosch.plugin.events.NewEvent;

import com.outstandingboy.donationalert.platform.Twip;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;


import java.io.IOException;

@Getter
public class GravityController extends JavaPlugin implements Listener {
  PluginDescriptionFile pdfFile = this.getDescription();
  String pfName = pdfFile.getName() + " v" + pdfFile.getVersion();

  BukkitScheduler scheduler = getServer().getScheduler();

  @Override
  public void onEnable(){
    Bukkit.getPluginManager().registerEvents(new NewEvent(this),this);

    //config 파일 있는지 파악 후 생성
    if (!getDataFolder().exists()) {
      getConfig().options().copyDefaults(true);
      saveConfig();
    } else {
      saveConfig();
    }


    try {
      Twip twip = new Twip("pDRed1bz29");
      twip.subscribeDonation((donation -> {
        Bukkit.broadcastMessage(String.valueOf(donation.getAmount()));
      }));
    } catch (IOException e) {
      e.printStackTrace();
    }

    //Event Register
    registerEvent();

    //command
    Commands cmd = new Commands(this,"gravity");
    getCommand(cmd.getCommand()).setExecutor(cmd);
    getCommand(cmd.getCommand()).setTabCompleter(cmd);

    consoleLog(pfName+"이 활성화 되었습니다");
    super.onEnable();
  }

  @Override
  public void onDisable(){
    consoleLog(pfName+"이 비활성화 되었습니다");
    super.onDisable();
  }

  public void consoleLog(String msg){
    getLogger().info(msg);
  }

  public void registerEvent() {
    new NewEvent(this);
  }
}
