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

    public Vector2 add(Vector2 other){
        return new Vector2(x + other.x, y + other.y);
    }

    public Vector2 subtract(Vector2 other){
        return new Vector2(x - other.x, y - other.y);
    }

    @Override
    public String toString(){
        return x + ":" + y;
    }

}
