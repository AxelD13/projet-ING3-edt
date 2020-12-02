package v;
import c.Database;
import m.Student;
import m.Teacher;
import m.dao.*;
import m.Promotion;
import m.user.EnumPermission;
import m.user.User;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class ViewRespSco extends JFrame {
    private final Database db;
    private final Connection cnx;

    private final String[] promos = {"1e année", "2e année", "3e année", "4e année", "5e année"}; //different groupe que l'on auar avec groupe.nom
    private final String[] groups = {"1A", "1B", "2A", "2B", "3A", "3B"}; //different groupe que l'on auar avec groupe.nom
    private final String[] startTime = {"8H00", "9H30", "11H00", "12H30", "14H00","15H30","17H00","18H30"};
    private final String[] endTime = {"9H30", "11H00", "12H30", "14H00","15H30","17H00","18H30","20H00"};
    private final String[] slots = {"8H00 - 9H30", "9H30 - 11H00", "11H00 - 12H30", "12H30 - 14H00", "14H00 - 15H30", "15H30 - 17H00", "18H30 - 20H00"};
    private final String[] courses = {"Maths", "Physique", "Physique Appliquée", "Informatique","LV1","LV2","Analyse financiere"};
    private final String[] days = {"Lundi", "Mardi", "Mercredi","Jeudi","Vendredi","Samedi"};
    private final String[] listContent = {"EDT", "AfficherE","AfficherT","AfficherR","AfficherP"};
    private CardLayout cardLayout;
    private JPanel panelMenu, panelPrincipal, panelJours, panelSemaine, panelHoraire,
            panelJoursSemaine, panelEdt, panelAfficherStudent, panelInfoStudent, panelInfoS,
            panelinfoSrecherche, panelListeStudent, panelAfficherTeacher, panelinfoTeacher, panelListeTeacher,
            panelAfficherRoom, panelinfoRoom, panelListeRoom, panelAfficherPromo, panelinfoPromo,
            panelListePromo, panelinfoT, panelinfoTrecherche, panelinfoR, panelinfoRrecherche;


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
        this.getContentPane().add(panelPrincipal(), BorderLayout.CENTER);
        this.setVisible(true);
    }

/////////////////////////////

    private JPanel panelPrincipal(){
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(cardLayout);
        panelPrincipal.add(panelEdt(), listContent[0]);
        panelPrincipal.add(panelAfficherS(), listContent[1]);
        panelPrincipal.add(panelAfficherT(), listContent[2]);
        panelPrincipal.add(panelAfficherR(), listContent[3]);
        panelPrincipal.add(panelAfficherP(), listContent[4]);

        return panelPrincipal;
    }

/////////////////////////////

    /* Methode de construction de la barre de menu */
    private JMenuBar createMenuBar() {

        // La barre de menu à proprement parler
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(0,50));

        // Définition du menu déroulant "Display" et de son contenu
        JMenu mnuDisplay = new JMenu( "Afficher");
        //mnuDisplay.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));  // Ajouter de la disantce entre les boutons

        JMenuItem mnuEdT = new JMenuItem( "temp jobs" );
        //mnuEdT.addActionListener( this::mnuNewListener );
        mnuDisplay.add(mnuEdT);

        mnuDisplay.addSeparator();

        JMenuItem mnuStudent = new JMenuItem( "Liste des étudiants" );
        mnuStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelPrincipal, listContent[1]);
            }
        });
        mnuDisplay.add(mnuStudent);

        JMenuItem mnuTeach = new JMenuItem( "Liste des professeurs" );
        mnuTeach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelPrincipal, listContent[2]);
            }
        });
        mnuDisplay.add(mnuTeach);

        JMenuItem mnuFreeRooms = new JMenuItem( "Salles libres" );
        mnuFreeRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelPrincipal, listContent[3]);
            }
        });
        mnuDisplay.add(mnuFreeRooms);

        JMenuItem mnuPromos = new JMenuItem( "Promotions" );
        mnuPromos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelPrincipal, listContent[4]);
            }
        });
        mnuDisplay.add(mnuPromos);

        mnuDisplay.addSeparator();

        mnuDisplay.addSeparator();

        JMenuItem mnuExit = new JMenuItem( "Déconnexion" );
        mnuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelPrincipal, listContent[0]);
            }
        });
        mnuDisplay.add(mnuExit);

        menuBar.add(mnuDisplay);

        // Définition du menu déroulant "Ajouter" et de son contenu
        JMenu mnuEdit = new JMenu( "Ajouter" );

        JMenuItem mnuAddTeacher = new JMenuItem( "Professeur" );
        mnuAddTeacher.addActionListener(this::ListnerAddTeacher);
        mnuEdit.add(mnuAddTeacher);

        JMenuItem mnuAddStudent = new JMenuItem( "Etudiant" );
        mnuAddStudent.addActionListener(this::ListnerAddStudent);
        mnuEdit.add(mnuAddStudent);

        mnuEdit.addSeparator();

        JMenuItem mnuAddCours = new JMenuItem( "Cours" );
        mnuAddCours.addActionListener(this::ListnerAddCours);
        mnuEdit.add(mnuAddCours);

        mnuEdit.addSeparator();

        JMenuItem mnuAddMatiere = new JMenuItem( "Matière" );
        //mnuAddMatiere.addActionListener(this::ListnerAddCours);
        mnuEdit.add(mnuAddMatiere);

        menuBar.add(mnuEdit);

        // Définition du menu déroulant "Delete" et de son contenu
        JMenu mnuDelete = new JMenu( "Supprimer" );

        JMenuItem mnuDeleteTeacher = new JMenuItem( "Professeur" );
        mnuDelete.add(mnuDeleteTeacher);


        JMenuItem mnuDeleteStudent = new JMenuItem( "Etudiant" );
        mnuDelete.add(mnuDeleteStudent);

        mnuDelete.addSeparator();

        JMenuItem mnuDeleteClasses = new JMenuItem( "Classes" );
        mnuDelete.add(mnuDeleteClasses);

        menuBar.add( mnuDelete );

        return menuBar;
    }

    /* Construction et injection de la barre de menu*/
    private JPanel panelmenu(){
        panelMenu = new JPanel();
        this.setJMenuBar( this.createMenuBar() );
        return panelMenu;
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

    private JPanel panelAfficherS(){
        panelAfficherStudent = new JPanel();
        panelAfficherStudent.setLayout(new BorderLayout());//////Probleme ici
        panelAfficherStudent.add(panelInfoStudent(),BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListeStudent());
        jScrollPaneS.setPreferredSize(new Dimension(0,70));
        panelAfficherStudent.add(jScrollPaneS,BorderLayout.CENTER);

        return panelAfficherStudent;
    }

    private JPanel panelInfoStudent(){
        panelInfoStudent = new JPanel();
        panelInfoStudent.setLayout(new BorderLayout());
        panelInfoStudent.add(panelinfoSrecherche(),BorderLayout.NORTH);
        panelInfoStudent.add(panelinfoS(),BorderLayout.CENTER);

        return panelInfoStudent;
    }

    private JPanel panelinfoSrecherche(){
        panelinfoSrecherche = new JPanel();
        JTextField jtextRechSNom = new JTextField();
        JLabel labelNom = new JLabel("Nom");
        labelNom.setLabelFor(jtextRechSNom);
        jtextRechSNom.setPreferredSize(new Dimension(120,30));
        JTextField jtextRechPrenom = new JTextField();
        JLabel labelPrenom = new JLabel("Prénom");
        labelNom.setLabelFor(jtextRechPrenom);
        jtextRechPrenom.setPreferredSize(new Dimension(120,30));
        JButton jButtonRecherche = new JButton("Rechercher");

        panelinfoSrecherche.add(labelNom);
        panelinfoSrecherche.add(jtextRechSNom);//---------------------Ajouter listner
        panelinfoSrecherche.add(labelPrenom);
        panelinfoSrecherche.add(jtextRechPrenom);//---------------------Ajouter listner
        panelinfoSrecherche.add(jButtonRecherche);//---------------------Ajouter listner

        return panelinfoSrecherche;
    }

    private JPanel panelinfoS(){
        panelInfoS= new JPanel( new GridLayout(1,6));
        panelInfoS.setPreferredSize(new Dimension(0,50));

        JLabel jLabel_nom = new JLabel("Nom", SwingConstants.CENTER);
        panelInfoS.add(jLabel_nom);
        JLabel jLabel_prenom = new JLabel("Prenom", SwingConstants.CENTER);
        panelInfoS.add(jLabel_prenom);
        JLabel jLabel_gp= new JLabel("Groupe", SwingConstants.CENTER);
        panelInfoS.add(jLabel_gp);
        JLabel jLabel_promo = new JLabel("Promo", SwingConstants.CENTER);
        panelInfoS.add(jLabel_promo);
        JLabel jLabel_note = new JLabel("Note", SwingConstants.CENTER);
        panelInfoS.add(jLabel_note);
        JLabel jLabel_abs = new JLabel("Absence", SwingConstants.CENTER);
        panelInfoS.add(jLabel_abs);

        return panelInfoS;
    }

    private JPanel panelListeStudent(){  //version provisoire avec User, sera remplacé par Student ensuite
        DAO<Student> studentDAO = new StudentDAO(cnx);
        List<Student> listStudents = studentDAO.getAll();

        panelListeStudent = new JPanel(new GridLayout(listStudents.size(),6));

        for(Student student : listStudents) {
            panelListeStudent.add(new JLabel(student.getLastName(), SwingConstants.CENTER));
            panelListeStudent.add(new JLabel(student.getFirstName(), SwingConstants.CENTER));
            panelListeStudent.add(new JLabel(student.getGroupPromotion(cnx).getName(), SwingConstants.CENTER));
            panelListeStudent.add(new JLabel(student.getPromotion(cnx).getName(), SwingConstants.CENTER));
            panelListeStudent.add(new JLabel("20", SwingConstants.CENTER));
            panelListeStudent.add(new JLabel("0", SwingConstants.CENTER));
        }

        return panelListeStudent;
    }

/////////////////////////////

    private JPanel panelAfficherT(){
        panelAfficherTeacher = new JPanel();
        panelAfficherTeacher.setLayout(new BorderLayout()); //////Probleme ici
        panelAfficherTeacher.add(panelinfoTeacher(),BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListeT());
        jScrollPaneS.setPreferredSize(new Dimension(0,70));
        panelAfficherTeacher.add(jScrollPaneS,BorderLayout.CENTER);

        return panelAfficherTeacher;
    }

    private JPanel panelinfoTeacher(){

        panelinfoTeacher = new JPanel();
        panelinfoTeacher.setLayout(new BorderLayout());
        panelinfoTeacher.add(panelinfoTrecherche(),BorderLayout.NORTH);
        panelinfoTeacher.add(panelinfoT(),BorderLayout.CENTER);

        return panelinfoTeacher;
    }

    private JPanel panelinfoTrecherche(){
        panelinfoTrecherche = new JPanel();
        JTextField jtextRechNom = new JTextField("Nom" );
        jtextRechNom.setPreferredSize(new Dimension(120,30));
        JTextField jtextRechMat = new JTextField("Matiere" );
        jtextRechMat.setPreferredSize(new Dimension(120,30));
        JButton jButtonRecherche = new JButton("Rechercher");

        panelinfoTrecherche.add(jtextRechNom);//---------------------Ajouter listner
        panelinfoTrecherche.add(jtextRechMat);//---------------------Ajouter listner
        panelinfoTrecherche.add(jButtonRecherche);//---------------------Ajouter listner

        return panelinfoTrecherche;
    }

    private JPanel panelinfoT(){

        panelinfoT = new JPanel( new GridLayout(1,6));
        panelinfoT.setPreferredSize(new Dimension(0,50));
        JLabel jLabelTfirstName= new JLabel("Nom", SwingConstants.CENTER);
        panelinfoT.add(jLabelTfirstName);
        JLabel jLabelTname= new JLabel("Prenom", SwingConstants.CENTER);
        panelinfoT.add(jLabelTname);
        JLabel jLabelTmatiere= new JLabel("Matiere", SwingConstants.CENTER);
        panelinfoT.add(jLabelTmatiere);
        JLabel jLabelTnbHours= new JLabel("Nombre Heure de cours", SwingConstants.CENTER);
        panelinfoT.add(jLabelTnbHours);
        return panelinfoT;

    }

    private JPanel panelListeT(){  //version provisoire avec User, sera remplacé par Teacher ensuite
        DAO<Teacher> teacherDAO = new TeacherDAO(cnx);
        List<Teacher> listTeachers = teacherDAO.getAll();

        panelListeTeacher = new JPanel(new GridLayout(listTeachers.size(),1));

        for (Teacher teacher : listTeachers) {
            panelListeTeacher.add(new JLabel(teacher.getLastName(), SwingConstants.CENTER));
            panelListeTeacher.add(new JLabel(teacher.getFirstName(), SwingConstants.CENTER));
            panelListeTeacher.add(new JLabel(teacher.displayCourses(), SwingConstants.CENTER));
            panelListeTeacher.add(new JLabel(String.valueOf(teacher.getNbHours()), SwingConstants.CENTER));
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

    private JPanel panelAfficherP(){
        panelAfficherPromo = new JPanel();
        panelAfficherPromo.setLayout(new BorderLayout());
        panelAfficherPromo.add(panelinfoPromo(),BorderLayout.NORTH);

        JScrollPane jScrollPaneS = new JScrollPane(panelListePromo());
        jScrollPaneS.setPreferredSize(new Dimension(0,70));
        panelAfficherPromo.add(jScrollPaneS,BorderLayout.CENTER);

        return panelAfficherPromo;
    }

    private JPanel panelinfoPromo(){
        panelinfoPromo= new JPanel( new GridLayout(1,6));
        panelinfoPromo.setPreferredSize(new Dimension(0,50));
        JLabel jLabelPromo= new JLabel("Promo", SwingConstants.CENTER);
        panelinfoPromo.add(jLabelPromo);
        JLabel jLabelPannee= new JLabel("Année", SwingConstants.CENTER);
        panelinfoPromo.add(jLabelPannee);
        JLabel jLabelPnbEleve= new JLabel("Nb élèves", SwingConstants.CENTER);
        panelinfoPromo.add(jLabelPnbEleve);
        JTextField jtextRechR = new JTextField("Number", SwingConstants.CENTER);
        panelinfoPromo.add(jtextRechR);//---------------------Ajouter listner

        return panelinfoPromo;
    }

    private JPanel panelListePromo(){
        DAO<Promotion> promotionDAO = new PromotionDAO(cnx);
        List<Promotion> listPromotions = promotionDAO.getAll();

        panelListePromo = new JPanel(new GridLayout(5,1));

        for (Promotion p : listPromotions) {
            panelListePromo.add(new JButton(p.getName()));//Ajouter fonction string avec tt les infos de l'etudiant
        }

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
        JButton jButonEnregistre = new JButton("ENREGISTRER");
        jButonEnregistre.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                System.out.print(jtfNomClasse.getText());
                System.out.print(jcmbGroupeClasse.getSelectedItem());
                System.out.print(jcmbMatiereCours.getSelectedItem());
                System.out.print(jcmHeureDebut.getSelectedItem());
                System.out.print(jcmHeureFin.getSelectedItem());
                JOptionPane.showMessageDialog(jPanel,"Session créée");

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

        JFrame jframeAddStudent = new JFrame();
        jframeAddStudent.setSize(400,300);
        jframeAddStudent.setTitle("Ajout d'un élève");
        jframeAddStudent.setLocationRelativeTo(null);
        jframeAddStudent.setResizable(false);
        jframeAddStudent.setVisible(true);
        jframeAddStudent.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        jframeAddStudent.getContentPane().add(jpanelAddStudent, BorderLayout.CENTER);
        jframeAddStudent.setVisible(true);

        return jframeAddStudent;
    }


    public static void main(String[] args) throws Exception {
        Database db = new Database("jdbc:mysql://localhost:3306/projet_edt", "root", "");
        Connection cnx = db.connectDB();
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        ViewRespSco frame = new ViewRespSco(db, cnx, 40);
        frame.setVisible(true);
    }
}