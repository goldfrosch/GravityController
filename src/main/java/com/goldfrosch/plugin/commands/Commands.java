package com.goldfrosch.plugin.commands;

import com.goldfrosch.plugin.GravityController;

import com.goldfrosch.plugin.utils.TimerUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Commands extends AbstractCommand{
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
      TimerUtils gravityTimer = new TimerUtils(plugin, player);

      if(label.equalsIgnoreCase("gravity")){
        if(args.length == 0){
          player.sendMessage(prefix + "어쩔 티비");
        } else {
          switch (args[0]) {
            case "start":
              player.sendMessage(prefix + "게임을 시작합니다. 둠황챠");
              gravityTimer.setGravity();
              break;
            case "stop":
              try {
                player.sendMessage(prefix + "장비를 정지합니다");
                Thread.sleep(1000);
                player.sendMessage(prefix + "어 뭐야 안되잖아?");
                Thread.sleep(500);
                player.sendMessage(prefix + "ㅋㅋ 구라임 멈춤 ㅅㄱ");
                gravityTimer.stopGravity();
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
