package com.goldfrosch.commands;

import com.goldfrosch.GravityController;
import com.goldfrosch.shedulers.PlayerGravity;
import com.goldfrosch.utils.GravityUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static org.bukkit.Bukkit.getServer;

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
              if(!plugin.getStatus()) {
                try {
                  player.sendMessage(prefix + ChatColor.WHITE + "10초 후 게임을 시작합니다.");
                  Thread.sleep(5000);
                  player.sendMessage(prefix + ChatColor.WHITE + "5초 후 게임을 시작합니다.");
                  Thread.sleep(1000);
                  player.sendMessage(prefix + ChatColor.WHITE + "4초 후 게임을 시작합니다.");
                  Thread.sleep(1000);
                  for(int i = 3; i >= 1; i--) {
                    player.sendMessage(prefix + ChatColor.WHITE + i + "초 후 게임을 시작합니다.");
                    Thread.sleep(1000);
                  }
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }

                player.sendMessage(prefix + "게임을 시작합니다. 둠황챠");
                plugin.setStatus(true);

                for(Player players: getServer().getOnlinePlayers()) {
                  new GravityUtils(plugin, players).setGravity();
                  Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new PlayerGravity(plugin, players), 0L, 1L);
                };
              } else {
                player.sendMessage(prefix + "게임이 현재 진행 중 입니다.");
              }
              break;
            case "stop":
              if(!plugin.getStatus()) {
                try {
                  player.sendMessage(prefix + "장비를 정지합니다");
                  Thread.sleep(1000);
                  player.sendMessage(prefix + "어 뭐야 안되잖아?");
                  Thread.sleep(500);
                  player.sendMessage(prefix + "ㅋㅋ 구라임 멈춤 ㅅㄱ");
                  plugin.setStatus(false);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              } else {
                player.sendMessage(prefix + "게임이 현재 진행 중 입니다.");
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
