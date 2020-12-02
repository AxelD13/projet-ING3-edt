package v;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTeacher extends JFrame {

    String[] niveauxStrings = {"1 er année", "2 ème année", "3 ème année", "4 ème année", "5 ème année"};//different groupe que l'on auar avec groupe.nom
    String[] HorraireDebut = {"8H00", "9H30", "11H00", "12H30", "14H00", "15H30", "17H00", "18H30"};
    String[] HorraireFin = {"9H30", "11H00", "12H30", "14H00", "15H30", "17H00", "18H30", "20H00"};
    String[] matieres = {"Maths", "Physique", "Physique Appliquée", "Informatique", "LV1", "LV2", "Analyse financiere"};
    String[] Jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    private String[] listContent = {"EDT", "AfficherE", "AfficherT", "AfficherR", "AfficherP"};
    private String[] listPromo = {"0", "1", "2", "3", "4", "5"};
    private String[] listStudent = {"0", "1"};
    private CardLayout cardLayout, cardLayoutPromo,cardLayoutStudent;

    private JPanel panelmenu, panelprincipal, panelJoursSemaine, panelEdt, panelAfficherStudent, panelInfoStudent, panelInfoS, panelinfoSrecherche, panelListeStudent,
           panelAfficherRoom,panelListeEleveP, panelinfoRoom, panelListeRoom, panelAfficherPromo, panelListePromo,panelinfoRrecherche, panelinfoR;


    /* Construction de l'interface graphique */
    public ViewTeacher() {
        super("Mon emploi du temps");
        this.setSize(1200, 800);//Largeur; Hauter
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().add(panelmenu(), BorderLayout.NORTH);
        this.getContentPane().add(panelprincipal(), BorderLayout.CENTER);
        this.setVisible(true);

    }

/////////////////////////////

    private JPanel panelprincipal() {

        cardLayout = new CardLayout();
        panelprincipal = new JPanel();
        panelprincipal.setLayout(cardLayout);
        panelprincipal.add(panelEdt(), listContent[0]);
        panelprincipal.add(panelAfficherS(), listContent[1]);
        panelprincipal.add(panelAfficherR(), listContent[2]);
        panelprincipal.add(panelAfficherP(), listContent[3]);

        return panelprincipal;
    }

/////////////////////////////

    /* Methode de construction de la barre de menu */
    private JMenuBar createMenuBar() {

        // La barre de menu à proprement parler
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(0, 50));

        // Définition du menu déroulant "Display" et de son contenu
        JMenu mnuDisplay = new JMenu("Dispaly");

        JMenuItem mnuEdT = new JMenuItem("temp jobs");
        mnuDisplay.add(mnuEdT);
        mnuDisplay.addSeparator();

        JMenuItem mnuStudent = new JMenuItem("Student list");
        mnuStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[1]);
            }
        });
        mnuDisplay.add(mnuStudent);

        JMenuItem mnuPromos = new JMenuItem("Promos list");
        mnuPromos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[3]);
            }
        });
        mnuDisplay.add(mnuPromos);

        mnuDisplay.addSeparator();

        JMenuItem mnufreerooms = new JMenuItem("Free rooms");
        mnufreerooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[2]);
            }
        });
        mnuDisplay.add(mnufreerooms);

        mnuDisplay.addSeparator();

        JMenuItem mnuExit = new JMenuItem("Exit");
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
    private JPanel panelmenu() {
        panelmenu = new JPanel();
        this.setJMenuBar(this.createMenuBar());
        return panelmenu;
    }

/////////////////////////////

    private JPanel panelEdt() {
        panelEdt = new JPanel();
        panelEdt.setName("emploi du temps");
        panelEdt.setLayout(new BorderLayout());//////Probleme ici
        panelEdt.add(panelJoursSemaine(), BorderLayout.NORTH);
        panelEdt.add(panHeure(), BorderLayout.WEST);
        panelEdt.add(panQuadrillage(), BorderLayout.CENTER);
        return panelEdt;
    }

    private JPanel panelJoursSemaine() {

        panelJoursSemaine = new JPanel();
        panelJoursSemaine.setLayout(new BorderLayout());
        panelJoursSemaine.add(panJours(), BorderLayout.CENTER);

        JScrollPane jScrollPaneS = new JScrollPane(panSemaine());
        jScrollPaneS.setPreferredSize(new Dimension(0, 70));
        panelJoursSemaine.add(jScrollPaneS, BorderLayout.NORTH);

        return panelJoursSemaine;
    }
    /* Methode de construction des heures */
    private JPanel panHeure() {
        JPanel jPanel = new JPanel(new GridLayout(8, 1));
        jPanel.setPreferredSize(new Dimension(160, 0));
        jPanel.setBackground(new Color(255, 255, 255));
        JLabel jlabel_7H_9H30 = new JLabel("7h - 9H30", SwingConstants.CENTER);
        jPanel.add(jlabel_7H_9H30);
        JLabel jlabel_9H30_11H = new JLabel("9H30 - 11H", SwingConstants.CENTER);
        jPanel.add(jlabel_9H30_11H);
        JLabel jlabel_11H_12H30 = new JLabel("11H - 12H30", SwingConstants.CENTER);
        jPanel.add(jlabel_11H_12H30);
        JLabel jlabel_12H30_14H = new JLabel("12H30 - 14H", SwingConstants.CENTER);
        jPanel.add(jlabel_12H30_14H);
        JLabel jlabel_14H_15H30 = new JLabel("14H - 15H30", SwingConstants.CENTER);
        jPanel.add(jlabel_14H_15H30);
        JLabel jlabel_15H30_17H = new JLabel("15H30 - 17H", SwingConstants.CENTER);
        jPanel.add(jlabel_15H30_17H);
        JLabel jlabel_17H_18H30 = new JLabel("17H - 18H30", SwingConstants.CENTER);
        jPanel.add(jlabel_17H_18H30);
        JLabel jlabel_18H30_20H = new JLabel("18H30 - 20H", SwingConstants.CENTER);
        jPanel.add(jlabel_18H30_20H);

        return jPanel;
    }
    /* Methode de construction des jours de la semaines*/
    private JPanel panJours() {

        JPanel jPanel1 = new JPanel(new GridLayout(1, 7));
        JLabel jLabel_jours = new JLabel("Horaires / Jours", SwingConstants.CENTER);
        jPanel1.add(jLabel_jours);
        JLabel jLabel_Lundi = new JLabel("Lundi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Lundi);
        JLabel jLabel_Mardi = new JLabel("Mardi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Mardi);
        JLabel jLabel_Mercredi = new JLabel("Mercredi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Mercredi);
        JLabel jLabel_Jeudi = new JLabel("Jeudi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Jeudi);
        JLabel jLabel_Vendredi = new JLabel("Vendredi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Vendredi);
        JLabel jLabel_Samedi = new JLabel("Samedi", SwingConstants.CENTER);
        jPanel1.add(jLabel_Samedi);

        return jPanel1;
    }
    /* Methode de construction des boutons semaines*/
    private JPanel panSemaine() {

        JPanel jPanel = new JPanel(new GridLayout(1, 30));
        jPanel.setBackground(new Color(255, 255, 255));
        for (int i = 1; i < 30; i++) {
            jPanel.add(new JButton(String.valueOf(i)));
        }

        return jPanel;
    }
    /* Methode de construction des plages horaires*/
    private JPanel panQuadrillage() {
        JPanel jPanel = new JPanel(new GridLayout(8, 6));
        jPanel.setBackground(new Color(255, 255, 255));
        for (int i = 1; i < 49; i++) {

            jPanel.add(new JTextField("Matiere : Maths / Salle : i404 / Prof : Dedecker"));
        }
        return jPanel;
    }

/////////////////////////////

    private JPanel panelAfficherS(){
        cardLayoutStudent = new CardLayout();
        panelAfficherStudent = new JPanel();
        panelAfficherStudent.setLayout(cardLayoutStudent);
        panelAfficherStudent.add(panelAfficherS2(), listStudent[0]);
        //panelAfficherStudent.add(panelAfficherS2(Stud1), listTeacher[0]);//prof1 Fonction qui retourne tt les eleves
        //panelAfficherStudent.add(panelAfficherS2(Stud2), listTeacher[1]);// prof2 fonction qui retourne une liste de eleve en fonction du nom
        return panelAfficherStudent;
    }

    //private JPanel panelAfficherS2(list listStudient){
    private JPanel panelAfficherS2() {

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(panelInfoStudent(), BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListeStudent(50));///////////////////NB eleve----------------et ajouter liste des studient
        jScrollPaneS.setPreferredSize(new Dimension(0, 70));
        jpanel.add(jScrollPaneS, BorderLayout.CENTER);

        return jpanel;
    }
    private JPanel panelInfoStudent() {

        panelInfoStudent = new JPanel();
        panelInfoStudent.setLayout(new BorderLayout());
        panelInfoStudent.add(panelinfoSrecherche(), BorderLayout.NORTH);
        panelInfoStudent.add(panelinfoS(), BorderLayout.CENTER);

        return panelInfoStudent;
    }
    //private JPanel panelinfoSrecherche(list listTtLesEleves){
    private JPanel panelinfoSrecherche() {
        panelinfoSrecherche = new JPanel();
        JTextField jtextRechSNom = new JTextField("Nom");
        jtextRechSNom.setPreferredSize(new Dimension(120, 30));
        JTextField jtextRechPrenom = new JTextField("Prenom");
        jtextRechPrenom.setPreferredSize(new Dimension(120, 30));
        JButton jButtonRecherche = new JButton("Search");
        JButton jButtonRetour = new JButton("Retour");

        panelinfoSrecherche.add(jtextRechSNom);
        panelinfoSrecherche.add(jtextRechPrenom);
        panelinfoSrecherche.add(jButtonRecherche);
        panelinfoSrecherche.add(jButtonRetour, BorderLayout.WEST);

        jButtonRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[0]);
                //cardLayout.show(panelprincipal, listContent[0]);
            }
        });

        jButtonRecherche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String Name = jtextRechSNom.getText();
                String FirstName = jtextRechPrenom.getText();
                    /*
                    for(int i = 0; i<listeEleve.size() ; i++ ) {
                        if( Name.equals(listEleve[i].name) ){

                            /////// Fenetre qui va s'executer lorsque l'on va enclencher la rechercher d'un etudiant, Je vois bien un truc genre parcourir tt le liste
                            /////// d'étudiant et la mettre dans une nouvelle liste car possibilite de doublons et par la suite afficher tt cela
                               cardLayoutPromo.show(panelAfficherS2, listStudent[1]);

                        }
                    }

                    else(){
                   JOptionPane.showMessageDialog(contentpane, status," This eleve doesn't exist", JOptionPane.WARNING_MESSAGE);
                    }
                    */
            }

        });
        return panelinfoSrecherche;
    }
    private JPanel panelinfoS() {


        panelInfoS = new JPanel(new GridLayout(1, 6));
        panelInfoS.setPreferredSize(new Dimension(0, 50));

        JLabel jLabel_Lundi = new JLabel("Nom", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Lundi);
        JLabel jLabel_Mardi = new JLabel("Prenom", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Mardi);
        JLabel jLabel_Mercredi = new JLabel("Groupe", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Mercredi);
        JLabel jLabel_Jeudi = new JLabel("Promo", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Jeudi);
        JLabel jLabel_Vendredi = new JLabel("Note", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Vendredi);
        JLabel jLabel_Samedi = new JLabel("Absence", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Samedi);

        return panelInfoS;

    }
    //private JPanel panelListeStudent(list listeTtEleves){
    private JPanel panelListeStudent(int nbEleve) {

        panelListeStudent = new JPanel(new GridLayout(nbEleve, 1));//remplacer 15 par n etudiants
        for (int i = 1; i <= nbEleve; i++) {
            panelListeStudent.add(new JLabel(" Francois", SwingConstants.CENTER));//
            //panelListeStudent.add(new JLabel(listeTtEleves[i].getPrenom, SwingConstants.CENTER));
            panelListeStudent.add(new JLabel(" Chevalier", SwingConstants.CENTER));
            panelListeStudent.add(new JLabel(" 2C", SwingConstants.CENTER));
            panelListeStudent.add(new JLabel(" ING 3", SwingConstants.CENTER));
            panelListeStudent.add(new JLabel(" 20", SwingConstants.CENTER));
            panelListeStudent.add(new JLabel(" 0", SwingConstants.CENTER));

        }

        return panelListeStudent;
    }

/////////////////////////////
    private JPanel panelAfficherR() {

        panelAfficherRoom = new JPanel();
        panelAfficherRoom.setLayout(new BorderLayout());
        panelAfficherRoom.add(panelinfoRoom(), BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListeRoom());
        jScrollPaneS.setPreferredSize(new Dimension(0, 70));
        panelAfficherRoom.add(jScrollPaneS, BorderLayout.CENTER);

        return panelAfficherRoom;
    }

    private JPanel panelinfoRoom() {

        panelinfoRoom = new JPanel();
        panelinfoRoom.setLayout(new BorderLayout());
        panelinfoRoom.add(panelinfoRrecherche(), BorderLayout.NORTH);
        panelinfoRoom.add(panelinfoR(), BorderLayout.CENTER);

        return panelinfoRoom;

    }
    private JPanel panelinfoRrecherche() {
        panelinfoRrecherche = new JPanel();
        JTextField jtextRechRoom = new JTextField("Number");
        jtextRechRoom.setPreferredSize(new Dimension(120, 30));
        JButton jButtonRecherche = new JButton("Search");

        panelinfoRrecherche.add(jtextRechRoom);//---------------------Ajouter listner
        panelinfoRrecherche.add(jButtonRecherche);//---------------------Ajouter listner

        return panelinfoRrecherche;
    }
    private JPanel panelinfoR() {

        panelinfoR = new JPanel(new GridLayout(1, 6));
        panelinfoR.setPreferredSize(new Dimension(0, 50));

        JLabel jLabelRoomStage = new JLabel("Stage", SwingConstants.CENTER);
        panelinfoR.add(jLabelRoomStage);
        JLabel jLabelRnumber = new JLabel("Number", SwingConstants.CENTER);
        panelinfoR.add(jLabelRnumber);
        JLabel jLabelRetat = new JLabel("Etat", SwingConstants.CENTER);
        panelinfoR.add(jLabelRetat);

        return panelinfoR;

    }
    private JPanel panelListeRoom() {

        panelListeRoom = new JPanel(new GridLayout(50, 1));//remplacer 15 par n etudiants
        //-------------------------------------------- recuperer le nombre d'eleves (dans un tableau ou jsp quoi)
        for (int i = 1; i <= 50; i++) {
            panelListeRoom.add(new JLabel(" 4", SwingConstants.CENTER));//Ajouter fonction string avec tt les infos de l'etudiant
            panelListeRoom.add(new JLabel(" i404", SwingConstants.CENTER));
            panelListeRoom.add(new JLabel(" Free", SwingConstants.CENTER));


        }

        return panelListeRoom;
    }

/////////////////////////////

    private JPanel panelAfficherP() {

        cardLayoutPromo = new CardLayout();
        panelAfficherPromo = new JPanel();
        panelAfficherPromo.setLayout(cardLayoutPromo);
        panelAfficherPromo.add(panelListePromo(), listPromo[0]);
        panelAfficherPromo.add(panelpromo2(100), listPromo[1]);///////////////____________Taille promo ++++++++++++ List listrpomo ING1
        panelAfficherPromo.add(panelpromo2(20), listPromo[2]);///////////////____________Taille promo ++++++++++++ List listrpomo ING2
        panelAfficherPromo.add(panelpromo2(20), listPromo[3]);///////////////____________Taille promo ++++++++++++ List listrpomo ING3
        panelAfficherPromo.add(panelpromo2(20), listPromo[4]);///////////////____________Taille promo ++++++++++++ List listrpomo ING4
        panelAfficherPromo.add(panelpromo2(20), listPromo[5]);///////////////____________Taille promo ++++++++++++ List listrpomo ING5
        //panelAfficherPromo.add(panelListeEleveP(20,ListEleveING5), listPromo[5]);///////////////____________Taille promo ++++++++++++ List listrpomo ING5

        return panelAfficherPromo;
    }

    private JPanel panelpromo2(int TaillePromo) {

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(panelInfoStudent(), BorderLayout.NORTH);
        JScrollPane jScrollPaneS = new JScrollPane(panelListeEleveP(TaillePromo));
        jScrollPaneS.setPreferredSize(new Dimension(0, 70));
        jpanel.add(jScrollPaneS, BorderLayout.CENTER);

        return jpanel;
    }
    //private JPanel panelListeEleveP(int TaillePromo, List listepromo){
    private JPanel panelListeEleveP(int TaillePromo) {

        panelListeEleveP = new JPanel();
        panelListeEleveP.add(panelinfoS(), BorderLayout.NORTH);
        panelListeEleveP.add(panelListeStudent(TaillePromo), BorderLayout.CENTER, SwingConstants.CENTER);////////////////--------Ajouter une liste des eleves en parametre

        return panelListeEleveP;
    }
    private JPanel panelListePromo() {

        JPanel jPanelfinal = new JPanel();

        panelListePromo = new JPanel(new GridLayout(5, 1));
        JButton PromoING1 = new JButton("ING 1");
        PromoING1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[1]);

                //panelListeEleveP(20);//////Taille promo ing1 +++++++ liste des eleves ing1
            }
        });
        JButton PromoING2 = new JButton("ING 2");
        PromoING2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //panelListeEleveP(20);//////Taille promo ing2 +++++++ liste des eleves ing2
                cardLayoutPromo.show(panelAfficherPromo, listPromo[2]);

            }
        });
        JButton PromoING3 = new JButton("ING 3");
        PromoING3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[3]);

                //panelListeEleveP(20);//////Taille promo ing3 +++++++ liste des eleves ing3
            }
        });
        JButton PromoING4 = new JButton("ING 4");
        PromoING4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[4]);
                //panelListeEleveP(20);//////Taille promo ing4 +++++++ liste des eleves ing4
            }
        });
        JButton PromoING5 = new JButton("ING 5");
        PromoING4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[5]);
                //panelListeEleveP(20);//////Taille promo ing5 +++++++ liste des eleves ing5
            }
        });


        panelListePromo.add(PromoING1);
        panelListePromo.add(PromoING2);
        panelListePromo.add(PromoING3);
        panelListePromo.add(PromoING4);
        panelListePromo.add(PromoING5);

        return panelListePromo;
    }


/////////////////////////////

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        ViewTeacher frame = new ViewTeacher();
        frame.setVisible(true);
    }
}