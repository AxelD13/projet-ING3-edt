package v;

import m.Course;
import m.Room;
import m.dao.CourseDAO;
import m.dao.DAO;
import m.dao.RoomDAO;
import m.dao.SessionDAO;
import m.session.Session;
import org.jfree.ui.RefineryUtilities;
import c.Database;
import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


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

    Database db = new Database("jdbc:mysql://localhost:8889/projet_edt", "root", "root");
    Connection cnx = db.connectDB();
    DAO<Room> RoomDAO = new RoomDAO(cnx);
    List<Room> listRoom = RoomDAO.getAll();
    DAO<Session> sessionDAO = new SessionDAO(cnx);
    List<Session> listSessions = sessionDAO.getAll();
    /* Construction de l'interface graphique */
    public ViewStudent(Database db, Connection cnx, int idUser) {
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

    private JMenuBar createMenuBar() {

        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(0,50));

        JMenu mnuDisplay = new JMenu( "General");

        mnuDisplay.addSeparator();

        JMenuItem mnufreerooms = new JMenuItem( "Salles " );
        mnufreerooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelprincipal, listContent[1]);
            }
        });

        mnuDisplay.add(mnufreerooms);

        mnuDisplay.addSeparator();

        JMenuItem mnuDiag = new JMenuItem( "Diagram ciculaire" );
        mnuDiag.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {
            SwingUtilities.invokeLater(() -> {
            PieChart example = new PieChart("NUMBER COURSE ",listSessions);
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            example.setVisible(true); });
        }}
        );
        mnuDisplay.add(mnuDiag);
        JMenuItem mnuDiag2 = new JMenuItem( "Diagram baton" );
        mnuDiag2.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent event) {
        SwingUtilities.invokeLater(()->{
        Chart2 example=new Chart2("NUMBER COURSE", listSessions);
        example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        example.setVisible(true); });
        }}
        );
        mnuDisplay.add(mnuDiag2);

        mnuDisplay.addSeparator();

        JMenuItem mnuExit = new JMenuItem( "Sortie" );
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
        panelEdt.setName("Mon emploi du temps");
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
        jPanel.setBackground(new Color(255,255,255));
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
        jPanel.setBackground(new Color(255,255,255));
        for(int i = 1; i<30;i++ ){
            jPanel.add( new JButton(String.valueOf(i)));
        }

        return jPanel;
    }
    /* Methode de construction des plages horaires*/
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


    public static void main(String[] args) throws Exception {
        Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
        Connection cnx = db.connectDB();
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        ViewStudent frame = new ViewStudent(db, cnx, 42);
        frame.setVisible(true);
    }
}
