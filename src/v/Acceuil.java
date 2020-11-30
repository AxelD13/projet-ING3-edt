package v;
import c.Database;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Acceuil extends JFrame {

    public Acceuil(Database db, Connection cnx) {

        this.setTitle("Menu");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 100);
        this.setLocationRelativeTo(null); //positionnement de la fenetre au centre

        JPanel contentpane = (JPanel) this.getContentPane();
        contentpane.setLayout(new FlowLayout());

        JLabel jlabelId = new JLabel("Email");
        contentpane.add(jlabelId);
        JTextField jtextId = new JTextField(6);
        contentpane.add(jtextId);

        this.setVisible(true);

        JLabel jlabelMp = new JLabel("Password");
        contentpane.add(jlabelMp);
        JPasswordField jtextMp = new JPasswordField(6);
        contentpane.add(jtextMp);

        JButton jButtonValider = new JButton("Ok");
        contentpane.add(jButtonValider, BorderLayout.CENTER);

        jButtonValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String email = jtextId.getText();
                String pw = String.valueOf(jtextMp.getPassword());
                String status = db.identification(cnx, email, pw);

                if (!status.equals("OK")) {  //affiche un message en cas d'email ou mdp incorrect
                    JOptionPane.showMessageDialog(contentpane, status," Erreur", JOptionPane.WARNING_MESSAGE);
                } else {  //si email et mdp sont ok, affiche l'interface
                    try {
                        UIManager.setLookAndFeel(new NimbusLookAndFeel());
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    ViewDirector frame = new ViewDirector();
                    frame.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {}

}