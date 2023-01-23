package it.unibo.t2sgame.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2DTest {
    
    @Test 
    void testVectorSum() {
        //Test with x and y coordinates
        var vector = new Vector2D(1,1);
        assertEquals(new Vector2D(vector.getX()+1.3, vector.getY()-0.3), vector.sum(1.3, -0.3));
        //Test with vectors
        assertEquals(new Vector2D(vector.getX()+1.3, vector.getY()-0.3), vector.sum(new Vector2D(1.3, -0.3)));
    }

    @Test 
    void testVectorSub() {
        //Test with x and y coordinates
        var vector = new Vector2D(1,1);
        assertEquals(new Vector2D(vector.getX()-1.3, vector.getY()+0.3), vector.sub(1.3, -0.3));
        //Test with vectors
        assertEquals(new Vector2D(vector.getX()-1.3, vector.getY()+0.3), vector.sub(new Vector2D(1.3, -0.3)));
    }

    @Test 
    void testVectorMul() {
        var vector = new Vector2D(1,1);
        assertEquals(new Vector2D(vector.getX()*2, vector.getY()*2), vector.mul(2));
        assertEquals(new Vector2D(vector.getX()*-1, vector.getY()*-1), vector.mul(-1));
        assertEquals(new Vector2D(vector.getX()*0, vector.getY()*0), vector.mul(0));
    }

}
