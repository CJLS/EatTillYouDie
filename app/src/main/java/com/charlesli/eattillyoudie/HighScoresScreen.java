package com.charlesli.eattillyoudie;

import com.charlesli.androidgames.framework.Game;
import com.charlesli.androidgames.framework.Graphics;
import com.charlesli.androidgames.framework.Input;
import com.charlesli.androidgames.framework.Screen;

import java.util.List;

/**
 * Created by Li on 2015/9/19.
 */
public class HighScoresScreen extends Screen {

    String lines[] = new String[5];

    public HighScoresScreen(Game game) {
        super(game);

        for (int i = 0; i < 5; i++) {
            lines[i] = "" + (i + 1) + ". " + Settings.highscores[i];
        }
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (inBounds(event, 1150, 700, Assets.cancelButton.getWidth(),
                        Assets.cancelButton.getHeight())) {
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
        g.drawPixmap(Assets.highScoresScreenFood, 0, 0);

        int y = 190;
        for (int i = 0; i < 5; i++) {
            drawText(g, lines[i], 540, y);
            y += 90;
        }

        g.drawPixmap(Assets.cancelButton, 1150, 700);
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

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 40;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 400;
                srcWidth = 20;
            } else {
                srcX = (character - '0') * 40;
                srcWidth = 40;
            }

            g.drawPixmap(Assets.numbersBig, x, y, srcX, 0, srcWidth, 60);
            x += srcWidth;
        }
    }
}
