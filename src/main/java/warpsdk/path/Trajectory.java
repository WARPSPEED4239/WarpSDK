package warpsdk.path;

import java.util.ArrayList;
import java.util.List;

public class Trajectory {
    private Path mPath;
    private double mMaxVelocity;
    private double mMaxAcceleration;
    private double mTimeStep;

    private List<ProfilePoint> mProfile;

    class ProfilePoint {
        public double t;
        public double x;
        public double y;

        public double dx;
        public double dy;

        public double distance;
        public double curvature;

        public String toString() {
            return "(t = " + t + "): x = " + x + ", y = " + y + ", dx = " + dx + ", dy = " + dy + ", distance = " + distance + ", curvature = " + curvature;
        }
    }

    public Trajectory(Path path, double maxVelocity, double maxAcceleration, double timeStep) {
        mPath = path;
        mMaxVelocity = maxVelocity;
        mMaxAcceleration = maxAcceleration;
        mTimeStep = timeStep;
    }

    public void calculate(int samples) {
        mProfile = new ArrayList<>();

        for (ISpline spline : mPath.getSplines()) {
            double dt = 1.0 / samples;

            for (double t = 0.0; t < 1.0 + dt; t += dt) {
                if (t > 1.0) {
                    t = 1.0;
                }

                Vector2 pos = spline.getPosition(t);

                ProfilePoint point = new ProfilePoint();
                point.t = t;
                point.x = pos.x;
                point.y = pos.y;
                point.dx = 0.0;
                point.dy = 0.0;
                point.distance = 0.0;
                point.curvature = 0.0;

                if (mProfile.size() > 0) {
                    ProfilePoint prev = mProfile.get(mProfile.size() - 1);
                    point.dx = point.x - prev.x;
                    point.dy = point.y - prev.y;

                    point.distance = prev.distance + Math.sqrt((point.dx*point.dx) + (point.dy*point.dy));
                }

                if (mProfile.size() > 1) {
                    ProfilePoint pen = mProfile.get(mProfile.size() - 2);
                    ProfilePoint prev = mProfile.get(mProfile.size() - 1);

                    prev.curvature = getCurvature(
                        pen,
                        prev,
                        point
                    );
                }

                mProfile.add(point);
            }
        }
    }

    public List<ProfilePoint> getProfile() {
        return mProfile;
    }

    public ProfilePoint[] getLeftProfile() {
        return null;
    }

    public ProfilePoint[] getRightProfile() {
        return null;
    }

    public double getDuration() {
        return 0.0;
    }

    public int getIndex() {
        return 0;
    }

    private double getCurvature(ProfilePoint p1, ProfilePoint p2, ProfilePoint p3) {
        double k1 = 0.5 * (p1.x*p1.x + p1.y*p1.y - p2.x*p2.x - p2.y*p2.y) / (p1.x - p2.x);
        double k2 = (p1.y - p2.y)/(p1.x - p2.x);
        double b = 0.5 * (p2.x*p2.x - 2*p2.x*k1 + p2.y*p2.y - p3.x*p3.x + 2*p3.x*k1 - p3.y*p3.y);
        b /= (p3.x*k2 - p3.y + p2.y - p2.x*k2);
        double a = k1 - k2*b;

        double dx = p1.x - a;
        double dy = p1.y - b;
        double r = Math.sqrt(dx*dx + dy*dy);
        return 1 / r;
    }

}