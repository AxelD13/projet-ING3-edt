package V;

import javax.swing.*;
import java.awt.*;

public class Acceuil extends JFrame{

        private JButton jButtonValider; //= new JButton("Validé");
        private JLabel jLabelTextMp = new JLabel("veuillez saisir votre mot de passe");
        private JLabel jLabelTextId = new JLabel("veuillez saisir votre identifiant");
        private JTextField jTextFieldMp = new JTextField(6);
        private JTextField jTextFieldId = new JTextField(6);

        public Acceuil(JButton jButton1){
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setLayout(new BorderLayout());

            JPanel jpaneln = new JPanel();
            jpaneln.setLayout(new FlowLayout());
            jpaneln.add(jLabelTextId);
            jpaneln.add(jTextFieldId);
            this.add(jpaneln,BorderLayout.NORTH);

            JPanel jpanel2 = new JPanel();
            jpanel2.setLayout(new FlowLayout());
            jpanel2.add(jLabelTextMp);
            jpanel2.add(jTextFieldMp);
            this.add(jpanel2,BorderLayout.CENTER);

            JPanel jpanelc = new JPanel();
            jpanelc.setLayout(new GridLayout());
            jpanelc.add(jButton1);
            this.add(jpanelc,BorderLayout.SOUTH);

            this.setBounds(450,450,600,300);
            this.setVisible(true);
        }


        public static void main(String[] args){
            Acceuil n = new Acceuil(new JButton(("salut")));
        }

    }

