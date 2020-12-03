package v;
import m.session.Session;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.util.List;


/** class for the Straight CHART **/
public class Chart2 extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<Session> listsession;
    public Chart2(String Title,List<Session> listsession) {
        super(Title);
        this.listsession = listsession;

        /** Create Dataset **/
        CategoryDataset dataset = createDataset();

        /**Create chart**/
        JFreeChart chart=ChartFactory.createBarChart(
                "NUMBER OF COURSE", //Chart Title
                "Year", // Category axis
                "NUMBER OF COURSE", // Value axis
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );
        ChartPanel panel=new ChartPanel(chart);
        setContentPane(panel);
    }

    /**create dataset from session list**/
    private CategoryDataset createDataset()  {
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        int CountP = 0;
        int CountM = 0;
        int CountI = 0;
        int CountE = 0;
        int CountA = 0;
        int CountLV1 = 0;
        int CountLV2 = 0;
        for (Session session : listsession) {
            if (session.getIdCourse() == 1) {
                CountE++;
            }
            if (session.getIdCourse() == 2) {
                CountI++;
            }
            if (session.getIdCourse() == 3) {
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
        result.addValue(CountE,  "Electronique", "Semestre1");
        result.addValue(CountI,  "Informatique", "Semestre1");
        result.addValue(CountM, "Mathématiques", "Semestre1");
        result.addValue(CountP,  "Physique", "Semestre1");
        result.addValue(CountA,  "Analyse financière", "Semestre1");
        result.addValue(CountLV1,  "LV1", "Semestre1");
        result.addValue(CountLV2,  "LV2", "Semestre1");
        return result;
    }
}