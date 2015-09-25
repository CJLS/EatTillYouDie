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
                foodList.add(new Asparagus(1280, 550, 50));
            }
            else if (food == BANANA) {
                foodList.add(new Banana(1280, 550, 50));
            }
            else if (food == BURGER) {
                foodList.add(new Burger(1280, 550, 50));
            }
            else if (food == CARROT) {
                foodList.add(new Carrot(1280, 550, 50));
            }
            else if (food == CHOCOLATE) {
                foodList.add(new Chocolate(1280, 550, 50));
            }
            else if (food == FISH) {
                foodList.add(new Fish(1280, 550, 50));
            }
            else if (food == ICECREAM) {
                foodList.add(new IceCream(1280, 550, 50));
            }
            else if (food == PIZZA) {
                foodList.add(new Pizza(1280, 550, 50));
            }
            else if (food == STEAK) {
                foodList.add(new Steak(1280, 550, 50));
            }
            else if (food == SUSHI) {
                foodList.add(new Sushi(1280, 550, 50));
            }
            else if (food == WATERMELON) {
                foodList.add(new Watermelon(1280, 550, 50));
            }

        }
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
