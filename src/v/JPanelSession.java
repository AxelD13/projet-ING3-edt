package v;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
public class JPanelSession extends JPanel {
    private int week;
    private Date date;
    private Time startTime;
    private Time endTime;
    private JTextField textField = new JTextField();
    public JPanelSession(int week, Date date, Time startTime, Time endTime) {
        super(new GridLayout(1, 1));
        this.week = week;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.add(this.textField);
        this.textField.setVisible(true);
    }
    public void setTextField(JTextField textField) {
        this.textField = textField;
        this.add(this.textField);
        this.textField.setVisible(true);
    }
}