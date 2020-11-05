package V;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {
    //private CardLayout cardLayout;
    //private JPanel panelGlobal;

    String[] niveauxStrings = {"1 er année", "2 ème année", "3 ème année", "4 ème année", "5 ème année"};//different groupe que l'on auar avec groupe.nom
    String[] HorraireDebut = {"8H00", "9H30", "11H00", "12H30", "14H00","15H30","17H00","18H30"};
    String[] HorraireFin = { "9H30", "11H00", "12H30", "14H00","15H30","17H00","18H30","20H00"};
    String[] matieres = {"Maths", "Physique", "Physique Appliquée", "Informatique"};
    String[] Jours = {"Lundi", "Mardi", "Mercredi","Jeudi","Vendredi","Samedi"};
    //String[] Date = {"1", "2", "3", "Informatique"};

    /* Construction de l'interface graphique */
    public Interface() {
        super( "Mon emploi du temps" );
        this.setSize(1400,800);//Largeur; Hauter
        this.setLocationRelativeTo( null );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );

        // Construction et injection de la barre de menu
        this.setJMenuBar( this.createMenuBar() );

        JPanel contentpane = (JPanel) this.getContentPane();

        JScrollPane jScrollPaneS = new JScrollPane(panSemaine());
        jScrollPaneS.setPreferredSize(new Dimension(0,70));
        contentpane.add(jScrollPaneS,BorderLayout.PAGE_END);

        JScrollPane jScrollPaneH = new JScrollPane(panHeure());
        jScrollPaneH.setPreferredSize(new Dimension(200,80));
        contentpane.add(jScrollPaneH,BorderLayout.WEST);

        JScrollPane jScrollPaneJ = new JScrollPane(panJours());
        jScrollPaneJ.setPreferredSize(new Dimension(200,80));
        contentpane.add(jScrollPaneJ,BorderLayout.PAGE_START);

        JScrollPane jScrollPaneQ = new JScrollPane(panQuadrillage());
        jScrollPaneQ.setPreferredSize(new Dimension(200,80));
        contentpane.add(jScrollPaneQ,BorderLayout.CENTER);
    }

    /* Methode de construction des heures */
    private  JPanel panHeure(){
        JPanel jPanel = new JPanel(new GridLayout(8,1));
        jPanel.setBackground(new Color(80,80,200));
        JLabel jlabel_7H_9H30= new JLabel("8h - 9H30", SwingConstants.CENTER);
        jPanel.add(jlabel_7H_9H30);
        JLabel jlabel_9H30_11H= new JLabel("9H30 - 11H", SwingConstants.CENTER);
        jPanel.add(jlabel_9H30_11H);
        JLabel jlabel_11H_12H30= new JLabel("11H - 12H30", SwingConstants.CENTER);
        jPanel.add(jlabel_11H_12H30);
        JLabel jlabel_12H30_14H= new JLabel("12H30 - 14H", SwingConstants.CENTER);
        jPanel.add(jlabel_12H30_14H);
        JLabel jlabel_14H_15H30= new JLabel("14H - 15H30", SwingConstants.CENTER);
        jPanel.add(jlabel_14H_15H30);
        JLabel jlabel_15H30_17H= new JLabel("15H30 - 17H", SwingConstants.CENTER);
        jPanel.add(jlabel_15H30_17H);
        JLabel jlabel_17H_18H30= new JLabel("17H - 18H30", SwingConstants.CENTER);
        jPanel.add(jlabel_17H_18H30);
        JLabel jlabel_18H30_20H= new JLabel("18H30 - 20H", SwingConstants.CENTER);
        jPanel.add(jlabel_18H30_20H);

        return jPanel;
    }

    /* Methode de construction des jours de la semaines*/
    private  JPanel panJours(){

        JPanel jPanel1 = new JPanel( new GridLayout(1,7));
        jPanel1.setBackground(new Color(80,80,200));
        JLabel jLabel_espace= new JLabel("Horaires / Jours", SwingConstants.CENTER);
        jPanel1.add(jLabel_espace);
        JLabel jLabel_Lundi= new JLabel("Lundi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Lundi);
        JLabel jLabel_Mardi= new JLabel("Mardi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Mardi);
        JLabel jLabel_Mercredi= new JLabel("Mercredi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Mercredi);
        JLabel jLabel_Jeudi= new JLabel("Jeudi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Jeudi);
        JLabel jLabel_Vendredi= new JLabel("Vendredi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Vendredi);
        JLabel jLabel_Samedi= new JLabel("Samedi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Samedi);

        return jPanel1;
    }

    /* Methode de construction des boutons semaines*/
    private JPanel panQuadrillage() {
        JPanel jPanel = new JPanel(new GridLayout(8, 6));
        jPanel.setBackground(new Color(37, 253, 233));
        for (int i = 1; i < 49; i++) {

            jPanel.add(new JButton(""));
        }
        return jPanel;
    }


        /* Methode de construction des boutons semaines*/
    private JPanel panSemaine() {
        JPanel jPanel = new JPanel(new GridLayout(1, 30));
        jPanel.setBackground(new Color(80, 80, 200));
        for (int i = 1; i < 30; i++) {
            jPanel.add(new JButton(String.valueOf(i)));
        }
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
        //mnuEdT.addActionListener( this::mnuNewListener );
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
        mnuAddTeacher.addActionListener(this::ListnerAddTeacher);
        mnuEdit.add(mnuAddTeacher);

        JMenuItem mnuAddStudent = new JMenuItem( "Student" );
        mnuAddStudent.addActionListener(this::ListnerAddStudent);
        mnuEdit.add(mnuAddStudent);

        mnuEdit.addSeparator();

        JMenuItem mnuAddCours = new JMenuItem( "Cours" );
        mnuAddCours.addActionListener(this::ListnerAddCours);
        mnuEdit.add(mnuAddCours);

        mnuEdit.addSeparator();

        JMenuItem mnuAddMatiere = new JMenuItem( "Matiere" );
        //mnuAddMatiere.addActionListener(this::ListnerAddCours);
        mnuEdit.add(mnuAddMatiere);

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

    public void ListnerAddTeacher(ActionEvent event) {
        create_frameAddTeacher();
    }
    public void ListnerAddCours(ActionEvent event) {
        create_frameAddCours();
    }
    public void ListnerAddStudent(ActionEvent event) {
        create_frameAddStudent();
    }

    /* JPanel 'Ajout d'un cours' */
    public JFrame create_frameAddCours() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(6, 2));

        JLabel jlabelNomClasse = new JLabel("Nom :");
        JTextField jtfNomClasse = new JTextField(10);

        JLabel jlabelGroupeClasse = new JLabel("Groupe :");
        JComboBox jcmbGroupeClasse = new JComboBox<String>(niveauxStrings);//different groupe que l'on auar avec groupe.nom

        JLabel jlabelMatiere = new JLabel("matiere:");
        JComboBox jcmbMatiereCours = new JComboBox<String>(matieres);

        JLabel jlabelHeureDebut = new JLabel("Heure de debut :");
        JComboBox jcmHeureDebut = new JComboBox<String>(HorraireDebut);

        JLabel jlabelHeureFin = new JLabel("Heure de Fin :");//a modifier pour ajouter une horloge
        JComboBox jcmHeureFin = new JComboBox<String>(HorraireFin);

        JButton jButonEnregistre = new JButton("registrer");
        jButonEnregistre.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                System.out.print(jtfNomClasse.getText());
                System.out.print(jcmbGroupeClasse.getSelectedItem());
                System.out.print(jcmbMatiereCours.getSelectedItem());
                System.out.print(jcmHeureDebut.getSelectedItem());
                System.out.print(jcmHeureFin.getSelectedItem());

            }
        });

        jPanel.add(jlabelNomClasse);
        jPanel.add(jtfNomClasse);
        jPanel.add(jlabelGroupeClasse);
        jPanel.add(jcmbGroupeClasse);
        jPanel.add(jlabelMatiere);
        jPanel.add(jcmbMatiereCours);
        jPanel.add(jlabelHeureDebut);
        jPanel.add(jcmHeureDebut);
        jPanel.add(jlabelHeureFin);
        jPanel.add(jcmHeureFin);
        jPanel.add(jButonEnregistre, BorderLayout.SOUTH);

        JFrame frameAjoutClasse = new JFrame();
        frameAjoutClasse.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        frameAjoutClasse.setSize(400,300);
        frameAjoutClasse.setTitle("Ajout d'un cours");
        frameAjoutClasse.setLocationRelativeTo(null);
        frameAjoutClasse.setResizable(false);
        frameAjoutClasse.setVisible(true);
        frameAjoutClasse.getContentPane().add(jPanel, BorderLayout.CENTER);
        frameAjoutClasse.setVisible(true);


        return frameAjoutClasse;
    }

    /* JPanel 'Ajout d'un professeur' */
    public JFrame create_frameAddTeacher() {
        JPanel jpanelAddTeacher = new JPanel();
        jpanelAddTeacher.setLayout(new GridLayout(5,2));

        JLabel jlabelNomProfesseur = new JLabel("Last Name :");
        JTextField jtfNomProfesseur = new JTextField(10);

        JLabel jlabelPrenomProfesseur = new JLabel("First name :");
        JTextField jtfPrenomProfesseur = new JTextField(10);

        JLabel jlabelMatiereProfesseur = new JLabel("Classes :");
        String[] matieres = {"Maths", "Physique", "Physique Appliquée", "Informatique"};
        JComboBox jcmbMatiereProfesseur = new JComboBox<String>(matieres);

        JLabel jlabelNombreHeureProfesseur = new JLabel("Numbers of hours :");
        JTextField jtfNombreHeureProfesseur = new JTextField(10);

        JButton jButtonAddProf = new JButton("registre");
        jButtonAddProf.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                System.out.print(jtfNomProfesseur.getText());
                System.out.print(jtfNomProfesseur.getText());
                System.out.print(jcmbMatiereProfesseur.getSelectedItem());
                System.out.print(jtfNombreHeureProfesseur.getText());

            }
        });

        jpanelAddTeacher.add(jlabelNomProfesseur);
        jpanelAddTeacher.add(jtfNomProfesseur);
        jpanelAddTeacher.add(jlabelPrenomProfesseur);
        jpanelAddTeacher.add(jtfPrenomProfesseur);
        jpanelAddTeacher.add(jlabelMatiereProfesseur);
        jpanelAddTeacher.add(jcmbMatiereProfesseur);
        jpanelAddTeacher.add(jlabelNombreHeureProfesseur);
        jpanelAddTeacher.add(jtfNombreHeureProfesseur);
        jpanelAddTeacher.add(jButtonAddProf,new FlowLayout());

        JFrame frameAddTeacher = new JFrame();
        frameAddTeacher.setSize(400,300);
        frameAddTeacher.setTitle("Add teachers");
        frameAddTeacher.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        frameAddTeacher.setLocationRelativeTo(null);
        frameAddTeacher.setResizable(false);//Pour redimensionner la fenetre
        frameAddTeacher.setVisible(true);
        frameAddTeacher.getContentPane().add(jpanelAddTeacher, BorderLayout.CENTER);
        frameAddTeacher.setVisible(true);

        return frameAddTeacher;
    }

    /* JPanel 'Ajout d'un eleve' */
    public JFrame create_frameAddStudent() {
        JPanel jpanelAddEleve = new JPanel();
        jpanelAddEleve.setLayout(new GridLayout(5,2));

        JLabel jlabelEleveName = new JLabel("Name :");
        JTextField jtfEleveName = new JTextField(10);

        JLabel jlabelEleveFirstName = new JLabel("First name :");
        JTextField jtfEleveFirstName = new JTextField(10);

        JLabel jlabelEleveClasse = new JLabel("Classe :");
        JComboBox jcmbEleveClasse = new JComboBox<String>();//recuperer les groupes sur mysql

        JLabel jlabelEleveGrp = new JLabel("Promo :");
        JComboBox jcmbEleveGrp = new JComboBox<String>(niveauxStrings);//recuperer les differentes promos

        JButton jButtonAddEleve = new JButton("registre");
        jButtonAddEleve.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                System.out.print(jtfEleveName.getText());
                System.out.print(jtfEleveFirstName.getText());
                System.out.print(jcmbEleveClasse.getSelectedItem());
                System.out.print(jcmbEleveGrp.getSelectedItem());

            }
        });

        jpanelAddEleve.add(jlabelEleveName);
        jpanelAddEleve.add(jtfEleveName);
        jpanelAddEleve.add(jlabelEleveFirstName);
        jpanelAddEleve.add(jtfEleveFirstName);
        jpanelAddEleve.add(jlabelEleveClasse);
        jpanelAddEleve.add(jcmbEleveClasse);
        jpanelAddEleve.add(jlabelEleveGrp);
        jpanelAddEleve.add(jcmbEleveGrp);
        jpanelAddEleve.add(jButtonAddEleve);

        JFrame jframeAddEleve = new JFrame();
        jframeAddEleve.setSize(400,300);
        jframeAddEleve.setTitle("Ajout d'un élève");
        jframeAddEleve.setLocationRelativeTo(null);
        jframeAddEleve.setResizable(false);
        jframeAddEleve.setVisible(true);
        jframeAddEleve.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        jframeAddEleve.getContentPane().add(jpanelAddEleve, BorderLayout.CENTER);
        jframeAddEleve.setVisible(true);

        return jframeAddEleve;
    }


    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        Interface frame = new Interface();
        frame.setVisible(true);
    }
}