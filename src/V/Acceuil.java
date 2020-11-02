package V;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Acceuil extends JFrame {

    public Acceuil() {

        this.setTitle("Menu");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300, 100);
        this.setLocationRelativeTo(null); //positionnement de la fenetre au centre

        JPanel contentpane = (JPanel) this.getContentPane();
        contentpane.setLayout(new FlowLayout());

        JLabel jlabelId = new JLabel("ID");
        contentpane.add(jlabelId);
        JTextField jtextId = new JTextField(6);
        contentpane.add(jtextId);

        this.setVisible(true);
        JLabel jlabelMp = new JLabel("Password");
        contentpane.add(jlabelMp);
        JPasswordField jtextMp = new JPasswordField(6);
        contentpane.add(jtextMp);

        contentpane.add(boutonValide(jtextId,jtextMp),BorderLayout.CENTER);
    }

    private JPanel boutonValide(JTextField jtextId,JTextField jtextMp) {
        JPanel jPanel = new JPanel();
        JButton jButtonValider = new JButton("Ok");
        jPanel.add(jButtonValider);

        jButtonValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String id = jtextId.getText();
                String mp = jtextMp.getText();
                System.out.println(id);
                System.out.println(mp);
            }
        });

        return jPanel;
    }

    public static void main(String[] args) {
        Acceuil n = new Acceuil();
    }


}