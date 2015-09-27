package com.charlesli.eattillyoudie;

import com.charlesli.androidgames.framework.Screen;
import com.charlesli.androidgames.framework.impl.AndroidGame;

/**
 * Created by Li on 2015/9/17.
 */
public class EatTillYouDieGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Assets.bgMusic != null && !Assets.bgMusic.isPlaying() && Settings.soundEnabled) {
            Assets.bgMusic.play();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Assets.bgMusic.stop();
    }
}
