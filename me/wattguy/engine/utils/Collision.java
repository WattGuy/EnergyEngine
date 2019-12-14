package me.wattguy.engine.utils;

import me.wattguy.engine.interfaces.Collider;
import me.wattguy.engine.objects.Circle;
import me.wattguy.engine.objects.Rectangle;

public class Collision {

    public static boolean isColliding(Collider col1, Collider col2){

        if (col1 instanceof Rectangle && col2 instanceof Rectangle){
            Rectangle r1 = (Rectangle) col1;
            Rectangle r2 = (Rectangle) col2;

            if (
                    r1.topRight().getY() < r2.bottomLeft().getY()
                            ||
                            r1.bottomLeft().getY() > r2.topRight().getY()
                            ||
                            r1.topRight().getX() < r2.bottomLeft().getX()
                            ||
                            r1.bottomLeft().getX() > r2.topRight().getX()
                    ) return false;

            return true;
        }else if (col1 instanceof Circle && col2 instanceof Circle){
            Circle c1 = (Circle) col1;
            Circle c2 = (Circle) col2;

            double xDif = c1.getPosition().getX() - c2.getPosition().getX();
            double yDif = c1.getPosition().getY() - c2.getPosition().getY();
            double distanceSquared = xDif * xDif + yDif * yDif;

            return distanceSquared < (c1.getRadius() + c2.getRadius()) * (c1.getRadius() + c2.getRadius());
        }else if ((col1 instanceof Rectangle && col2 instanceof Circle) || (col1 instanceof Circle && col2 instanceof Rectangle)){
            Rectangle r = (col1 instanceof Rectangle) ? (Rectangle) col1 : (Rectangle) col2;
            Circle c = (col1 instanceof Circle) ? (Circle) col1 : (Circle) col2;

            float x = c.getPosition().getX();
            float y = c.getPosition().getY();

            if (c.getPosition().getX() < r.topLeft().getX())         x = r.topLeft().getX();      // LEFT EDGE
            else if (c.getPosition().getX() > r.topLeft().getX() + r.getWidth()) x = r.topLeft().getX() + r.getWidth();   // RIGHT EDGE

            if (c.getPosition().getY() > r.topLeft().getY())         y = r.topLeft().getY();      // TOP EDGE
            else if (c.getPosition().getY() < r.topLeft().getY() - r.getHeight()) y = r.topLeft().getY() - r.getHeight();   // BOTTOM EDGE

            float distX = c.getPosition().getX() - x;
            float distY = c.getPosition().getY() - y;
            double distance = Math.sqrt((distX * distX) + (distY * distY));

            // IF DISTANCE LESS THAN A RADIUS THEN IT IS A COLLISION!
            return distance <= c.getRadius();
        }

        return false;
    }

}
