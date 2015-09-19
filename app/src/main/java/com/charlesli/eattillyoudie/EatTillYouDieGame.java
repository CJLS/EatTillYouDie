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
}
