package com.charlesli.eattillyoudie.fooditems;

/**
 * Created by Li on 2015/9/25.
 */
public class Food {
    public float x, y;
    public float speed;

    public Food(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void update(float timeDelta) {
        this.x -= speed * timeDelta;
    }
}
