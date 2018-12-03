package warpsdk.path;

public class Waypoint {
    private Vector2 mPosition;
    private Vector2 mVelocity;
    private Vector2 mAcceleration;

    public Waypoint(Vector2 position, Vector2 velocity, Vector2 acceleration) {
        mPosition = position;
        mVelocity = velocity;
        mAcceleration = acceleration;
    }

    public Waypoint(Vector2 position, Vector2 velocity) {
        this(position, velocity, new Vector2(0.0, 0.0));
    }

    public Waypoint(Vector2 position) {
        this(position, new Vector2(0.0, 0.0));
    }

    public Vector2 getPosition() {
        return mPosition;
    }

    public Vector2 getVelocity() {
        return mVelocity;
    }

    public Vector2 getAcceleration() {
        return mAcceleration;
    }
}