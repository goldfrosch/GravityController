package com.goldfrosch.plugin.utils;

import lombok.RequiredArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class MusicUtils {
    private final Player player;

    public void playRandom() {
        double[] pitchList = new double[] {
            0.5, 0.6, 0.7, 0.9,
            0.6, 0.7, 0.8, 1.0,
            0.7, 0.8, 0.9, 1.1,
            0.8, 0.9, 1.0, 1.2
        };

        for(double pitch: pitchList) {
            try {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 10, (float) pitch);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
