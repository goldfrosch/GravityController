package com.goldfrosch.plugin;

import com.goldfrosch.plugin.commands.Commands;
import com.goldfrosch.plugin.events.GravityEvent;

import com.goldfrosch.plugin.utils.MusicUtils;
import com.outstandingboy.donationalert.platform.Twip;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.IOException;

@Getter
@Setter
public class GravityController extends JavaPlugin implements Listener {
  private PluginDescriptionFile pdfFile = this.getDescription();
  private String pfName = pdfFile.getName() + " v" + pdfFile.getVersion();

  private Boolean status = false;

  @Override
  public void onEnable(){
    Bukkit.getPluginManager().registerEvents(new GravityEvent(this),this);

    //config 파일 있는지 파악 후 생성
    if (!getDataFolder().exists()) {
      getConfig().options().copyDefaults(true);
    }
    saveConfig();

    //투네이션 연동 작업
    try {
      Twip twip = new Twip("pDRed1bz29");
      twip.subscribeDonation((donation -> {
        for(Player player: getServer().getOnlinePlayers()) {
          MusicUtils musicUtils = new MusicUtils(player);
          musicUtils.playRandom();
        }
        Bukkit.broadcastMessage(donation.getAmount() + "원 후원 감사합니다 :)");
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
    new GravityEvent(this);
  }
}
