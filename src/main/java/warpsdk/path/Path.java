package warpsdk.path;

import java.util.List;
import java.util.ArrayList;

public class Path {
    private List<ISpline> mSplines;

    public Path(Waypoint[] waypoints) {
        mSplines = new ArrayList<ISpline>();
        for (int i = 1; i < waypoints.length; i++) {
            ISpline spline = new QuinticSpline(
                waypoints[i-1].getPosition(), 
                waypoints[i-1].getVelocity(), 
                waypoints[i-1].getAcceleration(),
                waypoints[i].getPosition(), 
                waypoints[i].getVelocity(),
                waypoints[i].getAcceleration()
            );
            mSplines.add(spline);
        }
    }

    public List<ISpline> getSplines() {
        return mSplines;
    }

}