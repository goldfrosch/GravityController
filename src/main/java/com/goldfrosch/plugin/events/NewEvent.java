package com.goldfrosch.plugin.events;

import com.goldfrosch.plugin.GravityController;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class NewEvent implements Listener {
  private final GravityController plugin;
}
