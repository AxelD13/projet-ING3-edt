package v;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class PieChart extends ApplicationFrame {


    public PieChart(final String title) {

        super(title);
        final PieDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }


    private PieDataset createDataset() {
        final DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", new Double(43.2));
        dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));
        return dataset;
    }


    private JFreeChart createChart(final PieDataset dataset) {
        final JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Demo 2",  // chart title
                dataset,             // dataset
                true,                // include legend
                false,
                false
        );
        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("No data available");
        plot.setExplodePercent(1, 0.30);
        return chart;
    }


    public static void main(final String[] args) {

        final PieChart test = new PieChart("Pie Chart");
        test.pack();
        RefineryUtilities.centerFrameOnScreen(test);
        test.setVisible(true);

    }

}