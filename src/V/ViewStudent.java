package V;

import javax.swing.*;
        import java.awt.*;

        import javax.swing.plaf.nimbus.NimbusLookAndFeel;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;



public class ViewStudent extends JFrame {

    String[] niveauxStrings = {"1 er année", "2 ème année", "3 ème année", "4 ème année", "5 ème année"};//different groupe que l'on auar avec groupe.nom
    String[] HorraireDebut = {"8H00", "9H30", "11H00", "12H30", "14H00","15H30","17H00","18H30"};
    String[] HorraireFin = { "9H30", "11H00", "12H30", "14H00","15H30","17H00","18H30","20H00"};
    String[] matieres = {"Maths", "Physique", "Physique Appliquée", "Informatique","LV1","LV2","Analyse financiere"};
    String[] Jours = {"Lundi", "Mardi", "Mercredi","Jeudi","Vendredi","Samedi"};
    private String[] listContent = {"EDT", "AfficherE","AfficherT","AfficherR","AfficherP"};
    private CardLayout cardLayout;
    private JPanel panelmenu,panelprincipal,panelJours,panelsemaine,panelhoraire,panelJoursSemaine,panelEdt, panelAfficherStudent,panelInfoStudent,panelInfoS,panelinfoSrecherche, panelListeStudent,
            panelAfficherTeacher, panelinfoTeacher,panelListeTeacher, panelAfficherRoom, panelinfoRoom,panelListeRoom,  panelAfficherPromo, panelinfoPromo,panelListePromo, panelinfoTrecherche,panelinfoT,panelinfoRrecherche,
            panelinfoR;


    /* Construction de l'interface graphique */
    public ViewStudent() {
        super( "Mon emploi du temps" );
        this.setSize(1200,800);//Largeur; Hauter
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo( null );
        //this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().add(panelmenu(), BorderLayout.NORTH);
        this.getContentPane().add(panelprincipal(), BorderLayout.CENTER);
        this.setVisible(true);

    }


/////////////////////////////

    private JPanel panelprincipal(){

        cardLayout = new CardLayout();
        panelprincipal = new JPanel();
        panelprincipal.setLayout(cardLayout);
        panelprincipal.add(panelEdt(), listContent[0]);
        //panelprincipal.add(panelAfficherS(), listContent[1]);
        //panelprincipal.add(panelAfficherT(), listContent[2]);
        panelprincipal.add(panelAfficherR(), listContent[1]);
        //panelprincipal.add(panelAfficherP(), listContent[2]);

        return panelprincipal;
    }

/////////////////////////////

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

        JMenuItem mnufreerooms = new JMenuItem( "Free rooms" );
        mnufreerooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[1]);
            }
        });
        mnuDisplay.add(mnufreerooms);

        mnuDisplay.addSeparator();

        JMenuItem mnuExit = new JMenuItem( "Exit" );
        mnuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[0]);
            }
        });
        mnuDisplay.add(mnuExit);

        menuBar.add(mnuDisplay);
        return menuBar;
    }
    /* Construction et injection de la barre de menu*/
    private JPanel panelmenu(){
        panelmenu =new JPanel();
        this.setJMenuBar( this.createMenuBar() );
        return panelmenu;
    }

/////////////////////////////

    private JPanel panelEdt(){
        panelEdt = new JPanel();
        panelEdt.setName("emploi du temps");
        panelEdt.setLayout(new BorderLayout());//////Probleme ici
        panelEdt.add(panelJoursSemaine(),BorderLayout.NORTH);
        panelEdt.add(panHeure(),BorderLayout.WEST);
        panelEdt.add(panQuadrillage(),BorderLayout.CENTER);
        return panelEdt;
    }

    private JPanel panelJoursSemaine(){

        panelJoursSemaine = new JPanel();
        panelJoursSemaine.setLayout(new BorderLayout());
        panelJoursSemaine.add(panJours(),BorderLayout.CENTER);

        JScrollPane jScrollPaneS = new JScrollPane(panSemaine());
        jScrollPaneS.setPreferredSize(new Dimension(0,70));
        panelJoursSemaine.add(jScrollPaneS,BorderLayout.NORTH);

        return panelJoursSemaine;
    }
    /* Methode de construction des heures */
    private  JPanel panHeure(){
        JPanel jPanel = new JPanel(new GridLayout(8,1));
        jPanel.setPreferredSize(new Dimension(160,0));
        jPanel.setBackground(new Color(80,80,200));
        JLabel jlabel_7H_9H30= new JLabel("7h - 9H30", SwingConstants.CENTER);
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
        JLabel jLabel_jours= new JLabel("Horaires / Jours", SwingConstants.CENTER);
        jPanel1.add(jLabel_jours);
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
    private JPanel panSemaine(){

        JPanel jPanel = new JPanel(new GridLayout(1,30));
        jPanel.setBackground(new Color(80,80,200));
        for(int i = 1; i<30;i++ ){
            jPanel.add( new JButton(String.valueOf(i)));
        }

        return jPanel;
    }
    /* Methode de construction des plages horaires*/
    private JPanel panQuadrillage() {
        JPanel jPanel = new JPanel(new GridLayout(8, 6));
        jPanel.setBackground(new Color(37, 253, 233));
        for (int i = 1; i < 49; i++) {

            jPanel.add(new JTextField("Matiere : Maths / Salle : i404 / Prof : Dedecker"));
        }
        return jPanel;
    }

    /////////////////////////////
    private JPanel panelAfficherR(){

        panelAfficherRoom = new JPanel();
        panelAfficherRoom.setLayout(new BorderLayout());
        panelAfficherRoom.add(panelinfoRoom(),BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListeRoom());
        jScrollPaneS.setPreferredSize(new Dimension(0,70));
        panelAfficherRoom.add(jScrollPaneS,BorderLayout.CENTER);

        return panelAfficherRoom;
    }

    private JPanel panelinfoRoom(){

        panelinfoRoom = new JPanel();
        panelinfoRoom.setLayout(new BorderLayout());
        panelinfoRoom.add(panelinfoRrecherche(),BorderLayout.NORTH);
        panelinfoRoom.add(panelinfoR(),BorderLayout.CENTER);

        return panelinfoRoom;

    }
    private JPanel panelinfoRrecherche(){
        panelinfoRrecherche = new JPanel();
        JTextField jtextRechRoom = new JTextField("Number" );
        jtextRechRoom.setPreferredSize(new Dimension(120,30));
        JButton jButtonRecherche = new JButton("Search");

        panelinfoRrecherche.add(jtextRechRoom);//---------------------Ajouter listner
        panelinfoRrecherche.add(jButtonRecherche);//---------------------Ajouter listner

        return panelinfoRrecherche;
    }
    private JPanel panelinfoR(){

        panelinfoR= new JPanel( new GridLayout(1,6));
        panelinfoR.setPreferredSize(new Dimension(0,50));

        JLabel jLabelRoomStage= new JLabel("Stage", SwingConstants.CENTER);
        panelinfoR.add(jLabelRoomStage);
        JLabel jLabelRnumber= new JLabel("Number", SwingConstants.CENTER);
        panelinfoR.add(jLabelRnumber);
        JLabel jLabelRetat= new JLabel("Etat", SwingConstants.CENTER);
        panelinfoR.add(jLabelRetat);

        return panelinfoR;

    }
    private JPanel panelListeRoom(){

        panelListeRoom = new JPanel(new GridLayout(50,1));//remplacer 15 par n etudiants
        //-------------------------------------------- recuperer le nombre d'eleves (dans un tableau ou jsp quoi)
        for (int i = 1; i <= 50; i++) {
            panelListeRoom.add(new JLabel(" 4", SwingConstants.CENTER));//Ajouter fonction string avec tt les infos de l'etudiant
            panelListeRoom.add(new JLabel(" i404", SwingConstants.CENTER));
            panelListeRoom.add(new JLabel(" Free", SwingConstants.CENTER));


        }

        return panelListeRoom;
    }

/////////////////////////////


    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        ViewStudent frame = new ViewStudent();
        frame.setVisible(true);
    }

}
