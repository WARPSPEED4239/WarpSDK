package warpsdk.path;

public class Vector2 {
    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 scaleCopy(double magnitude) {
        return new Vector2(magnitude * x, magnitude * y);
    }

    public Vector2 addCopy(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    public void scale(double magnitude) {
        this.x *= magnitude;
        this.y *= magnitude;
    }

    public void add(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
    }

    public double magnitude() {
        return Math.sqrt(x*x + y*y);
    }

    public void normalize() {
        double mag = magnitude();
        if (mag != 0.0) {
            x /= mag;
            y /= mag;
        }
    }

    public String toString() {
        return "x = " + x + ", y = " + y;
    }
}
