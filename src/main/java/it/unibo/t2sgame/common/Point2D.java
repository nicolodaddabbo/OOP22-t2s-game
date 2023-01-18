package it.unibo.t2sgame.common;

public class Point2D {
    
    private final double x;
    private final double y;

    /**
     * 
     * @param x coordinate of the point in x axis
     * @param y coordinate of the point in y axis
     */
    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return coordinate of the point in x axis
     */
    public double getX() {
        return this.x;
    }

    /**
     * 
     * @return coordinate of the point in y axis
     */
    public double getY() {
        return this.y;
    }

    /**
     * This method sums the current point with the given vector and returns the new position.
     * @param vector the vector to sum
     * @return the resulting point
     */
    public Point2D sum(final Vector2D vector) {
        return new Point2D(this.x+vector.getX(), this.y+vector.getY());
    }

    /**
     * This method subtracts the current point with the given point and returns the result.
     * @param point the point to subtract
     * @return the resulting vector
     */
    public Vector2D sub(final Point2D point) {
        return new Vector2D(this.x-point.x, this.y-point.y);
    }

    /**
     * This method returns the distance between the current point and the given point.
     * @param point the point to sum
     * @return the resulting distance
     */
    public double distance(final Point2D point) {
        return Math.sqrt(Math.pow(point.x-this.x, 2)+Math.pow(point.y-this.y, 2));
    }

}
