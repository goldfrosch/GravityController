package com.goldfrosch.plugin.commands;

import com.goldfrosch.plugin.GravityController;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

@Getter
public abstract class AbstractCommand implements TabExecutor {
  protected GravityController plugin;
  private String Command;

  public AbstractCommand(GravityController plugin, String Command) {
    this.plugin = plugin;
    this.Command = Command;
  }

  public abstract List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args);
  public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
