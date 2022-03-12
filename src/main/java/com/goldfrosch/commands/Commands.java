package com.goldfrosch.commands;

import com.goldfrosch.GravityController;
import com.goldfrosch.shedulers.PlayerGravity;
import com.goldfrosch.utils.GravityUtils;
import com.goldfrosch.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Commands extends AbstractCommand {
  public Commands(GravityController plugin, String Command) {
    super(plugin,Command);
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
    return null;
  }

  @Override
  public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
    String prefix = plugin.getConfig().getString("message.prefix").replace("&", "§");

    if (sender instanceof Player) {
      Player player = (Player) sender;
      if(label.equalsIgnoreCase("gc")){
        if(args.length == 0){
          player.sendMessage(prefix + "어쩔 티비");
        } else {
          switch (args[0]) {
            case "start":
              GravityUtils gravity = new GravityUtils(plugin, player);

              try {
                for(int i = 3; i >= 1; i--) {
                  player.sendMessage(prefix + i + "초 후 게임을 시작합니다");
                  Thread.sleep(1000);
                }

                player.sendMessage(prefix + "게임을 시작합니다. 둠황챠");
                plugin.setStatus(true);

                gravity.setGravity();
                Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new PlayerGravity(plugin, player), 0L, 1L);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              break;
            case "stop":
              try {
                player.sendMessage(prefix + "장비를 정지합니다");
                Thread.sleep(1000);
                player.sendMessage(prefix + "어 뭐야 안되잖아?");
                Thread.sleep(500);
                player.sendMessage(prefix + "ㅋㅋ 구라임 멈춤 ㅅㄱ");

                Bukkit.getScheduler().cancelTasks(plugin);
                plugin.setStatus(false);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              break;
            default:
              player.sendMessage("없는 명령어 입니다.");
              break;
          }
        }
      }
    }
    return false;
  }
}
