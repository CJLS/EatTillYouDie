package com.charlesli.eattillyoudie;

import com.charlesli.androidgames.framework.Game;
import com.charlesli.androidgames.framework.Graphics;
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

/**
 * Created by Li on 2015/9/19.
 */
public class GameScreen extends Screen {

    private int score = 0;
    private int numberOfLives = 3;
    private int starvingTime = 100;
    private int foodTime = 0;
    private int foodTimeCutOff;

    private Random mRandom = new Random();

    ArrayList<Food> foodList = new ArrayList<Food>();

    ArrayList<Asparagus> asparagusList = new ArrayList<Asparagus>();
    ArrayList<Banana> bananaList = new ArrayList<Banana>();
    ArrayList<Burger> burgerList = new ArrayList<Burger>();
    ArrayList<Carrot> carrotList = new ArrayList<Carrot>();
    ArrayList<Chocolate> chocolateList = new ArrayList<Chocolate>();
    ArrayList<Fish> fishList = new ArrayList<Fish>();
    ArrayList<IceCream> iceCreamList = new ArrayList<IceCream>();
    ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
    ArrayList<Steak> steakList = new ArrayList<Steak>();
    ArrayList<Sushi> sushiList = new ArrayList<Sushi>();
    ArrayList<Watermelon> watermelonList = new ArrayList<Watermelon>();

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
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        updateRunning(touchEvents, deltaTime);
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        Graphics g = game.getGraphics();
        int len = touchEvents.size();

        foodTime += deltaTime;
        if (foodTime > foodTimeCutOff) {
            int food = mRandom.nextInt(11);
            if (food == ASPARAGUS) {
                foodList.add(new Asparagus(1200, 550, 50));
            }
            else if (food == BANANA) {
                foodList.add(new Banana(1200, 550, 50));
            }
            else if (food == BURGER) {
                foodList.add(new Burger(1200, 550, 50));
            }
            else if (food == CARROT) {
                foodList.add(new Carrot(1200, 550, 50));
            }
            else if (food == CHOCOLATE) {
                foodList.add(new Chocolate(1200, 550, 50));
            }
            else if (food == FISH) {
                foodList.add(new Fish(1200, 550, 50));
            }
            else if (food == ICECREAM) {
                foodList.add(new IceCream(1200, 550, 50));
            }
            else if (food == PIZZA) {
                foodList.add(new Pizza(1200, 550, 50));
            }
            else if (food == STEAK) {
                foodList.add(new Steak(1200, 550, 50));
            }
            else if (food == SUSHI) {
                foodList.add(new Sushi(1200, 550, 50));
            }
            else if (food == WATERMELON) {
                foodList.add(new Watermelon(1200, 550, 50));
            }
            foodTime = 0;
            foodTimeCutOff = 3;
        }

        // Update Food items location
        for (int i = 0; i < foodList.size(); i++) {
            foodList.get(i).update(deltaTime);
        }

        // Check if Food items are clicked
        Iterator<Food> foodIterator = foodList.iterator();
        while (foodIterator.hasNext()) {
            Food foodItem = foodIterator.next();
            for(int i = 0; i < len; i++) {
                TouchEvent event = touchEvents.get(i);
                if(event.type == TouchEvent.TOUCH_UP) {
                    // FIX THIS
                    if(inBounds(event, 50, 50, 50, 50)) {
                        foodIterator.remove();
                        score += 10;
                    }
                }
            }
        }

        // Temporary: Go back to main menu
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, 0, 0, 50, 50)) {
                    game.setScreen(new MainMenuScreen(game));
                }
            }
        }

        // check Food item out of play
        Iterator<Food> foodIterator2 = foodList.iterator();
        while (foodIterator2.hasNext()) {
            Food foodItem = foodIterator2.next();

            if (foodItem.x < 0) {
                foodIterator2.remove();
                numberOfLives--;
                if(numberOfLives == 0) {
                    Settings.addScore(score);
                    Settings.save(game.getFileIO());
                }
            }
        }
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
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.gameScreen, 0, 0);
        drawWorld();

        drawText(g, score + "", 1150,  25);
    }

    private void drawWorld() {
        Graphics g = game.getGraphics();
        for (int i = 0; i < foodList.size(); i++) {
            g.drawPixmap(Assets.watermelon, (int) foodList.get(i).x, (int) foodList.get(i).y);
        }
        for(int i = 0; i < this.numberOfLives; i++) {
            int x = 50 + Assets.heart.getWidth() * i;
            int y = 20;
            g.drawPixmap(Assets.heart, x, y);
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
