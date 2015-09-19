package com.charlesli.eattillyoudie;

import com.charlesli.androidgames.framework.Game;
import com.charlesli.androidgames.framework.Graphics;
import com.charlesli.androidgames.framework.Screen;
import com.charlesli.androidgames.framework.Input.TouchEvent;

/**
 * Created by Li on 2015/9/18.
 */
public class MainMenuScreen extends Screen {

    private int logoXpos;
    private int logoYpos;
    private int characterXpos;
    private int characterYpos;
    private int conveyorBeltXpos;
    private int conveyorBeltYpos;
    private int playButtonXpos;
    private int playButtonYpos;
    private int highscoresButtonXpos;
    private int highscoresButtonYpos;
    private int helpButtonXpos;
    private int helpButtonYpos;

    public MainMenuScreen(Game game) {
        super(game);
        Graphics g = game.getGraphics();
        logoXpos = g.getWidth() / 2 - Assets.logo.getWidth() / 2;
        logoYpos = Assets.logo.getHeight();
        characterXpos = g.getWidth() / 2 - Assets.character.getWidth() / 2;
        characterYpos = g.getHeight() * 3;
        conveyorBeltXpos = 0;
        conveyorBeltYpos = characterYpos + Assets.character.getHeight() / 2;
        playButtonXpos = g.getWidth() / 2 - Assets.playButton.getWidth() / 2;
        playButtonYpos = characterYpos + Assets.character.getHeight() + Assets.playButton.getHeight() / 2;
        highscoresButtonXpos = g.getWidth() / 2 - Assets.highScoresButton.getWidth() / 2;
        highscoresButtonYpos = playButtonYpos + Assets.playButton.getHeight() * 3 / 2;
        helpButtonXpos = g.getWidth() / 2 - Assets.helpButton.getWidth() / 2;
        helpButtonYpos = highscoresButtonYpos + Assets.highScoresButton.getHeight() * 3 / 2;
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, logoXpos, logoYpos);
        g.drawPixmap(Assets.character, characterXpos, characterYpos);
        g.drawPixmap(Assets.conveyorBelt, conveyorBeltXpos, conveyorBeltYpos);
        g.drawPixmap(Assets.playButton, playButtonXpos, playButtonYpos);
        g.drawPixmap(Assets.highScoresButton, highscoresButtonXpos, highscoresButtonYpos);
        g.drawPixmap(Assets.helpButton, helpButtonXpos, helpButtonYpos);
    }

    private boolean inBounds(TouchEvent event,
                             int x, int y,
                             int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
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
