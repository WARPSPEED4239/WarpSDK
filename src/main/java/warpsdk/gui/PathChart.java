package warpsdk.gui;

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

import warpsdk.path.ISpline;
import warpsdk.path.Path;
import warpsdk.path.Vector2;

public class PathChart {
    private static void drawPath(Path path) {
        List<ISpline> splines = path.getSplines();
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries curve = new XYSeries("Curve");
        for (ISpline spline : splines) {
            double delta = 0.005;
            for (double t = 0.0; t <= 1.0 + delta; t += delta) {
                Vector2 pos = spline.getPosition(t);
                curve.add(pos.x, pos.y);
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