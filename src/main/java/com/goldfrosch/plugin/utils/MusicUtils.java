package com.goldfrosch.plugin.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MusicUtils {
    Player player;

    public MusicUtils(Player player) {
        this.player = player;
    }

    public void playRandom() {
        double[] pitchList = new double[] {
            0.5, 0.6, 0.7, 0.9,
            0.6, 0.7, 0.8, 1.0,
            0.7, 0.8, 0.9, 1.1,
            0.8, 0.9, 1.0, 1.2
        };

        for(double pitch: pitchList) {
            try {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1, (float) pitch);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
