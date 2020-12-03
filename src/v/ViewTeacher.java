package v;
import c.Database;
import m.Room;
import m.Student;
import m.dao.DAO;
import m.dao.RoomDAO;
import m.dao.StudentDAO;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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

    Database db = new Database("jdbc:mysql://localhost:8889/projet_edt", "root", "root");
    Connection cnx = db.connectDB();
    DAO<Room> RoomDAO = new RoomDAO(cnx);
    List<Room> listRoom = RoomDAO.getAll();
    DAO<Student> studentDAO = new StudentDAO(cnx);
    List<Student> listStudents = studentDAO.getAll();


    /* Construction de l'interface graphique */
    public ViewTeacher(Database db, Connection cnx, int idUser) {
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
        JMenu mnuDisplay = new JMenu("Géneral");


        JMenuItem mnuStudent = new JMenuItem("Liste des etudiants");
        mnuStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[1]);
            }
        });
        mnuDisplay.add(mnuStudent);

        JMenuItem mnuPromos = new JMenuItem("Promos");
        mnuPromos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[3]);
            }
        });
        mnuDisplay.add(mnuPromos);

        mnuDisplay.addSeparator();

        JMenuItem mnufreerooms = new JMenuItem("Salles");
        mnufreerooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[2]);
            }
        });
        mnuDisplay.add(mnufreerooms);

        mnuDisplay.addSeparator();

        JMenuItem mnuExit = new JMenuItem("Sortie");
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
    return panelAfficherStudent;
}
    private JPanel panelAfficherS2() {

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(panelInfoStudent(), BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListeStudent(listStudents));///////////////////NB eleve----------------et ajouter liste des studient
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
    private JPanel panelinfoSrecherche() {
        panelinfoSrecherche = new JPanel();
        JTextField jtextRechSNom = new JTextField("Prenom");
        jtextRechSNom.setPreferredSize(new Dimension(120, 30));
        JTextField jtextRechPrenom = new JTextField("Nom");
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

                for (Student student : listStudents) {
                    if (Name.equals(student.getFirstName())) {
                        if (FirstName.equals(student.getLastName())) {
                            sRecherche(student);
                        }

                        else {
                            JOptionPane.showMessageDialog(panelinfoSrecherche, "IL n'existe pas d'élève "+ Name +" "+ FirstName );
                        }
                    }
                }
            }
        });

        return panelinfoSrecherche;

    }
    private JFrame sRecherche(Student student){

        JPanel jPanel = new JPanel(new GridLayout(4,1));
        jPanel.add(new JLabel("Prénom de L'élève : "+student.getFirstName(), SwingConstants.CENTER));
        jPanel.add(new JLabel("Nom de l'élève : "+student.getLastName(), SwingConstants.CENTER));
        jPanel.add(new JLabel("Groupe n° "+ String.valueOf(student.getIdGroupPromotion()), SwingConstants.CENTER));
        jPanel.add(new JLabel( "Mail :"+ student.getEmail(), SwingConstants.CENTER));

        JFrame frameSrecherche = new JFrame();
        frameSrecherche.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameSrecherche.setSize(400, 200);
        frameSrecherche.setTitle("Liste eleve");
        frameSrecherche.setLocationRelativeTo(null);
        frameSrecherche.setResizable(false);
        frameSrecherche.getContentPane().add(jPanel, BorderLayout.CENTER);
        frameSrecherche.setVisible(true);

        return frameSrecherche;

    }
    private JPanel panelinfoS() {


        panelInfoS = new JPanel(new GridLayout(1, 6));
        panelInfoS.setPreferredSize(new Dimension(0, 50));

        JLabel jLabel_Lundi = new JLabel("Nom", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Lundi);
        JLabel jLabel_Mardi = new JLabel("Prenom", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Mardi);
        JLabel jLabel_Mercredi = new JLabel("Groupe Promotion", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Mercredi);
        JLabel jLabel_Jeudi = new JLabel("Email", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Jeudi);

        return panelInfoS;

    }
    private JPanel panelListeStudent(List<Student> listStudents) {

        panelListeStudent = new JPanel(new GridLayout(listStudents.size(), 1));
        for (Student student : listStudents) {
            panelListeStudent.add(new JLabel(student.getFirstName(), SwingConstants.CENTER));
            panelListeStudent.add(new JLabel(student.getLastName(), SwingConstants.CENTER));
            panelListeStudent.add(new JLabel(String.valueOf(student.getIdGroupPromotion()), SwingConstants.CENTER));
            panelListeStudent.add(new JLabel(student.getEmail(), SwingConstants.CENTER));
        }

        return panelListeStudent;
    }

/////////////////////////////

    private JPanel panelAfficherR() {

        panelAfficherRoom = new JPanel();
        panelAfficherRoom.setLayout(new BorderLayout());
        panelAfficherRoom.add(panelinfoRoom(), BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListeRoom(listRoom));
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
        JTextField jtextRechRoom = new JTextField("Numero");
        jtextRechRoom.setPreferredSize(new Dimension(120, 30));
        JButton jButtonRecherche = new JButton("Recherche");
        panelinfoRrecherche.add(jtextRechRoom);
        panelinfoRrecherche.add(jButtonRecherche);

        jButtonRecherche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String Numero = jtextRechRoom.getText();
                for (Room room : listRoom) {
                    if (room.getName().equals(Numero)) {
                        Rrecherche(room);}

                    else JOptionPane.showMessageDialog(panelinfoSrecherche, "La salle n° "+ Numero +" n'existe pas" );
                }
            }
        });
        return panelinfoRrecherche;
    }
    private JFrame Rrecherche(Room room){
        String site;
        JPanel jPanel = new JPanel(new GridLayout(3,1));
        jPanel.add(new JLabel("Numero de salle: "+room.getName(), SwingConstants.CENTER));
        jPanel.add(new JLabel("Capcaité salle max : "+room.getCapacity(), SwingConstants.CENTER));
        if(room.getIdSite()==1){
            site = "Paris";
        }
        else{
            site = "Lyon";
        }
        jPanel.add(new JLabel("site : "+ site, SwingConstants.CENTER));

        JFrame frameSrecherche = new JFrame();
        frameSrecherche.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameSrecherche.setSize(400, 200);
        frameSrecherche.setTitle("Recherche Professeur");
        frameSrecherche.setLocationRelativeTo(null);
        frameSrecherche.setResizable(false);
        frameSrecherche.getContentPane().add(jPanel, BorderLayout.CENTER);
        frameSrecherche.setVisible(true);

        return frameSrecherche;

    }
    private JPanel panelinfoR() {

        panelinfoR = new JPanel(new GridLayout(1, 6));
        panelinfoR.setPreferredSize(new Dimension(0, 50));

        JLabel jLabelRoomStage = new JLabel("Numero", SwingConstants.CENTER);
        panelinfoR.add(jLabelRoomStage);
        JLabel jLabelRnumber = new JLabel("Capacité", SwingConstants.CENTER);
        panelinfoR.add(jLabelRnumber);
        JLabel jLabelRetat = new JLabel("Site", SwingConstants.CENTER);
        panelinfoR.add(jLabelRetat);

        return panelinfoR;

    }
    private JPanel panelListeRoom(List<Room> listRoom) {
        String site;
        panelListeRoom = new JPanel(new GridLayout(listRoom.size(), 1));//remplacer 15 par n etudiants

        for (Room room : listRoom) {
            panelListeRoom.add(new JLabel(room.getName(), SwingConstants.CENTER));//Ajouter fonction string avec tt les infos de l'etudiant
            panelListeRoom.add(new JLabel(String.valueOf(room.getCapacity()), SwingConstants.CENTER));

            if(room.getIdSite()==1){
                site = "Paris";
            }
            else{
                site = "Lyon";
            }
            panelListeRoom.add(new JLabel(site, SwingConstants.CENTER));

        }

        return panelListeRoom;
    }

/////////////////////////////


    private JPanel panelAfficherP() {

        List<Student> listePromo1 = new ArrayList();
        List<Student> listePromo2 = new ArrayList();
        List<Student> listePromo3 = new ArrayList();
        List<Student> listePromo4 = new ArrayList();
        List<Student> listePromo5 = new ArrayList();
        for(Student student : listStudents){
            if(student.getIdGroupPromotion() == 1){
                listePromo1.add(student);
            }
            if(student.getIdGroupPromotion() == 2){
                listePromo1.add(student);
            }
            if(student.getIdGroupPromotion() == 3){
                listePromo2.add(student);
            }
            if(student.getIdGroupPromotion() == 4){
                listePromo2.add(student);
            }
            if(student.getIdGroupPromotion() == 5){
                listePromo3.add(student);
            }
            if(student.getIdGroupPromotion() == 6){
                listePromo3.add(student);
            }
        }
        cardLayoutPromo = new CardLayout();
        panelAfficherPromo = new JPanel();
        panelAfficherPromo.setLayout(cardLayoutPromo);
        panelAfficherPromo.add(panelListePromo(), listPromo[0]);
        panelAfficherPromo.add(panelpromo2(listePromo1), listPromo[1]);///////////////____________Taille promo ++++++++++++ List listrpomo ING2
        panelAfficherPromo.add(panelpromo2(listePromo2), listPromo[2]);///////////////____________Taille promo ++++++++++++ List listrpomo ING3
        panelAfficherPromo.add(panelpromo2(listePromo3), listPromo[3]);///////////////____________Taille promo ++++++++++++ List listrpomo ING4
        panelAfficherPromo.add(panelpromo2(listePromo4), listPromo[4]);///////////////____________Taille promo ++++++++++++ List listrpomo ING5
        panelAfficherPromo.add(panelpromo2(listePromo5), listPromo[5]);///////////////____________Taille promo ++++++++++++ List listrpomo ING5

        return panelAfficherPromo;
    }
    private JPanel panelpromo2(List<Student> students) {

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(panelInfoStudent(), BorderLayout.NORTH);
        JScrollPane jScrollPaneS = new JScrollPane(panelListeEleveP(students));
        jScrollPaneS.setPreferredSize(new Dimension(0, 70));
        jpanel.add(jScrollPaneS, BorderLayout.CENTER);

        return jpanel;
    }
    private JPanel panelListeEleveP(List<Student> students) {

        panelListeEleveP = new JPanel();
        panelListeEleveP.add(panelinfoS(), BorderLayout.NORTH);
        panelListeEleveP.add(panelListeStudent(students), BorderLayout.CENTER, SwingConstants.CENTER);////////////////--------Ajouter une liste des eleves en parametre

        return panelListeEleveP;
    }
    private JPanel panelListePromo() {


        panelListePromo = new JPanel(new GridLayout(5, 1));
        JButton PromoING1 = new JButton("ING 1");
        PromoING1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[1]);
            }
        });
        JButton PromoING2 = new JButton("ING 2");
        PromoING2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[2]);
            }
        });
        JButton PromoING3 = new JButton("ING 3");
        PromoING3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[3]);
            }
        });
        JButton PromoING4 = new JButton("ING 4");
        PromoING4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[4]);
            }
        });
        JButton PromoING5 = new JButton("ING 5");
        PromoING5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[5]);
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
        Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
        Connection cnx = db.connectDB();
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        ViewTeacher frame = new ViewTeacher(db,cnx,41);
        frame.setVisible(true);
    }
}