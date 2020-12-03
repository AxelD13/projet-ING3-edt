package v;
import m.session.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import javax.swing.*;
import java.text.DecimalFormat;
import java.util.List;

/** Class for the Pie CHART **/
public class PieChart extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;
    private List<Session> listsession;

    /** Create constructor **/
    public PieChart(String title, List<Session> listsession) {
        super(title);
        this.listsession = listsession;

        /** Create dataset **/
        PieDataset dataset = createDataset();

        /** Create chart **/
        JFreeChart chart = ChartFactory.createPieChart(
                "NUMBER COURSE",
                dataset,
                true,
                true,
                false);

        /** Format Label **/
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                " {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        /** Create Panel **/
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }
    /** use the data of the list session **/
    private PieDataset createDataset() {
        int CountP = 0;
        int CountM = 0;
        int CountI = 0;
        int CountE = 0;
        int CountA = 0;
        int CountLV1 = 0;
        int CountLV2 = 0;
        final DefaultPieDataset dataset = new DefaultPieDataset();
        for (Session session : listsession) {
            if (session.getIdCourse() == 1) {
                CountE++;
            }
            if (session.getIdCourse() == 2) {
                CountI++;
            }
            if (session.getIdCourse() == 4) {
                CountLV1++;
            }
            if (session.getIdCourse() == 4) {
                CountLV2++;
            }
            if (session.getIdCourse() == 5) {
                CountM++;
            }
            if (session.getIdCourse() == 6) {
                CountP++;
            }
            if (session.getIdCourse() == 13) {
                CountA++;
            }
        }
        dataset.setValue("Elec", new Double(CountE));
        dataset.setValue("Info", new Double(CountI));
        dataset.setValue("Physique", new Double(CountP));
        dataset.setValue("Math", new Double(CountM));
        dataset.setValue("Analyse Financière", new Double(CountA));
        dataset.setValue("LV1" , new Double(CountLV1));
        dataset.setValue("LV2" , new Double(CountLV2));
        return dataset;
    }
}