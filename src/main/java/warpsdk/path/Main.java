package warpsdk.path;

import java.awt.Color;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main {
    public static void main(String[] args) {
        ISpline spline = new QuinticSpline(
            new Vector2(5, 5), 
            new Vector2(1, -1), 
            new Vector2(10, 0), 
            new Vector2(1, 0)
        );

        Path path = new Path(new Waypoint[]{
            new Waypoint(new Vector2(0, 0), new Vector2(1, 0)),
            new Waypoint(new Vector2(2, 3), new Vector2(0, 1)),
            new Waypoint(new Vector2(5, 5), new Vector2(1, -1)),
            new Waypoint(new Vector2(10, 0), new Vector2(1, 0))
        });
        drawPath((path));

        Trajectory traj = new Trajectory(path, 10, 10, 0.01);
        traj.calculate(1000);

        for (Trajectory.ProfilePoint point : traj.getProfile()) {
            System.out.println(point);
        }

    }

    private static void drawPath(Path path) {
        List<ISpline> splines = path.getSplines();
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries curve = new XYSeries("Curve");
        for (ISpline spline : splines) {
            double delta = 0.005;

            double arcLength = 0.0;
            Vector2 prev = null;
            for (double t = 0.0; t <= 1.0 + delta; t += delta) {
                Vector2 pos = spline.getPosition(t);
                curve.add(pos.x, pos.y);

                if (prev != null) {
                    double dx = pos.x - prev.x;
                    double dy = pos.y - prev.y;
                    arcLength += Math.sqrt(dx*dx + dy*dy);
                }

                prev = pos;
            }

        }
        dataset.addSeries(curve);

        JFreeChart chart = ChartFactory.createScatterPlot("curve", "x", "y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));

        ChartPanel panel = new ChartPanel(chart);
        JFrame frame = new JFrame("TestPlot");
        frame.setContentPane(panel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}