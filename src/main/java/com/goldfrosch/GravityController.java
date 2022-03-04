package com.goldfrosch;

import com.goldfrosch.commands.Commands;
import com.goldfrosch.events.GravityEvent;


import com.goldfrosch.shedulers.ItemGravity;
import com.goldfrosch.utils.TwipUtils;
import lombok.Getter;
import lombok.Setter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

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
    new TwipUtils(this).donateAction();

    //Event Register
    registerEvent();
    floatItems();

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

  public void floatItems() {
    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ItemGravity(this), 0L, 1L);
  }
}
