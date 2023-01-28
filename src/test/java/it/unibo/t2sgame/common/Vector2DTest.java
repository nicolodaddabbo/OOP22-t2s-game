package it.unibo.t2sgame.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2DTest {
    
    @Test 
    void testSum() {
        Vector2D vector = new Vector2D(1,1);
        double xToSum = 1.3;
        double yToSum = -0.3;
        assertEquals(new Vector2D(vector.getX()+xToSum, vector.getY()+yToSum), vector.sum(xToSum, yToSum));
    }

    @Test 
    void testVectorSum() {
        Vector2D vector = new Vector2D(1,1);
        Vector2D vectorToSum = new Vector2D(1.3,-0.3);
        assertEquals(new Vector2D(vector.getX()+vectorToSum.getX(), vector.getY()+vectorToSum.getY()), vector.sum(vectorToSum));
    }

    @Test 
    void testSub() {
        Vector2D vector = new Vector2D(1,1);
        double xToSub = 1.3;
        double yToSub = -0.3;
        assertEquals(new Vector2D(vector.getX()-xToSub, vector.getY()-yToSub), vector.sub(xToSub, yToSub));
    }

    @Test 
    void testVectorSub() {
        Vector2D vector = new Vector2D(1,1);
        Vector2D vectorToSub = new Vector2D(1.3,-0.3);
        assertEquals(new Vector2D(vector.getX()-vectorToSub.getX(), vector.getY()-vectorToSub.getY()), vector.sub(vectorToSub));
    }

    @Test 
    void testMul() {
        Vector2D vector = new Vector2D(1,1);
        double scalar = 0;
        assertEquals(new Vector2D(vector.getX()*scalar, vector.getY()*scalar), vector.mul(scalar));
    }

    @Test 
    void testDistance() {
        Vector2D vector = new Vector2D(1,1);
        double x = 2;
        double y = 3;
        assertEquals(Math.sqrt(Math.pow(vector.getX()-x, 2)+Math.pow(vector.getY()-y, 2)), vector.distance(x, y));
    }

    @Test 
    void testVectorDistance() {
        Vector2D firstVector = new Vector2D(1,1);
        Vector2D secondVector = new Vector2D(2, 3);
        assertEquals(Math.sqrt(Math.pow(firstVector.getX()-secondVector.getX(), 2)+Math.pow(firstVector.getY()-secondVector.getY(), 2)), firstVector.distance(secondVector));
    }

}
