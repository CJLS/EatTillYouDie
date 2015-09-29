package com.charlesli.eattillyoudie;

import com.charlesli.androidgames.framework.Game;
import com.charlesli.androidgames.framework.Graphics;
import com.charlesli.androidgames.framework.Input;
import com.charlesli.androidgames.framework.Input.TouchEvent;
import com.charlesli.androidgames.framework.Screen;
import com.charlesli.eattillyoudie.fooditems.Asparagus;
import com.charlesli.eattillyoudie.fooditems.Banana;
import com.charlesli.eattillyoudie.fooditems.Burger;
import com.charlesli.eattillyoudie.fooditems.Carrot;
import com.charlesli.eattillyoudie.fooditems.Chocolate;
import com.charlesli.eattillyoudie.fooditems.Fish;
import com.charlesli.eattillyoudie.fooditems.Food;
import com.charlesli.eattillyoudie.fooditems.IceCream;
import com.charlesli.eattillyoudie.fooditems.Pizza;
import com.charlesli.eattillyoudie.fooditems.Steak;
import com.charlesli.eattillyoudie.fooditems.Sushi;
import com.charlesli.eattillyoudie.fooditems.Watermelon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * Created by Li on 2015/9/19.
 */
public class GameScreen extends Screen {

    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;

    private int score = 0;
    private int numberOfLives = 3;
    private float starvingTimeMax = 40;
    private float starvingTimeCurrent = 40;
    private float foodTime = 0;
    private float foodTimeCutOff = 0;

    private Random mRandom = new Random();
    private TreeSet<Integer> foodSet = new TreeSet<Integer>();

    ArrayList<Food> foodList = new ArrayList<Food>();
    ArrayList<Food> toEatList = new ArrayList<Food>();

    private final int ASPARAGUS = 0;
    private final int BANANA = 1;
    private final int BURGER = 2;
    private final int CARROT = 3;
    private final int CHOCOLATE = 4;
    private final int FISH = 5;
    private final int ICECREAM = 6;
    private final int PIZZA = 7;
    private final int STEAK = 8;
    private final int SUSHI = 9;
    private final int WATERMELON = 10;


    public GameScreen(Game game) {
        super(game);
        while (foodSet.size() < 4) {
            int food = mRandom.nextInt(11);
            foodSet.add(food);
        }
        int foodCount = 0;
        for (Integer aFoodSet : foodSet) {
            if (foodCount < foodSet.size()) {
                addFoodSpecificToList(toEatList, aFoodSet, 440 + 150 * foodCount, 190, 0);
            }
            foodCount++;
        }
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if(touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        Graphics g = game.getGraphics();
        int len = touchEvents.size();
        starvingTimeCurrent -= deltaTime;
        if (starvingTimeCurrent <= 0) {
            Settings.addScore(score);
            Settings.save(game.getFileIO());
            state = GameState.GameOver;
        }

        if (score >= 4000) {
            starvingTimeMax = 15;
        }
        else if (score >= 2500) {
            starvingTimeMax = 18;
        }
        else if (score >= 2000) {
            starvingTimeMax = 20;
        }
        else if (score >= 1500) {
            starvingTimeMax = 23;
        }
        else if (score >= 1000) {
            starvingTimeMax = 25;
        }
        else if (score >= 800) {
            starvingTimeMax = 28;
        }
        else if (score >= 600) {
            starvingTimeMax = 30;
        }
        else if (score >= 400) {
            starvingTimeMax = 33;
        }
        else if (score >= 300) {
            starvingTimeMax = 35;
        }
        else if (score >= 200) {
            starvingTimeMax = 38;
        }

        foodTime += deltaTime;
        if (foodTime > foodTimeCutOff) {
            addFoodRandomToList(foodList, 11, 1200, 540, 200);
            foodTime = 0;
            foodTimeCutOff = (float) 0.6;
        }


        // Check if Food items are clicked
        Iterator<Food> foodIterator = foodList.iterator();
        while (foodIterator.hasNext()) {
            Food foodItem = foodIterator.next();
            for(int i = 0; i < len; i++) {
                TouchEvent event = touchEvents.get(i);
                if(event.type == TouchEvent.TOUCH_UP) {
                    if(inBounds(event, foodItem.x, foodItem.y, 75, 75)) {
                        if(Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        for (int j = 0; j < toEatList.size(); j++) {
                            if (foodItem.getClass() == toEatList.get(j).getClass()) {
                                if (starvingTimeCurrent < starvingTimeMax - 2) {
                                    starvingTimeCurrent += 2;
                                }
                                else {
                                    starvingTimeCurrent = starvingTimeMax;
                                }
                                score += 10;
                                int xPos = (int) toEatList.get(j).x;
                                int yPos = (int) toEatList.get(j).y;
                                toEatList.remove(j);
                                addFoodRandomToList(toEatList, 11, xPos, yPos, 0);
                                break;
                            }
                            else if (j == toEatList.size() - 1) {
                                numberOfLives--;
                                // Check if no hearts left
                                if(numberOfLives <= 0) {
                                    state = GameState.GameOver;
                                    Settings.addScore(score);
                                    Settings.save(game.getFileIO());
                                }
                            }
                        }
                        foodIterator.remove();
                    }
                }
            }
        }

        // Update Food items location
        for (int i = 0; i < foodList.size(); i++) {
            foodList.get(i).update(deltaTime);
        }


        // Pressed on Pause Buton
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, 1170, 730, 55, 55)) {
                    if(Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    state = GameState.Paused;
                }
            }
        }

        // check Food item out of play
        Iterator<Food> foodIterator2 = foodList.iterator();
        while (foodIterator2.hasNext()) {
            Food foodItem = foodIterator2.next();

            if (foodItem.x < 0) {
                foodIterator2.remove();
            }
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 465 && event.x <= 810) {
                    if(Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    if(event.y > 290 && event.y <= 360) {
                        state = GameState.Running;
                        return;
                    }
                    if(event.y > 390 && event.y < 485) {
                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                game.setScreen(new MainMenuScreen(game));
                return;
            }
        }
    }

    private void addFoodRandomToList(ArrayList<Food> foodArrayList, int randomRange, int xPos, int yPos, int speed) {
        int food = mRandom.nextInt(randomRange);
        if (food == ASPARAGUS) {
            foodArrayList.add(new Asparagus(xPos, yPos, speed));
        }
        else if (food == BANANA) {
            foodArrayList.add(new Banana(xPos, yPos, speed));
        }
        else if (food == BURGER) {
            foodArrayList.add(new Burger(xPos, yPos, speed));
        }
        else if (food == CARROT) {
            foodArrayList.add(new Carrot(xPos, yPos, speed));
        }
        else if (food == CHOCOLATE) {
            foodArrayList.add(new Chocolate(xPos, yPos, speed));
        }
        else if (food == FISH) {
            foodArrayList.add(new Fish(xPos, yPos, speed));
        }
        else if (food == ICECREAM) {
            foodArrayList.add(new IceCream(xPos, yPos, speed));
        }
        else if (food == PIZZA) {
            foodArrayList.add(new Pizza(xPos, yPos, speed));
        }
        else if (food == STEAK) {
            foodArrayList.add(new Steak(xPos, yPos, speed));
        }
        else if (food == SUSHI) {
            foodArrayList.add(new Sushi(xPos, yPos, speed));
        }
        else if (food == WATERMELON) {
            foodArrayList.add(new Watermelon(xPos, yPos, speed));
        }
    }

    private void addFoodSpecificToList(ArrayList<Food> foodArrayList, int food, int xPos, int yPos, int speed) {
        if (food == ASPARAGUS) {
            foodArrayList.add(new Asparagus(xPos, yPos, speed));
        }
        else if (food == BANANA) {
            foodArrayList.add(new Banana(xPos, yPos, speed));
        }
        else if (food == BURGER) {
            foodArrayList.add(new Burger(xPos, yPos, speed));
        }
        else if (food == CARROT) {
            foodArrayList.add(new Carrot(xPos, yPos, speed));
        }
        else if (food == CHOCOLATE) {
            foodArrayList.add(new Chocolate(xPos, yPos, speed));
        }
        else if (food == FISH) {
            foodArrayList.add(new Fish(xPos, yPos, speed));
        }
        else if (food == ICECREAM) {
            foodArrayList.add(new IceCream(xPos, yPos, speed));
        }
        else if (food == PIZZA) {
            foodArrayList.add(new Pizza(xPos, yPos, speed));
        }
        else if (food == STEAK) {
            foodArrayList.add(new Steak(xPos, yPos, speed));
        }
        else if (food == SUSHI) {
            foodArrayList.add(new Sushi(xPos, yPos, speed));
        }
        else if (food == WATERMELON) {
            foodArrayList.add(new Watermelon(xPos, yPos, speed));
        }
    }

    private boolean inBounds(TouchEvent event,
                             float x, float y,
                             int width, int height) {
        return event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1;
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.gameScreen, 0, 0);
        g.drawPixmap(Assets.timeBar, 260, 17);
        if(state == GameState.Ready) {
            drawReadyUI();
        }
        if(state == GameState.Running) {
            drawRunningUI();
            drawWorld();
            g.drawPixmap(Assets.timeBarFull, 260, 17, 0, 0, (int) (Math.min(starvingTimeCurrent / starvingTimeMax, 1) *
                    Assets.timeBarFull.getWidth()), Assets.timeBarFull.getHeight());
            drawText(g, score + "", 1150,  25);
        }
        if(state == GameState.Paused) {
            drawPausedUI();
        }
        if(state == GameState.GameOver) {
            drawGameOverUI();
        }
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.ready, 0, 0);
    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.pauseButton, 1170, 730);
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.resumeQuit, 465, 290);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.gameOver, 390, 310);
    }

    private void drawWorld() {
        Graphics g = game.getGraphics();
        drawFood(g, toEatList);
        drawFood(g, foodList);
        for(int i = 0; i < this.numberOfLives; i++) {
            int x = 50 + Assets.heart.getWidth() * i;
            int y = 20;
            g.drawPixmap(Assets.heart, x, y);
        }
    }

    private void drawFood(Graphics g, ArrayList<Food> foodArrayList) {
        for (int i = 0; i < foodArrayList.size(); i++) {
            if (foodArrayList.get(i) instanceof Asparagus) {
                g.drawPixmap(Assets.asparagus, (int) foodArrayList.get(i).x, (int) foodArrayList.get(i).y);
            }
            else if (foodArrayList.get(i) instanceof Banana) {
                g.drawPixmap(Assets.banana, (int) foodArrayList.get(i).x, (int) foodArrayList.get(i).y);
            }
            else if (foodArrayList.get(i) instanceof Burger) {
                g.drawPixmap(Assets.burger, (int) foodArrayList.get(i).x, (int) foodArrayList.get(i).y);
            }
            else if (foodArrayList.get(i) instanceof Carrot) {
                g.drawPixmap(Assets.carrot, (int) foodArrayList.get(i).x, (int) foodArrayList.get(i).y);
            }
            else if (foodArrayList.get(i) instanceof Chocolate) {
                g.drawPixmap(Assets.chocolate, (int) foodArrayList.get(i).x, (int) foodArrayList.get(i).y);
            }
            else if (foodArrayList.get(i) instanceof Fish) {
                g.drawPixmap(Assets.fish, (int) foodArrayList.get(i).x, (int) foodArrayList.get(i).y);
            }
            else if (foodArrayList.get(i) instanceof IceCream) {
                g.drawPixmap(Assets.iceCream, (int) foodArrayList.get(i).x, (int) foodArrayList.get(i).y);
            }
            else if (foodArrayList.get(i) instanceof Pizza) {
                g.drawPixmap(Assets.pizza, (int) foodArrayList.get(i).x, (int) foodArrayList.get(i).y);
            }
            else if (foodArrayList.get(i) instanceof Steak) {
                g.drawPixmap(Assets.steak, (int) foodArrayList.get(i).x, (int) foodArrayList.get(i).y);
            }
            else if (foodArrayList.get(i) instanceof Sushi) {
                g.drawPixmap(Assets.sushi, (int) foodArrayList.get(i).x, (int) foodArrayList.get(i).y);
            }
            else if (foodArrayList.get(i) instanceof Watermelon) {
                g.drawPixmap(Assets.watermelon, (int) foodArrayList.get(i).x, (int) foodArrayList.get(i).y);
            }
        }
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 30);
            x += srcWidth;
        }
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
