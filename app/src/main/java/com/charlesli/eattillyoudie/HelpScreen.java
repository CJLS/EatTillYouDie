package com.charlesli.eattillyoudie;

import com.charlesli.androidgames.framework.Game;
import com.charlesli.androidgames.framework.Graphics;
import com.charlesli.androidgames.framework.Input;
import com.charlesli.androidgames.framework.Screen;

import java.util.List;

/**
 * Created by Li on 2015/9/19.
 */
public class HelpScreen extends Screen {

    private int helpScreenNumber;
    private int nextButtonXpos;
    private int nextButtonYpos;
    private int cancelButtonXpos;
    private int cancelButtonYpos;

    public HelpScreen(Game game, int pageNumber) {
        super(game);
        helpScreenNumber = pageNumber;
        nextButtonXpos = 1150;
        nextButtonYpos = 700;
        cancelButtonXpos = 1150;
        cancelButtonYpos = 700;
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (helpScreenNumber == 1 && inBounds(event, nextButtonXpos, nextButtonYpos,
                        Assets.nextButton.getWidth(), Assets.nextButton.getHeight())) {
                    if(Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    game.setScreen(new HelpScreen(game, 2));
                    return;
                }
                else if (helpScreenNumber == 2 && inBounds(event, nextButtonXpos, nextButtonYpos,
                        Assets.nextButton.getWidth(), Assets.nextButton.getHeight())) {
                    if(Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    game.setScreen(new HelpScreen(game, 3));
                    return;
                }
                else if (helpScreenNumber == 3 && inBounds(event, cancelButtonXpos, cancelButtonYpos,
                        Assets.cancelButton.getWidth(), Assets.cancelButton.getHeight())) {
                    if(Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        if (helpScreenNumber == 1) {
            g.drawPixmap(Assets.helpScreen1, 0, 0);
            g.drawPixmap(Assets.nextButton, nextButtonXpos, nextButtonYpos);
        }
        else if (helpScreenNumber == 2) {
            g.drawPixmap(Assets.helpScreen2, 0, 0);
            g.drawPixmap(Assets.nextButton, nextButtonXpos, nextButtonYpos);
        }
        else if (helpScreenNumber == 3) {
            g.drawPixmap(Assets.helpScreen3, 0, 0);
            g.drawPixmap(Assets.cancelButton, cancelButtonXpos, cancelButtonYpos);
        }
    }

    private boolean inBounds(Input.TouchEvent event,
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
