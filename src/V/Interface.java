package V;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Interface extends JFrame{

        /* Construction de l'interface graphique */
        public Interface() {
            super( "Mon emploi du temps" );
            this.setSize(1200,800);//Largeur; Hauter
            this.setLocationRelativeTo( null );
            this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );

            // Construction et injection de la barre de menu
            this.setJMenuBar( this.createMenuBar() );

            JPanel contentpane = (JPanel) this.getContentPane();

            JScrollPane jScrollPaneS = new JScrollPane(panSemaine());
            jScrollPaneS.setPreferredSize(new Dimension(0,100));
            contentpane.add(jScrollPaneS,BorderLayout.NORTH);

            //contentpane.add(panJours(),new FlowLayout());

            JScrollPane jScrollPaneH = new JScrollPane(panHeure());
            jScrollPaneH.setPreferredSize(new Dimension(130,0));
            contentpane.add(jScrollPaneH,BorderLayout.WEST);

        }

        private  JPanel panHeure(){
            JPanel jPanel = new JPanel(new GridLayout(8,1));
            jPanel.setBackground(new Color(80,80,200));
            JLabel jlabel_7H_9H30= new JLabel("7h - 9H30");
            jPanel.add(jlabel_7H_9H30);
            JLabel jlabel_9H30_11H= new JLabel("9H30 - 11H");
            jPanel.add(jlabel_9H30_11H);
            JLabel jlabel_11H_12H30= new JLabel("11H - 12H30");
            jPanel.add(jlabel_11H_12H30);
            JLabel jlabel_12H30_14H= new JLabel("12H30 - 14H");
            jPanel.add(jlabel_12H30_14H);
            JLabel jlabel_14H_15H30= new JLabel("14H - 15H30");
            jPanel.add(jlabel_14H_15H30);
            JLabel jlabel_15H30_17H= new JLabel("15H30 - 17H");
            jPanel.add(jlabel_15H30_17H);
            JLabel jlabel_17H_18H30= new JLabel("17H - 18H30");
            jPanel.add(jlabel_17H_18H30);
            JLabel jlabel_18H30_20H= new JLabel("18H30 - 20H");
            jPanel.add(jlabel_18H30_20H);

            return jPanel;
        }

        private  JPanel panJours(){

            JPanel jPanel1 = new JPanel( new GridLayout(1,6));
            JLabel jLabel_Lundi= new JLabel("Lundi");
            jPanel1.add(jLabel_Lundi);
            JLabel jLabel_Mardi= new JLabel("Mardi");
            jPanel1.add(jLabel_Mardi);
            JLabel jLabel_Mercredi= new JLabel("Mercredi");
            jPanel1.add(jLabel_Mercredi);
            JLabel jLabel_Jeudi= new JLabel("Jeudi");
            jPanel1.add(jLabel_Jeudi);
            JLabel jLabel_Vendredi= new JLabel("Vendredi");
            jPanel1.add(jLabel_Vendredi);
            JLabel jLabel_Samedi= new JLabel("Samedi");
            jPanel1.add(jLabel_Samedi);

            return jPanel1;
        }

        /* Methode de construction des boutons semaines*/
        private JPanel panSemaine(){
            JPanel jPanel = new JPanel(new GridLayout(1,30));
            jPanel.setBackground(new Color(80,80,200));
            for(int i = 1; i<30;i++ ){
                jPanel.add( new JButton(String.valueOf(i)));
            }

/*
        JPanel jPanel1 = new JPanel( new GridLayout(1,6));
        JLabel jLabel_Lundi= new JLabel("Lundi");
        jPanel1.add(jLabel_Lundi);
        JLabel jLabel_Mardi= new JLabel("Mardi");
        jPanel1.add(jLabel_Mardi);
        JLabel jLabel_Mercredi= new JLabel("Mercredi");
        jPanel1.add(jLabel_Mercredi);
        JLabel jLabel_Jeudi= new JLabel("Jeudi");
        jPanel1.add(jLabel_Jeudi);
        JLabel jLabel_Vendredi= new JLabel("Vendredi");
        jPanel1.add(jLabel_Vendredi);
        JLabel jLabel_Samedi= new JLabel("Samedi");
        jPanel1.add(jLabel_Samedi);

        jPanel.add(jPanel1,BorderLayout.NORTH);
*/
            return jPanel;
        }

        /* Methode de construction de la barre de menu */
        private JMenuBar createMenuBar() {

            // La barre de menu à proprement parler
            JMenuBar menuBar = new JMenuBar();
            menuBar.setPreferredSize(new Dimension(0,50));

            // Définition du menu déroulant "Display" et de son contenu
            JMenu mnuDisplay = new JMenu( "Dispaly");
            //mnuDisplay.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));// Ajouter de la disantce entre les boutons

            JMenuItem mnuEdT = new JMenuItem( "temp jobs" );
            mnuEdT.addActionListener( this::mnuNewListener );
            mnuDisplay.add(mnuEdT);

            mnuDisplay.addSeparator();

            JMenuItem mnuStudent = new JMenuItem( "Student list" );
            mnuDisplay.add(mnuStudent);

            JMenuItem mnuTeach = new JMenuItem( "Teacher list" );
            mnuDisplay.add(mnuTeach);

            JMenuItem mnuPromos = new JMenuItem( "Promos list" );
            mnuDisplay.add(mnuPromos);

            mnuDisplay.addSeparator();

            JMenuItem mnufreerooms = new JMenuItem( "Free rooms" );
            mnuDisplay.add(mnufreerooms);

            mnuDisplay.addSeparator();

            JMenuItem mnuExit = new JMenuItem( "Exit" );
            mnuDisplay.add(mnuExit);

            menuBar.add(mnuDisplay);

            // Définition du menu déroulant "Ajouter" et de son contenu
            JMenu mnuEdit = new JMenu( "Add" );

            JMenuItem mnuAddTeacher = new JMenuItem( "Teacher" );
            mnuEdit.add(mnuAddTeacher);

            JMenuItem mnuAddStudent = new JMenuItem( "Student" );
            mnuEdit.add(mnuAddStudent);

            mnuEdit.addSeparator();

            JMenuItem mnuAddClasses = new JMenuItem( "Classes" );
            mnuEdit.add(mnuAddClasses);

            menuBar.add(mnuEdit);

            // Définition du menu déroulant "Delete" et de son contenu
            JMenu mnuDelete = new JMenu( "Delete" );

            JMenuItem mnuDeleteTeacher = new JMenuItem( "Teacher" );
            mnuDelete.add(mnuDeleteTeacher);

            JMenuItem mnuDeleteStudent = new JMenuItem( "Student" );
            mnuDelete.add(mnuDeleteStudent);

            mnuDelete.addSeparator();

            JMenuItem mnuDeleteClasses = new JMenuItem( "Classes" );
            mnuDelete.add(mnuDeleteClasses);

            menuBar.add( mnuDelete );

            return menuBar;
        }

        public void mnuNewListener( ActionEvent event ) {
            JOptionPane.showMessageDialog( this, "Button clicked !" );
        }

        public static void main(String[] args) throws Exception {
            UIManager.setLookAndFeel( new NimbusLookAndFeel() );
            Interface frame = new Interface();
            frame.setVisible(true);
        }
    }

