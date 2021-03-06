package com.charlesli.eattillyoudie;

import com.charlesli.androidgames.framework.Game;
import com.charlesli.androidgames.framework.Graphics;
import com.charlesli.androidgames.framework.Screen;

/**
 * Created by Li on 2015/9/18.
 */
public class LoadingScreen extends Screen {

    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.asparagus = g.newPixmap("asparagus.png", Graphics.PixmapFormat.ARGB4444);
        Assets.backButton = g.newPixmap("backButton.png", Graphics.PixmapFormat.ARGB4444);
        Assets.background = g.newPixmap("backgroundSky.png", Graphics.PixmapFormat.RGB565);
        Assets.banana = g.newPixmap("banana.png", Graphics.PixmapFormat.ARGB4444);
        Assets.burger = g.newPixmap("burger.png", Graphics.PixmapFormat.ARGB4444);
        Assets.cancelButton = g.newPixmap("cancelButton.png", Graphics.PixmapFormat.ARGB4444);
        Assets.carrot = g.newPixmap("carrot.png", Graphics.PixmapFormat.ARGB4444);
        Assets.character = g.newPixmap("character.png", Graphics.PixmapFormat.ARGB4444);
        Assets.chocolate = g.newPixmap("chocolate.png", Graphics.PixmapFormat.ARGB4444);
        Assets.conveyorBelt = g.newPixmap("conveyorBelt.png", Graphics.PixmapFormat.ARGB4444);
        Assets.fish = g.newPixmap("fish.png", Graphics.PixmapFormat.ARGB4444);
        Assets.foodBanner = g.newPixmap("foodBanner.png", Graphics.PixmapFormat.ARGB4444);
        Assets.helpButton = g.newPixmap("helpButton.png", Graphics.PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameOver.png", Graphics.PixmapFormat.ARGB4444);
        Assets.gameScreen = g.newPixmap("basicScreen.png", Graphics.PixmapFormat.ARGB4444);
        Assets.heart = g.newPixmap("heart.png", Graphics.PixmapFormat.ARGB4444);
        Assets.helpScreen1 = g.newPixmap("helpScreen1.png", Graphics.PixmapFormat.ARGB4444);
        Assets.helpScreen2 = g.newPixmap("helpScreen2.png", Graphics.PixmapFormat.ARGB4444);
        Assets.helpScreen3 = g.newPixmap("helpScreen3.png", Graphics.PixmapFormat.ARGB4444);
        Assets.highScoresButton = g.newPixmap("highscoresButton.png", Graphics.PixmapFormat.ARGB4444);
        Assets.highScoresScreen = g.newPixmap("highScoresScreen.png", Graphics.PixmapFormat.ARGB4444);
        Assets.highScoresScreenFood = g.newPixmap("highScoresScreenFood.png", Graphics.PixmapFormat.ARGB4444);
        Assets.iceCream = g.newPixmap("iceCream.png", Graphics.PixmapFormat.ARGB4444);
        Assets.logo = g.newPixmap("logo.png", Graphics.PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", Graphics.PixmapFormat.ARGB4444);
        Assets.numbersBig = g.newPixmap("numbersBig.png", Graphics.PixmapFormat.ARGB4444);
        Assets.nextButton = g.newPixmap("nextButton.png", Graphics.PixmapFormat.ARGB4444);
        Assets.pizza = g.newPixmap("pizza.png", Graphics.PixmapFormat.ARGB4444);
        Assets.pauseButton = g.newPixmap("pauseButton.png", Graphics.PixmapFormat.ARGB4444);
        Assets.playButton = g.newPixmap("playButton.png", Graphics.PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("readyScreen.png", Graphics.PixmapFormat.ARGB4444);
        Assets.resumeQuit = g.newPixmap("resumeQuit.png", Graphics.PixmapFormat.ARGB4444);
        Assets.steak = g.newPixmap("steak.png", Graphics.PixmapFormat.ARGB4444);
        Assets.sushi = g.newPixmap("sushi.png", Graphics.PixmapFormat.ARGB4444);
        Assets.timeBar = g.newPixmap("timeBar.png", Graphics.PixmapFormat.ARGB4444);
        Assets.timeBarFull = g.newPixmap("timeBarFull.png", Graphics.PixmapFormat.ARGB4444);
        Assets.watermelon = g.newPixmap("watermelon.png", Graphics.PixmapFormat.ARGB4444);

        Assets.bgMusic = game.getAudio().newMusic("candyland.ogg");

        Assets.click = game.getAudio().newSound("click.ogg");

        if (!Assets.bgMusic.isPlaying() && Settings.soundEnabled) {
            Assets.bgMusic.play();
        }
        if (!Assets.bgMusic.isLooping()) {
            Assets.bgMusic.setLooping(true);
        }

        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {

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
