package warpsdk.path;

public class CubicSpline implements ISpline {
    private Vector2 mStartPosition;
    private Vector2 mStartVelocity;
    private Vector2 mEndPosition;
    private Vector2 mEndVelocity;

    public CubicSpline(Vector2 startPosition, Vector2 startVelocity, Vector2 endPosition, Vector2 endVelocity) {
        mStartPosition = startPosition;
        mStartVelocity = startVelocity;
        mEndPosition = endPosition;
        mEndVelocity = endVelocity;
    }

    @Override
    public Vector2 getPosition(double t) {
        double h11 = 1 - (3*t*t) + (2*t*t*t);
        double h12 = t - (2*t*t) + (t*t*t);
        double h21 = (3*t*t) - (2*t*t*t);
        double h22 = -(t*t) + (t*t*t); 

        Vector2 result = new Vector2(0.0, 0.0);
        result.add(mStartPosition.scaleCopy(h11));
        result.add(mStartVelocity.scaleCopy(h12));
        result.add(mEndPosition.scaleCopy(h21));
        result.add(mEndVelocity.scaleCopy(h22));
        return result;
    }
}