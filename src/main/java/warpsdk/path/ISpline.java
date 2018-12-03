package warpsdk.path;

public interface ISpline {

    /**
     * @param t A value [0, 1] such that 0 is start point and 1 is endpoint
     * @return The position on the spline
     */
    public Vector2 getPosition(double t);
}