package warpsdk.controllers;

import warpsdk.path.Path;

public class PurePursuit {

    class ProfilePoint {
        public double leftVelocity;
        public double rightVelocity;

        public ProfilePoint(double leftVelocity, double rightVelocity) {
            this.leftVelocity = leftVelocity;
            this.rightVelocity = rightVelocity;
        }
    }

    private Path mPath;
    public void setPath(Path path) {
        mPath = path;
    }

    public ProfilePoint update(double x, double y, double dx, double dy) {
        return new ProfilePoint(0.0, 0.0);
    }

}