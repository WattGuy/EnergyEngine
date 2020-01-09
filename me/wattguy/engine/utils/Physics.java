package me.wattguy.engine.utils;

import me.wattguy.engine.objects.Rigidbody;

public class Physics {

    public static void gravity(Rigidbody rb){

        rb.setVelocity(rb.getVelocity().add(new Vector2(0f, -(rb.getMass() * 9.807f))));

    }

    public static void collision(Rigidbody rb1, Rigidbody rb2){

        // TODO IMPULSE

    }

}
