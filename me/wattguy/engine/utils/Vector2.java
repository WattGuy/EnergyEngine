package me.wattguy.engine.utils;

public class Vector2 {

    private float x;
    private float y;

    public Vector2(){

        this.x = 0f;
        this.y = 0f;

    }

    public Vector2(float x, float y){

        this.x = x;
        this.y = y;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString(){
        return x + ":" + y;
    }

}
