/*
This class stores the background music for the game
 */
package cpt;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
//music for main menu
    public static final AudioClip MAIN = Applet.newAudioClip(Sound.class.getResource("MainMusic.wav"));
//music for gameplay
    public static final AudioClip GAME = Applet.newAudioClip(Sound.class.getResource("Game.wav"));
//music for game over screen
    public static final AudioClip OVER = Applet.newAudioClip(Sound.class.getResource("GameOver.wav"));
}
