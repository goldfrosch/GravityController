package com.goldfrosch;

import com.goldfrosch.commands.Commands;
import com.goldfrosch.events.GravityEvent;
import com.goldfrosch.utils.MusicUtils;

import com.outstandingboy.donationalert.platform.Twip;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

@Getter
@Setter
public class GravityController extends JavaPlugin {
  private PluginDescriptionFile pdfFile = this.getDescription();
  private String pfName = pdfFile.getName() + " v" + pdfFile.getVersion();

  private Boolean status = false;

  @Override
  public void onEnable(){
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
          player.sendTitle("", donation.getAmount() + "원 후원 감사합니다 :)",1,1,1);
          MusicUtils musicUtils = new MusicUtils(player);
          musicUtils.playRandom();
        }
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
    Bukkit.getPluginManager().registerEvents(new GravityEvent(this),this);
  }
}
