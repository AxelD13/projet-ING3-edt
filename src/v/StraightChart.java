package v;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StatisticalBarRenderer;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
import org.jfree.data.statistics.StatisticalCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;



public class StraightChart extends ApplicationFrame {


    public StraightChart(final String title) {

        super(title);
        final StatisticalCategoryDataset dataset = createDataset();

        final CategoryAxis xAxis = new CategoryAxis("Type");
        xAxis.setLowerMargin(0.01d); // percentage of space before first bar
        xAxis.setUpperMargin(0.01d); // percentage of space after last bar
        xAxis.setCategoryMargin(0.05d); // percentage of space between categories
        final ValueAxis yAxis = new NumberAxis("Value");

        // define the plot
        final CategoryItemRenderer renderer = new StatisticalBarRenderer();
        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

        final JFreeChart chart = new JFreeChart("Note Semestre 1",
                new Font("Helvetica", Font.BOLD, 14),
                plot,
                true);
        //chart.setBackgroundPaint(Color.white);
        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }



    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private StatisticalCategoryDataset createDataset() {

        final DefaultStatisticalCategoryDataset result = new DefaultStatisticalCategoryDataset();

        result.add(32.5, 0, "Math", "Semestre1");
        result.add(22.9,  0, "Physique", "Semestre1");
        result.add(12.5, 0, "Elec", "Semestre1");



        return result;

    }


    public static void main(final String[] args) {

        final StraightChart test1 = new StraightChart(
                "Graphique test"
        );
        test1.pack();
        RefineryUtilities.centerFrameOnScreen(test1);
        test1.setVisible(true);

    }

}
