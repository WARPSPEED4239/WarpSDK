package warpsdk.path;

public class QuinticSpline implements ISpline {
    private Vector2 mStartPosition;
    private Vector2 mStartVelocity;
    private Vector2 mStartAcceleration;
    private Vector2 mEndPosition;
    private Vector2 mEndVelocity;
    private Vector2 mEndAcceleration;

    public QuinticSpline(Vector2 startPosition, Vector2 startVelocity, Vector2 startAcceleration, Vector2 endPosition, Vector2 endVelocity, Vector2 endAcceleration) {
        mStartPosition = startPosition;
        mStartVelocity = startVelocity;
        mStartAcceleration = startAcceleration;
        mEndPosition = endPosition;
        mEndVelocity = endVelocity;
        mEndAcceleration = endAcceleration;
    }

    public QuinticSpline(Vector2 startPosition, Vector2 startVelocity, Vector2 endPosition, Vector2 endVelocity) {
        mStartPosition = startPosition;
        mStartVelocity = startVelocity;
        mStartAcceleration = new Vector2(0.0, 0.0);
        mEndPosition = endPosition;
        mEndVelocity = endVelocity;
        mEndAcceleration = new Vector2(0.0, 0.0);
    }

    @Override
    public Vector2 getPosition(double t) {
        double h0 = 1 - (10*t*t*t) + (15*t*t*t*t) - (6*t*t*t*t*t);
        double h1 = t - (6*t*t*t) + (8*t*t*t*t) - (3*t*t*t*t*t);
        double h2 = (0.5*t*t) - (1.5*t*t*t) + (1.5*t*t*t*t) - (0.5*t*t*t*t*t);
        double h3 = (10*t*t*t) - (15*t*t*t*t) + (6*t*t*t*t*t);
        double h4 = (-4.0*t*t*t) + (7*t*t*t*t) - (3*t*t*t*t*t);
        double h5 = (0.5*t*t*t) - (t*t*t*t) + (0.5*t*t*t*t*t);

        Vector2 result = new Vector2(0.0, 0.0);
        result.add(mStartPosition.scaleCopy(h0));
        result.add(mStartVelocity.scaleCopy(h1));
        result.add(mStartAcceleration.scaleCopy(h2));
        result.add(mEndPosition.scaleCopy(h3));
        result.add(mEndVelocity.scaleCopy(h4));
        result.add(mEndAcceleration.scaleCopy(h5));
        return result;
    }
}