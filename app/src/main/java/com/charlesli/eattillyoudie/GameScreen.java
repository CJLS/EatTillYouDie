package com.charlesli.eattillyoudie;

import com.charlesli.androidgames.framework.Game;
import com.charlesli.androidgames.framework.Graphics;
import com.charlesli.androidgames.framework.Screen;

/**
 * Created by Li on 2015/9/19.
 */
public class GameScreen extends Screen {

    private int score = 0;
    private int lives = 3;
    private int time = 100;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.gameScreen, 0, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
