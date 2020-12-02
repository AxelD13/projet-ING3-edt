package v;
import c.Database;
import m.user.EnumPermission;
import m.user.User;

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
        this.setSize(300, 100);
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
                User edtUser = db.identification(cnx, email, pw);

                if (edtUser == null) {  //affiche un message en cas d'email ou mdp incorrect
                    JOptionPane.showMessageDialog(contentpane, "Email ou mot de passe incorrect.","Erreur", JOptionPane.WARNING_MESSAGE);
                } else {  //si email et mdp sont ok, affiche l'interface
                    try {
                        UIManager.setLookAndFeel(new NimbusLookAndFeel());
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    JFrame frame = displayInterface(db, cnx, edtUser.getPermission(), edtUser.getId());
                    frame.setVisible(true);
                }
            }
        });
    }

    public JFrame displayInterface(Database db, Connection cnx, EnumPermission permission, int idUser) {
        JFrame frame = switch (permission) {
            case ADMIN -> new ViewDirector(db, cnx, idUser);
            case REFERENT -> new ViewRespSco(db, cnx, idUser);
            case TEACHER -> new ViewTeacher(db, cnx, idUser);
            case STUDENT -> new ViewStudent(db, cnx, idUser);
        };

        return frame;
    }

    public static void main(String[] args) {}

}