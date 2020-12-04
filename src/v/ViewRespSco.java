package v;
import c.Database;
import m.*;
import m.dao.*;
import m.session.Session;
import m.user.EnumPermission;
import m.user.User;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ViewRespSco extends JFrame {
    String[] niveauxStrings = {"1 er année", "2 ème année", "3 ème année", "4 ème année", "5 ème année"};//different groupe que l'on auar avec groupe.nom
    String[] HorraireDebut = {"8H00", "9H30", "11H00", "12H30", "14H00", "15H30", "17H00", "18H30"};
    String[] HorraireFin = {"9H30", "11H00", "12H30", "14H00", "15H30", "17H00", "18H30", "20H00"};
    String[] matieres = {"Maths", "Physique", "Physique Appliquée", "Informatique", "LV1", "LV2", "Analyse financiere"};
    String[] Jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    private final String[] slots = {"8H00 - 9H30", "9H30 - 11H00", "11H00 - 12H30", "12H30 - 14H00", "14H00 - 15H30", "15H30 - 17H00", "18H30 - 20H00"};
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

    Database db = new Database("jdbc:mysql://localhost:8889/projet_edt", "root", "root");
    Connection cnx = db.connectDB();
    DAO<Room> roomDAO = new RoomDAO(cnx);
    List<Room> listRooms = roomDAO.getAll();
    DAO<Student> studentDAO = new StudentDAO(cnx);
    List<Student> listStudents = studentDAO.getAll();
    DAO<Teacher> teacherDAO = new TeacherDAO(cnx);
    List<Teacher> listTeachers = teacherDAO.getAll();
    DAO<TeachersSession> TeachersSessionDAO = new TeachersSessionDAO(cnx);
    List<TeachersSession> listTeachersSession = TeachersSessionDAO.getAll();
    DAO<Room> RoomDAO = new RoomDAO(cnx);
    List<Room> listRoom = RoomDAO.getAll();



    /* Construction de l'interface graphique */
    public ViewRespSco(Database db, Connection cnx, int idUser) {
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
        JMenu mnuDisplay = new JMenu("Géneral");
        //mnuDisplay.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));// Ajouter de la disantce entre les boutons


        JMenuItem mnuStudent = new JMenuItem("List des etudiants");
        mnuStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[1]);
            }
        });
        mnuDisplay.add(mnuStudent);

        JMenuItem mnuTeach = new JMenuItem("List des professeurs");
        mnuTeach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[2]);
            }
        });
        mnuDisplay.add(mnuTeach);

        JMenuItem mnuPromos = new JMenuItem("List des promotions");
        mnuPromos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[4]);
            }
        });
        mnuDisplay.add(mnuPromos);

        mnuDisplay.addSeparator();

        JMenuItem mnufreerooms = new JMenuItem("Salles");
        mnufreerooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[3]);
            }
        });
        mnuDisplay.add(mnufreerooms);

        mnuDisplay.addSeparator();

        JMenuItem mnuExit = new JMenuItem("Retour");
        mnuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayoutPromo.show(panelAfficherPromo, listPromo[0]);
                cardLayout.show(panelprincipal, listContent[0]);
            }
        });
        mnuDisplay.add(mnuExit);

        menuBar.add(mnuDisplay);

        // Définition du menu déroulant "Ajouter" et de son contenu
        JMenu mnuEdit = new JMenu("Ajouter");

        JMenuItem mnuAddTeacher = new JMenuItem("Professeur");
        mnuAddTeacher.addActionListener(this::ListnerAddTeacher);
        mnuEdit.add(mnuAddTeacher);

        JMenuItem mnuAddStudent = new JMenuItem("Eleve");
        mnuAddStudent.addActionListener(this::ListnerAddStudent);
        mnuEdit.add(mnuAddStudent);

        mnuEdit.addSeparator();

        JMenuItem mnuAddCours = new JMenuItem("Cours");
        mnuAddCours.addActionListener(this::ListnerAddCours);
        mnuEdit.add(mnuAddCours);

        menuBar.add(mnuEdit);

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

    /**
     *   Methode de construction des heures
     */
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

    /**
     * Methode de construction des jours de la semaines
     * @return
     */
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

    /**
     * Methode de construction des boutons semaines
     * @return
     */
    private JPanel panSemaine(){
        JPanel jPanel = new JPanel(new GridLayout(1,30));
        jPanel.setBackground(new Color(255, 255, 255));
        for(int i = 1; i<30;i++ ){
            jPanel.add( new JButton(String.valueOf(i)));
        }

        return jPanel;
    }

    /**
     * Methode de construction des plages horaires
     * @return
     */
    private JPanel panQuadrillage() {
        DAO<Session> sessionDAO = new SessionDAO(cnx);
        DAO<Course> courseDAO = new CourseDAO(cnx);
        List<Session> listSessions = sessionDAO.getAll();
        JPanel jPanel = new JPanel(new GridLayout(8, 6));
        jPanel.setBackground(new Color(255,255,255));
        int week = 1;
        Date date = new Date(2020,11,16);
        Time startTime = new Time(8,0,0);
        Time endTime = new Time(9,30,0);
        for(int i=0; i<8; i++) {
            for(int j=0; j<6; j++) {
                JPanelSession jPanelSession = new JPanelSession(week, date, startTime, endTime);
                for(Session session : listSessions) {
                    if(session.getWeek() == week  && session.getStartTime().equals(startTime) && session.getEndTime().equals(endTime)) {
                        //jPanelSession.setTextField(new JTextField(courseDAO.find(session.getIdCourse()).getName()));
                    }
                }
                jPanel.add(jPanelSession);
            }
            startTime.setHours(startTime.getHours() + 1);
            startTime.setMinutes(startTime.getMinutes() + 30);
            endTime.setHours(startTime.getHours() + 1);
            endTime.setMinutes(startTime.getMinutes() + 30);
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

    /**
     * Ajout du panel panelInfoStudent et panelListeStudent
     * @return
     */
    private JPanel panelAfficherS2() {

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(panelInfoStudent(), BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListeStudent(listStudents));///////////////////NB eleve----------------et ajouter liste des studient
        jScrollPaneS.setPreferredSize(new Dimension(0, 70));
        jpanel.add(jScrollPaneS, BorderLayout.CENTER);

        return jpanel;
    }

    /**
     * Ajout dans un panel tout les panel concernant les informations de l'etudiant
     * @return
     */
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

        JLabel jLabel_Lundi = new JLabel("Prenom", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Lundi);
        JLabel jLabel_Mardi = new JLabel("Nom", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Mardi);
        JLabel jLabel_Mercredi = new JLabel("Groupe Promotion", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Mercredi);
        JLabel jLabel_Jeudi = new JLabel("Email", SwingConstants.CENTER);
        panelInfoS.add(jLabel_Jeudi);

        return panelInfoS;

    }

    /**
     * Affichage de tt les données de l'etudiant listStudents
     * @return
     */
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
    /**
     * Creation d'un des panel principale Affichage des professeurs
     * @return
     */
    private JPanel panelAfficherT() {

        cardLayoutTeacher = new CardLayout();
        panelAfficherTeacher = new JPanel();
        panelAfficherTeacher.setLayout(cardLayoutTeacher);
        panelAfficherTeacher.add(panelAfficherT2(), listTeacher[0]);
        //panelAfficherTeacher.add(panelListeTRecherche(), listTeacher[1]);//prof1 Fonction qui retourne tt les profs
        //panelAfficherTeacher.add(panelAfficherT2(prof2), listTeacher[1]);// prof2 fonction qui retourne une liste de prof en fonction du nom
        return panelAfficherTeacher;
    }

    /**
     * Association des differents panels information et liste
     * @return
     */
    private JPanel panelAfficherT2() {
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(panelinfoTeacher(), BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListeT(listTeachers));//-- ajouter la liste
        jScrollPaneS.setPreferredSize(new Dimension(0, 70));
        jpanel.add(jScrollPaneS, BorderLayout.CENTER);

        return jpanel;
    }
    /**
     * Ajout dans un panel tout les panel concernant les informations de du professeur
     * @return
     */
    private JPanel panelinfoTeacher() {

        panelinfoTeacher = new JPanel();
        panelinfoTeacher.setLayout(new BorderLayout());
        panelinfoTeacher.add(panelinfoTrecherche(), BorderLayout.NORTH);
        panelinfoTeacher.add(panelinfoT(), BorderLayout.CENTER);

        return panelinfoTeacher;
    }
    private JPanel panelinfoTrecherche() {
        panelinfoTrecherche = new JPanel();
        JTextField jtextRechNom = new JTextField("Prenom");
        jtextRechNom.setPreferredSize(new Dimension(120, 30));
        JTextField jtextPrenom = new JTextField("Nom");
        jtextPrenom.setPreferredSize(new Dimension(120, 30));
        JButton jButtonRecherche = new JButton("Recherhce");
        JButton jButtonReturn = new JButton("Retour");
        panelinfoTrecherche.add(jtextRechNom);
        panelinfoTrecherche.add(jtextPrenom);
        panelinfoTrecherche.add(jButtonRecherche);
        panelinfoTrecherche.add(jButtonReturn);


        jButtonRecherche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String Name = jtextRechNom.getText();
                String FirstName = jtextPrenom.getText();

                for (Teacher teacher : listTeachers) {
                    if (Name.equals(teacher.getFirstName())) {
                        if (FirstName.equals(teacher.getLastName())) {
                            tRecherche(teacher);
                        }

                        else {
                            JOptionPane.showMessageDialog(panelinfoSrecherche, "IL n'existe pas de professeur "+ Name +" "+ FirstName );
                        }
                    }
                }
            }
        });

        jButtonReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelAfficherTeacher, listTeacher[0]);
            }
        });
        return panelinfoTrecherche;
    }
    private JFrame tRecherche(Teacher teacher){

        JPanel jPanel = new JPanel(new GridLayout(3,1));
        jPanel.add(new JLabel("Prénom du Professeur : "+teacher.getFirstName(), SwingConstants.CENTER));
        jPanel.add(new JLabel("Nom du Professeur : "+teacher.getLastName(), SwingConstants.CENTER));
        jPanel.add(new JLabel("Email : "+teacher.getEmail(), SwingConstants.CENTER));

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

    /**
     * panel concernant toutes les incformations concernant les professeurs
     * @return
     */
    private JPanel panelinfoT() {

        panelinfoT = new JPanel(new GridLayout(1, 6));
        panelinfoT.setPreferredSize(new Dimension(0, 50));
        JLabel jLabelTfirstName = new JLabel("Prenom", SwingConstants.CENTER);
        panelinfoT.add(jLabelTfirstName);
        JLabel jLabelTname = new JLabel("Nom", SwingConstants.CENTER);
        panelinfoT.add(jLabelTname);
        JLabel jLabelTmatiere = new JLabel("Email", SwingConstants.CENTER);
        panelinfoT.add(jLabelTmatiere);

        return panelinfoT;

    }

    /**
     * Panel affichage de tt les données du professeur
     * @return
     */
    private JPanel panelListeT(List<Teacher> listTeachers) {

        panelListeTeacher = new JPanel(new GridLayout(listTeachers.size(), 1));
        for (Teacher teacher : listTeachers) {
            panelListeTeacher.add(new JLabel(teacher.getFirstName(), SwingConstants.CENTER));
            panelListeTeacher.add(new JLabel(teacher.getLastName(), SwingConstants.CENTER));
            panelListeTeacher.add(new JLabel(teacher.getEmail(), SwingConstants.CENTER));
        }

        return panelListeTeacher;
    }

/////////////////////////////
    /**
     * Creation d'un des panel principale Affichage des salles
     * @return
     */
    private JPanel panelAfficherR() {

        panelAfficherRoom = new JPanel();
        panelAfficherRoom.setLayout(new BorderLayout());
        panelAfficherRoom.add(panelinfoRoom(), BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListeRoom(listRoom));
        jScrollPaneS.setPreferredSize(new Dimension(0, 70));
        panelAfficherRoom.add(jScrollPaneS, BorderLayout.CENTER);

        return panelAfficherRoom;
    }
    /**
     * Regroupement de toutes les panel informations et liste
     * @return
     */
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
        JTextField jtextRechRoomSite = new JTextField("Site");
        jtextRechRoomSite.setPreferredSize(new Dimension(120, 30));
        JButton jButtonRecherche = new JButton("Recherche");
        panelinfoRrecherche.add(jtextRechRoom);
        panelinfoRrecherche.add(jtextRechRoomSite);
        panelinfoRrecherche.add(jButtonRecherche);

        jButtonRecherche.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String Numero = jtextRechRoom.getText();
                String site = jtextRechRoomSite.getText();
                List<Room> roomRecherche = new ArrayList();
                int idSite;
                if(site.equals("Paris")){idSite = 1;}
                else{ idSite = 2;}

                for (Room room : listRoom) {
                    if (Numero.equals(room.getName())) {
                        roomRecherche.add(room);
                    }
                    if(room.getIdSite() == idSite){
                        roomRecherche.add(room);
                    }
                }
                    if(roomRecherche == null){
                        JOptionPane.showMessageDialog(panelinfoSrecherche, "La salle que vous recherché n'existe pas");
                    }
                Rrecherche(roomRecherche);
            }
        });

        return panelinfoRrecherche;
    }
    /**
     * Panel affichant les Room rechercher
     * @param room
     * @return
     */
    private JFrame Rrecherche(List<Room> room){
        String site;

        JPanel jPanel = new JPanel(new GridLayout(room.size(),1));
        for (Room rooms : room) {
            jPanel.add(new JLabel("Numero de salle: " + rooms.getName(), SwingConstants.CENTER));
            jPanel.add(new JLabel("Capcaité salle max : " + rooms.getCapacity(), SwingConstants.CENTER));
            if (rooms.getIdSite() == 1) {
                site = "Paris";
            } else {
                site = "Lyon";
            }
            jPanel.add(new JLabel("site : " + site, SwingConstants.CENTER));
        }

        JFrame frameSrecherche = new JFrame();
        frameSrecherche.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameSrecherche.setSize(400, 300);
        frameSrecherche.setTitle("Recherche Salles");
        frameSrecherche.setLocationRelativeTo(null);
        frameSrecherche.setResizable(true);
        JScrollPane jScrollPaneS = new JScrollPane(jPanel);
        jScrollPaneS.setPreferredSize(new Dimension(0, 70));
        frameSrecherche.add(jScrollPaneS, BorderLayout.CENTER);
        frameSrecherche.setVisible(true);

        return frameSrecherche;

    }
    /**
     * Panel concernant les caracteristiques de chaque salles
     * @return
     */
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
    /**
     * Panel montrant l'affichage de la list des salles
     * @param listRoom
     * @return
     */
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
    /**
     * Creation d'un des panel principale Affichage des promos
     * @return
     */
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

    /**
     * Associations de chaque panel info et liste des promos
     * @param students
     * @return
     */
    private JPanel panelpromo2(List<Student> students) {

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(panelInfoStudent(), BorderLayout.NORTH);
        JScrollPane jScrollPaneS = new JScrollPane(panelListeEleveP(students));
        jScrollPaneS.setPreferredSize(new Dimension(0, 70));
        jpanel.add(jScrollPaneS, BorderLayout.CENTER);

        return jpanel;
    }

    /**
     * panel des liste des eleves par eleves
     * @param students
     * @return
     */
    private JPanel panelListeEleveP(List<Student> students) {

        panelListeEleveP = new JPanel();
        panelListeEleveP.add(panelinfoS(), BorderLayout.NORTH);
        panelListeEleveP.add(panelListeStudent(students), BorderLayout.CENTER, SwingConstants.CENTER);////////////////--------Ajouter une liste des eleves en parametre

        return panelListeEleveP;
    }

    /**
     * panel affichant les differents promotion afin de choisir la liste d'eleve a choisir
     * @return
     */
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

    /**
     * Ajout d'un event pour professeur
     * @param event
     */
    public void ListnerAddTeacher(ActionEvent event) {
        create_frameAddTeacher();
    }

    /**
     * Ajout d'un event pour cours
     * @param event
     */
    public void ListnerAddCours(ActionEvent event) {
        create_frameAddCours();
    }

    /**
     * Ajour d'un event pour étudiant
     * @param event
     */
    public void ListnerAddStudent(ActionEvent event) {
        create_frameAddStudent();
    }

    /**
     * creation d'un cours
     * @return
     */
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
        JButton jButonEnregistre = new JButton("Enregistrer");
        jButonEnregistre.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                System.out.print(jtfNomClasse.getText());
                System.out.print(jcmbGroupeClasse.getSelectedItem());
                System.out.print(jcmbMatiereCours.getSelectedItem());
                System.out.print(jcmHeureDebut.getSelectedItem());
                System.out.print(jcmHeureFin.getSelectedItem());
                JOptionPane.showMessageDialog(jPanel, "Votre choix a été enregistrer");

            }
        });

        jpanel2.add(jButonEnregistre);


        JFrame frameAjoutClasse = new JFrame();
        frameAjoutClasse.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameAjoutClasse.setSize(400, 300);
        frameAjoutClasse.setTitle("Ajout d'un cours");
        frameAjoutClasse.setLocationRelativeTo(null);
        frameAjoutClasse.setResizable(false);
        frameAjoutClasse.setVisible(true);
        frameAjoutClasse.getContentPane().add(jPanel, BorderLayout.CENTER);
        frameAjoutClasse.getContentPane().add(jpanel2, BorderLayout.SOUTH);

        frameAjoutClasse.setVisible(true);


        return frameAjoutClasse;
    }

    /**
     * creation d'un professeur
     * @return
     */


    public JFrame create_frameAddTeacher() {
        JPanel jpanelAddTeacher = new JPanel();
        jpanelAddTeacher.setLayout(new GridLayout(5, 2));
        JLabel jlabelFirstName = new JLabel("Prénom :");
        JTextField jtfFirstName = new JTextField(10);
        JLabel jlabelLastName = new JLabel("Nom :");
        JTextField jtfLastName = new JTextField(10);
        DAO<Course> courseDAO = new CourseDAO(cnx);
        List<Course> listCourses = courseDAO.getAll();
        Vector<String> vectorCourses = new Vector<>();
        for(Course course : listCourses) {
            vectorCourses.add(course.getName());
        }
        JLabel jlabelCourse = new JLabel("Matière :");
        JComboBox jcmbMatiereProfesseur = new JComboBox<String>(vectorCourses);
        JLabel jlabelHours = new JLabel("Nombre d'heures :");
        JSpinner jspinnerHours = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        jpanelAddTeacher.add(jlabelFirstName);
        jpanelAddTeacher.add(jtfFirstName);
        jpanelAddTeacher.add(jlabelLastName);
        jpanelAddTeacher.add(jtfLastName);
        jpanelAddTeacher.add(jlabelCourse);
        jpanelAddTeacher.add(jcmbMatiereProfesseur);
        jpanelAddTeacher.add(jlabelHours);
        jpanelAddTeacher.add(jspinnerHours);
        JPanel jpanel2 = new JPanel();
        JButton jButonEnregistre = new JButton("ENREGISTRER");
        jButonEnregistre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                DAO<Teacher> teacherDAO = new TeacherDAO(cnx);
                CourseDAO courseDAO = new CourseDAO(cnx);
                String lastName = jtfLastName.getText();
                String firstName = jtfFirstName.getText();
                String email = firstName + "." + lastName + "@inseec-edu.com";
                String password = firstName.charAt(0) + lastName + "123";
                Teacher newTeacher = new Teacher(email.toLowerCase(), password.toLowerCase(),lastName, firstName);
                newTeacher.addCourse(courseDAO.find(String.valueOf(jcmbMatiereProfesseur.getSelectedItem())));
                if(teacherDAO.create(newTeacher)) {
                    System.out.println(jtfFirstName.getText());
                    System.out.println(jtfLastName.getText());
                    System.out.println(jcmbMatiereProfesseur.getSelectedItem());
                    System.out.println(jspinnerHours.getValue());
                    JOptionPane.showMessageDialog(jpanelAddTeacher, "Professeur ajouté.");
                }
            }
        });
        jpanel2.add(jButonEnregistre);
        JFrame frameAddTeacher = new JFrame();
        frameAddTeacher.setSize(400, 300);
        frameAddTeacher.setTitle("Ajout d'un professeur");
        frameAddTeacher.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameAddTeacher.setLocationRelativeTo(null);
        frameAddTeacher.setResizable(false);//Pour redimensionner la fenetre
        frameAddTeacher.setVisible(true);
        frameAddTeacher.getContentPane().add(jpanelAddTeacher, BorderLayout.CENTER);
        frameAddTeacher.getContentPane().add(jpanel2, BorderLayout.SOUTH);
        frameAddTeacher.setVisible(true);
        return frameAddTeacher;
    }

    /**
     * creation d'un etudiant
     * @return
     */
    public JFrame create_frameAddStudent() {
        JPanel jpanelAddStudent = new JPanel();
        jpanelAddStudent.setLayout(new GridLayout(5, 2));
        JLabel jlabelFirstName = new JLabel("Prénom :");
        JTextField jtfFirstName = new JTextField(10);
        JLabel jlabelLastName = new JLabel("Nom :");
        JTextField jtfLastName = new JTextField(10);
        DAO<Promotion> promotionDAO = new PromotionDAO(cnx);
        List<Promotion> listPromotions = promotionDAO.getAll();
        Vector<String> vectorPromotions = new Vector<>();
        for(Promotion promotion : listPromotions) {
            vectorPromotions.add(promotion.getName());
        }
        JLabel jlabelPromotion = new JLabel("Promotion :");
        JComboBox jcmbPromotion = new JComboBox<String>(vectorPromotions);
        DAO<GroupPromotion> groupPromotionDAO = new GroupPromotionDAO(cnx);
        List<GroupPromotion> listGroupsPromotion = groupPromotionDAO.getAll();
        Vector<String> vectorGroupsPromotion = new Vector<>();
        for(GroupPromotion groupPromotion : listGroupsPromotion) {
            vectorGroupsPromotion.add(groupPromotion.getName());
        }
        JLabel jlabelGroupPromotion = new JLabel("Groupe :");
        JComboBox jcmbGroupPromotion = new JComboBox<>(vectorGroupsPromotion);
        jpanelAddStudent.add(jlabelFirstName);
        jpanelAddStudent.add(jtfFirstName);
        jpanelAddStudent.add(jlabelLastName);
        jpanelAddStudent.add(jtfLastName);
        jpanelAddStudent.add(jlabelPromotion);
        jpanelAddStudent.add(jcmbPromotion);
        jpanelAddStudent.add(jlabelGroupPromotion);
        jpanelAddStudent.add(jcmbGroupPromotion);
        JPanel jpanelSave = new JPanel();
        JButton jbuttonSave = new JButton("ENREGISTRER");
        jbuttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                StudentDAO studentDAO = new StudentDAO(cnx);
                GroupPromotionDAO groupPromotionDAO = new GroupPromotionDAO(cnx);
                GroupPromotion selectedGP = groupPromotionDAO.find(String.valueOf(jcmbGroupPromotion.getSelectedItem()));
                String lastName = jtfLastName.getText();
                String firstName = jtfFirstName.getText();
                String email = firstName + "." + lastName + "@edu.ece.fr";
                String password = firstName.charAt(0) + lastName + "123";
                int number = studentDAO.generateRandomNumber();
                Student newUser = new Student(email.toLowerCase(), password.toLowerCase(), lastName,
                        firstName, number, selectedGP.getId());
                if(studentDAO.create(newUser)) {
                    System.out.println(jtfFirstName.getText());
                    System.out.println(jtfLastName.getText());
                    System.out.println(jcmbPromotion.getSelectedItem());
                    System.out.println(jcmbGroupPromotion.getSelectedItem());
                    JOptionPane.showMessageDialog(jpanelAddStudent, "Etudiant ajouté.");
                }
            }
        });
        jpanelSave.add(jbuttonSave);
        JFrame jframeAddStudent = new JFrame();
        jframeAddStudent.setSize(400, 300);
        jframeAddStudent.setTitle("Ajout d'un élève");
        jframeAddStudent.setLocationRelativeTo(null);
        jframeAddStudent.setResizable(false);
        jframeAddStudent.setVisible(true);
        jframeAddStudent.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jframeAddStudent.getContentPane().add(jpanelAddStudent, BorderLayout.CENTER);
        jframeAddStudent.getContentPane().add(jpanelSave, BorderLayout.SOUTH);
        jframeAddStudent.setVisible(true);
        return jframeAddStudent;
    }


/////////////////////////////

    public static void main(String[] args) throws Exception {
        Database db = new Database("jdbc:mysql://localhost:8889/projet_edt", "root", "root");
        Connection cnx = db.connectDB();
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        ViewRespSco frame = new ViewRespSco(db, cnx,40);
        frame.setVisible(true);
    }
}