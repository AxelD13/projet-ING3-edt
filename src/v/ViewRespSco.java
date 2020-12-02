package v;
import c.Database;
import m.dao.DAO;
import m.dao.UserDAO;
import m.user.EnumPermission;
import m.user.User;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

public class ViewRespSco extends JFrame {
    private final Database db;
    private final Connection cnx;

    private final String[] promos = {"1e année", "2e année", "3e année", "4e année", "5e année"};//different groupe que l'on auar avec groupe.nom
    private final String[] groups = {"1A", "1B", "2A", "2B", "3A", "3B"};//different groupe que l'on auar avec groupe.nom
    private final String[] startTime = {"8H00", "9H30", "11H00", "12H30", "14H00","15H30","17H00","18H30"};
    private final String[] endTime = {"9H30", "11H00", "12H30", "14H00","15H30","17H00","18H30","20H00"};
    private final String[] slots = {"8H00 - 9H30", "9H30 - 11H00", "11H00 - 12H30", "12H30 - 14H00", "14H00 - 15H30", "15H30 - 17H00", "18H30 - 20H00"};
    private final String[] courses = {"Maths", "Physique", "Physique Appliquée", "Informatique","LV1","LV2","Analyse financiere"};
    private final String[] days = {"Lundi", "Mardi", "Mercredi","Jeudi","Vendredi","Samedi"};
    private String[] listContent = {"EDT", "AfficherE", "AfficherT", "AfficherR", "AfficherP", "AfficherSR"};
    private String[] listPromo = {"0", "1", "2", "3", "4", "5"};
    private String[] listTeacher = {"0", "1"};
    private String[] listStudent = {"0", "1"};
    private String[] listDeleteCours = {"0", "1","2","3","4","5"};
    private CardLayout cardLayout, cardLayoutPromo, cardLayoutTeacher,cardLayoutStudent, cardLayoutDeleteCours;
    private JPanel panelmenu, panelprincipal, panelJoursSemaine, panelEdt, panelAfficherStudent, panelInfoStudent, panelInfoS, panelinfoSrecherche, panelListeStudent,
            panelAfficherTeacher, panelinfoTeacher, panelListeTeacher, panelAfficherRoom, panelinfoRoom, panelListeRoom, panelAfficherPromo, panelListePromo, panelinfoTrecherche, panelinfoT, panelinfoRrecherche,
            panelinfoR, panelListeEleveP, panelPrincipalFinal;


    /* Construction de l'interface graphique */
    public ViewRespSco(Database db, Connection cnx) {
        super( "Mon emploi du temps" );
        this.db = db;
        this.cnx = cnx;
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
        panelprincipal.add(panelAfficherS(), listContent[1]);
        panelprincipal.add(panelAfficherT(), listContent[2]);
        panelprincipal.add(panelAfficherR(), listContent[3]);
        panelprincipal.add(panelAfficherP(), listContent[4]);

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
        //mnuDisplay.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));// Ajouter de la disantce entre les boutons


        JMenuItem mnuStudent = new JMenuItem("Student list");
        mnuStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[1]);
            }
        });
        mnuDisplay.add(mnuStudent);

        JMenuItem mnuTeach = new JMenuItem("Teacher list");
        mnuTeach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[2]);
            }
        });
        mnuDisplay.add(mnuTeach);

        JMenuItem mnuPromos = new JMenuItem("Promos list");
        mnuPromos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[4]);
            }
        });
        mnuDisplay.add(mnuPromos);

        mnuDisplay.addSeparator();

        JMenuItem mnufreerooms = new JMenuItem("Free rooms");
        mnufreerooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[3]);
            }
        });
        mnuDisplay.add(mnufreerooms);

        mnuDisplay.addSeparator();

        JMenuItem mnuExit = new JMenuItem("Exit");
        mnuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[0]);
                cardLayout.show(panelprincipal, listContent[0]);
            }
        });
        mnuDisplay.add(mnuExit);

        menuBar.add(mnuDisplay);

        // Définition du menu déroulant "Ajouter" et de son contenu
        JMenu mnuEdit = new JMenu("Add");

        JMenuItem mnuAddTeacher = new JMenuItem("Teacher");
        mnuAddTeacher.addActionListener(this::ListnerAddTeacher);
        mnuEdit.add(mnuAddTeacher);

        JMenuItem mnuAddStudent = new JMenuItem("Student");
        mnuAddStudent.addActionListener(this::ListnerAddStudent);
        mnuEdit.add(mnuAddStudent);

        mnuEdit.addSeparator();

        JMenuItem mnuAddCours = new JMenuItem("Cours");
        mnuAddCours.addActionListener(this::ListnerAddCours);
        mnuEdit.add(mnuAddCours);

        mnuEdit.addSeparator();

        JMenuItem mnuAddMatiere = new JMenuItem("Matiere");
        //mnuAddMatiere.addActionListener(this::ListnerAddCours);
        mnuEdit.add(mnuAddMatiere);

        menuBar.add(mnuEdit);

        // Définition du menu déroulant "Delete" et de son contenu
        JMenu mnuDelete = new JMenu("Delete");

        JMenuItem mnuDeleteTeacher = new JMenuItem("Teacher");
        mnuDelete.add(mnuDeleteTeacher);


        JMenuItem mnuDeleteStudent = new JMenuItem("Student");
        mnuDelete.add(mnuDeleteStudent);

        mnuDelete.addSeparator();

        JMenuItem mnuDeleteClasses = new JMenuItem("Classes");
        mnuDelete.add(mnuDeleteClasses);

        menuBar.add(mnuDelete);

        return menuBar;
    }
    /* Construction et injection de la barre de menu*/
    private JPanel panelmenu() {
        panelmenu = new JPanel();
        this.setJMenuBar(this.createMenuBar());
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
        jPanel.setBackground(new Color(255, 255, 255));

        for(String creneau : slots) {
            JLabel jlabel_creneau= new JLabel(creneau, SwingConstants.CENTER);
            jPanel.add(jlabel_creneau);
        }

        return jPanel;
    }
    /* Methode de construction des jours de la semaines*/
    private  JPanel panJours(){
        JPanel jPanel1 = new JPanel( new GridLayout(1,7));
        JLabel jLabel_jours= new JLabel("Horaires / Jours", SwingConstants.CENTER);
        jPanel1.add(jLabel_jours);

        for(String jour : days) {
            JLabel jLabel_jour= new JLabel(jour, SwingConstants.CENTER);
            jPanel1.add(jLabel_jour);
        }

        return jPanel1;
    }
    /* Methode de construction des boutons semaines*/
    private JPanel panSemaine(){
        JPanel jPanel = new JPanel(new GridLayout(1,30));
        jPanel.setBackground(new Color(255, 255, 255));
        for(int i = 1; i<30;i++ ){
            jPanel.add( new JButton(String.valueOf(i)));
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

    private JPanel panelAfficherT() {

        cardLayoutTeacher = new CardLayout();
        panelAfficherTeacher = new JPanel();
        panelAfficherTeacher.setLayout(cardLayoutTeacher);
        panelAfficherTeacher.add(panelAfficherT2(), listTeacher[0]);
        //panelAfficherTeacher.add(panelAfficherT2(prof1), listTeacher[0]);//prof1 Fonction qui retourne tt les profs
        //panelAfficherTeacher.add(panelAfficherT2(prof2), listTeacher[1]);// prof2 fonction qui retourne une liste de prof en fonction du nom
        return panelAfficherTeacher;
    }

    //private JPanel panelAfficherT2(list Deprof) {
    private JPanel panelAfficherT2() {

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(panelinfoTeacher(), BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListeT());//-- ajouter la liste
        jScrollPaneS.setPreferredSize(new Dimension(0, 70));
        jpanel.add(jScrollPaneS, BorderLayout.CENTER);

        return jpanel;
    }
    private JPanel panelinfoTeacher() {

        panelinfoTeacher = new JPanel();
        panelinfoTeacher.setLayout(new BorderLayout());
        panelinfoTeacher.add(panelinfoTrecherche(), BorderLayout.NORTH);
        panelinfoTeacher.add(panelinfoT(), BorderLayout.CENTER);

        return panelinfoTeacher;
    }
    //private JPanel panelinfoTrecherche(list listeTeacher){
    private JPanel panelinfoTrecherche() {
        panelinfoTrecherche = new JPanel();
        JTextField jtextRechNom = new JTextField("Nom");
        jtextRechNom.setPreferredSize(new Dimension(120, 30));
        JTextField jtextRechMat = new JTextField("Matiere");
        jtextRechMat.setPreferredSize(new Dimension(120, 30));
        JButton jButtonRecherche = new JButton("Search");
        JButton jButtonReturn = new JButton("Return");
        panelinfoTrecherche.add(jtextRechNom);
        panelinfoTrecherche.add(jtextRechMat);
        panelinfoTrecherche.add(jButtonRecherche);
        panelinfoTrecherche.add(jButtonReturn);

        String Name = jtextRechNom.getText();
        String Matiere = jtextRechMat.getText();

        jButtonRecherche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                /*
                for(int i = 0; i<listeEleve.size() ; i++ ) {
                    if( Name.equals(listprof[i].name) ){


                        /////// Fenetre qui va s'executer lorsque l'on va enclencher la rechercher d'un etudiant, Je vois bien un truc genre parcourir tt le liste
                        /////// d'étudiant et la mettre dans une nouvelle liste car possibilite de doublons et par la suite afficher tt cela
                        cardLayout.show(panelAfficherTeacher, listTeacher[1]);

                    }
                    if( Matiere.equals(listProf[i].matiere) ){
                        cardLayout.show(panelAfficherTeacher, listTeacher[1]);}

                    else(){
                        JOptionPane.showMessageDialog(contentpane, status," This teacher doesn't exist", JOptionPane.WARNING_MESSAGE);
                    }
                    */
            }

        });

        jButtonReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelAfficherTeacher, listTeacher[0]);
            }
        });
        return panelinfoTrecherche;
    }
    private JPanel panelinfoT() {

        panelinfoT = new JPanel(new GridLayout(1, 6));
        panelinfoT.setPreferredSize(new Dimension(0, 50));
        JLabel jLabelTfirstName = new JLabel("Nom", SwingConstants.CENTER);
        panelinfoT.add(jLabelTfirstName);
        JLabel jLabelTname = new JLabel("Prenom", SwingConstants.CENTER);
        panelinfoT.add(jLabelTname);
        JLabel jLabelTmatiere = new JLabel("Matiere", SwingConstants.CENTER);
        panelinfoT.add(jLabelTmatiere);
        JLabel jLabelTnbHours = new JLabel("Nombre Heure de cours", SwingConstants.CENTER);
        panelinfoT.add(jLabelTnbHours);
        return panelinfoT;

    }
    //private JPanel panelListeT(list listProf){
    private JPanel panelListeT() {

        panelListeTeacher = new JPanel(new GridLayout(25, 1));//Taille de la liste remplacer par 25
        for (int i = 1; i <= 25; i++) {
            panelListeTeacher.add(new JLabel("Amira", SwingConstants.CENTER));
            //panelListeTeacher.add(new JLabel(ListProf[i].getNom, SwingConstants.CENTER));
            panelListeTeacher.add(new JLabel("dedecker", SwingConstants.CENTER));
            panelListeTeacher.add(new JLabel("physique", SwingConstants.CENTER));
            panelListeTeacher.add(new JLabel("200h", SwingConstants.CENTER));
        }

        return panelListeTeacher;
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
            panelListeRoom.add(new JLabel(" Libre", SwingConstants.CENTER));


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

    public void ListnerAddTeacher(ActionEvent event) {
        create_frameAddTeacher();
    }
    public void ListnerAddCours(ActionEvent event) {
        create_frameAddCours();
    }
    public void ListnerAddStudent(ActionEvent event) {
        create_frameAddStudent();
    }

    // JPanel 'Ajout d'un cours'
    public JFrame create_frameAddCours() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(6, 2));

        JLabel jlabelNomClasse = new JLabel("Nom :");
        JTextField jtfNomClasse = new JTextField(10);

        JLabel jlabelGroupeClasse = new JLabel("Groupe :");
        JComboBox jcmbGroupeClasse = new JComboBox<String>(groups);//different groupe que l'on auar avec groupe.nom

        JLabel jlabelMatiere = new JLabel("Matiere :");
        JComboBox jcmbMatiereCours = new JComboBox<String>(courses);

        JLabel jlabelHeureDebut = new JLabel("Heure de debut :");
        JComboBox jcmHeureDebut = new JComboBox<String>(startTime);

        JLabel jlabelHeureFin = new JLabel("Heure de Fin :");//a modifier pour ajouter une horloge
        JComboBox jcmHeureFin = new JComboBox<String>(endTime);


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

        JPanel jpanel2 = new JPanel();
        JButton jButonEnregistre = new JButton("ENREGISTRE");
        jButonEnregistre.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                System.out.print(jtfNomClasse.getText());
                System.out.print(jcmbGroupeClasse.getSelectedItem());
                System.out.print(jcmbMatiereCours.getSelectedItem());
                System.out.print(jcmHeureDebut.getSelectedItem());
                System.out.print(jcmHeureFin.getSelectedItem());
                JOptionPane.showMessageDialog(jPanel,"Choice register");

            }
        });

        jpanel2.add(jButonEnregistre);


        JFrame frameAjoutClasse = new JFrame();
        frameAjoutClasse.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        frameAjoutClasse.setSize(400,300);
        frameAjoutClasse.setTitle("Ajout d'un cours");
        frameAjoutClasse.setLocationRelativeTo(null);
        frameAjoutClasse.setResizable(false);
        frameAjoutClasse.setVisible(true);
        frameAjoutClasse.getContentPane().add(jPanel, BorderLayout.CENTER);
        frameAjoutClasse.getContentPane().add(jpanel2, BorderLayout.SOUTH);

        frameAjoutClasse.setVisible(true);

        return frameAjoutClasse;
    }
    /* JPanel 'Ajout d'un professeur' */
    public JFrame create_frameAddTeacher() {
        JPanel jpanelAddTeacher = new JPanel();
        jpanelAddTeacher.setLayout(new GridLayout(5,2));

        JLabel jlabelTeacherFirstName = new JLabel("Prénom :");
        JTextField jtfTeacherFirstName = new JTextField(10);

        JLabel jlabelTeacherLastName = new JLabel("Nom :");
        JTextField jtfTeacherLastName = new JTextField(10);

        JLabel jlabelTeacherEmail = new JLabel("Email :");
        JTextField jtfTeacherEmail = new JTextField(10);

        JLabel jlabelTeacherCourse = new JLabel("Matière :");
        JComboBox jcmbTeacherCourse = new JComboBox<String>(courses);

        JButton jButtonAddProf = new JButton("ENREGISTRER");
        jButtonAddProf.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                DAO<User> userDao = new UserDAO(cnx);
                User newUser = new User(jtfTeacherEmail.getText(), "mdp123",
                        jtfTeacherFirstName.getText(), jtfTeacherLastName.getText(), EnumPermission.TEACHER);

                if(userDao.create(newUser)) {
                    //fermer la fenêtre
                }
            }
        });

        jpanelAddTeacher.add(jlabelTeacherFirstName);
        jpanelAddTeacher.add(jtfTeacherFirstName);
        jpanelAddTeacher.add(jlabelTeacherLastName);
        jpanelAddTeacher.add(jtfTeacherLastName);
        jpanelAddTeacher.add(jlabelTeacherEmail);
        jpanelAddTeacher.add(jtfTeacherEmail);
        jpanelAddTeacher.add(jlabelTeacherCourse);
        jpanelAddTeacher.add(jcmbTeacherCourse);
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
        JPanel jpanelAddStudent = new JPanel();
        jpanelAddStudent.setLayout(new GridLayout(5,3));

        JLabel jlabelStudentFirstName = new JLabel("Prénom :");
        JTextField jtfStudentFirstName = new JTextField(10);

        JLabel jlabelStudentLastName = new JLabel("Nom :");
        JTextField jtfStudentLastName = new JTextField(10);

        JLabel jlabelStudentPromo = new JLabel("Promotion :");
        JComboBox jcmbStudentPromo = new JComboBox<String>(promos);//recuperer les differentes promos

        JLabel jlabelStudentGroup = new JLabel("Classe :");
        JComboBox jcmbStudentGroup = new JComboBox<String>(groups);//recuperer les groupes sur mysql

        JButton jButtonAddStudent = new JButton("ENREGISTRER");
        jButtonAddStudent.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                DAO<User> userDao = new UserDAO(cnx);
                User newUser = new User("nvleleve@edu.ece.fr", "mdp123",
                        jtfStudentLastName.getText(), jtfStudentFirstName.getText(), EnumPermission.STUDENT);

                if(userDao.create(newUser)) {
                    //fermer la fenêtre
                }
            }
        });

        jpanelAddStudent.add(jlabelStudentFirstName);
        jpanelAddStudent.add(jtfStudentFirstName);
        jpanelAddStudent.add(jlabelStudentLastName);
        jpanelAddStudent.add(jtfStudentLastName);
        jpanelAddStudent.add(jlabelStudentPromo);
        jpanelAddStudent.add(jcmbStudentPromo);
        jpanelAddStudent.add(jlabelStudentGroup);
        jpanelAddStudent.add(jcmbStudentGroup);
        jpanelAddStudent.add(jButtonAddStudent);

        JFrame jframeAddEleve = new JFrame();
        jframeAddEleve.setSize(400,300);
        jframeAddEleve.setTitle("Ajout d'un élève");
        jframeAddEleve.setLocationRelativeTo(null);
        jframeAddEleve.setResizable(false);
        jframeAddEleve.setVisible(true);
        jframeAddEleve.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        jframeAddEleve.getContentPane().add(jpanelAddStudent, BorderLayout.CENTER);
        jframeAddEleve.setVisible(true);

        return jframeAddEleve;
    }



/////////////////////////////

    public JFrame DeleteframeCours(ArrayList coursIng1, ArrayList coursIng2, ArrayList coursIng3, ArrayList coursIng4, ArrayList coursIng5) {

        JFrame frameDeleteCours = new JFrame();
        frameDeleteCours.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameDeleteCours.setSize(800, 600);
        frameDeleteCours.setTitle("Delete Cours");
        frameDeleteCours.setLocationRelativeTo(null);
        frameDeleteCours.setResizable(false);
        frameDeleteCours.setVisible(true);
        frameDeleteCours.getContentPane().add(DeleteCours0(coursIng1,coursIng2,coursIng3,coursIng4,coursIng5));
        frameDeleteCours.setVisible(true);

        return frameDeleteCours;
    }

    private JPanel DeleteCours0(ArrayList coursIng1, ArrayList coursIng2,ArrayList coursIng3,ArrayList coursIng4, ArrayList coursIng5){
        // il faut sans doute mettre tt les listes de tt les cours

        cardLayoutDeleteCours = new CardLayout();
        panelPrincipalFinal = new JPanel();
        panelPrincipalFinal.setLayout(cardLayoutDeleteCours);
        panelPrincipalFinal.add(DeleteCours1(), listDeleteCours[0]);
        panelPrincipalFinal.add(DeleteCours2(coursIng1), listDeleteCours[1]);
        panelPrincipalFinal.add(DeleteCours2(coursIng2), listDeleteCours[2]);
        panelPrincipalFinal.add(DeleteCours2(coursIng3), listDeleteCours[3]);
        panelPrincipalFinal.add(DeleteCours2(coursIng4), listDeleteCours[4]);
        panelPrincipalFinal.add(DeleteCours2(coursIng5), listDeleteCours[5]);

        return panelPrincipalFinal;
    }

    private JPanel DeleteCours2(ArrayList cours){
        JPanel jpanel = new JPanel(new GridLayout(cours.size(),1));
        for (int i = 1; i <= cours.size(); i++) {
            jpanel.add(new JButton(" Maths"));
            //panelListeStudent.add(new JLabel(listeTtEleves[i].getPrenom, SwingConstants.CENTER));
            jpanel.add(new JLabel(" Dedecker", SwingConstants.CENTER));
            jpanel.add(new JLabel(" ING 3", SwingConstants.CENTER));
            jpanel.add(new JLabel(" 9h30", SwingConstants.CENTER));
            jpanel.add(new JLabel(" 11h", SwingConstants.CENTER));
            jpanel.add(new JLabel(" 22/12/00", SwingConstants.CENTER));
            JButton JboutonChoice = new JButton(" Choice");
            jpanel.add(JboutonChoice);
            JboutonChoice.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //Supprimer de la base de donnée alors
                    JOptionPane.showMessageDialog(jpanel, "Choice register");

                }
            });

        }
        return jpanel;

    }

    private JPanel DeleteCours1(){

        JPanel jPanelNorth = new JPanel();
        JLabel jlabel= new JLabel("In wich promotion would you delete cours ?");
        jPanelNorth.add(jlabel);
        JPanel jPanelPromo= new JPanel(new GridLayout(5, 1));
        JButton PromoING1 = new JButton("ING 1");
        JButton PromoING2 = new JButton("ING 2");
        JButton PromoING3 = new JButton("ING 3");
        JButton PromoING4 = new JButton("ING 4");
        JButton PromoING5 = new JButton("ING 5");
        jPanelPromo.add((PromoING1));
        jPanelPromo.add((PromoING2));
        jPanelPromo.add((PromoING3));
        jPanelPromo.add((PromoING4));
        jPanelPromo.add((PromoING5));
        JPanel panelfinal = new JPanel();
        panelfinal.add(jPanelNorth,BorderLayout.NORTH);
        panelfinal.add(jPanelPromo,BorderLayout.CENTER);


        PromoING1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutDeleteCours.show(panelPrincipalFinal, listDeleteCours[1]);
            }
        });
        PromoING2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutDeleteCours.show(panelPrincipalFinal, listDeleteCours[2]);

            }
        });
        PromoING3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutDeleteCours.show(panelPrincipalFinal, listDeleteCours[3]);

            }
        });
        PromoING4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutDeleteCours.show(panelPrincipalFinal, listDeleteCours[4]);

            }
        });
        PromoING5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutDeleteCours.show(panelPrincipalFinal, listDeleteCours[5]);

            }
        });

        return panelfinal;

    }

/////////////////////////////

    public JFrame DeleteframeTeachers(ArrayList listTeachers){

        JFrame frameDeleteTeachers = new JFrame();
        frameDeleteTeachers.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameDeleteTeachers.setSize(800, 600);
        frameDeleteTeachers.setTitle("Delete Teachers");
        frameDeleteTeachers.setLocationRelativeTo(null);
        frameDeleteTeachers.setResizable(false);
        frameDeleteTeachers.setVisible(true);
        frameDeleteTeachers.getContentPane().add(DeleteTeachers(listTeachers));
        frameDeleteTeachers.setVisible(true);

        return frameDeleteTeachers;
    }

    private JPanel DeleteTeachers(ArrayList Teachers){
        JPanel jpanel = new JPanel(new GridLayout(Teachers.size(),1));
        for (int i = 1; i <= Teachers.size(); i++) {
            jpanel.add(new JButton(" Maths"));
            //panelListeStudent.add(new JLabel(listeTtEleves[i].getPrenom, SwingConstants.CENTER));
            jpanel.add(new JLabel(" Dedecker", SwingConstants.CENTER));
            JButton JboutonChoice = new JButton(" Choice");
            jpanel.add(JboutonChoice);
            JboutonChoice.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //Supprimer de la base de donnée alors
                    JOptionPane.showMessageDialog(jpanel, "Choice register");

                }
            });

        }
        return jpanel;

    }

/////////////////////////////






    public static void main(String[] args) throws Exception {
        Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
        Connection cnx = db.connectDB();
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        ViewRespSco frame = new ViewRespSco(db, cnx);
        frame.setVisible(true);
    }
}