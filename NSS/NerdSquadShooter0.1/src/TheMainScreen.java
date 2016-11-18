import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Cursor;

import javax.swing.JLabel;

public class TheMainScreen extends JFrame {

    private JPanel startScreen;

    private static SaveType currentSave, save1, save2, save3, save4, save5;

    // KeyBoardMovement control
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    // Boolean to make sure you are playing the level for the game to run
    private boolean inGame = false;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TheMainScreen frame = new TheMainScreen();
                    frame.setVisible(true);
                    frame.setResizable(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TheMainScreen() {
        setCursor();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 971, 600);
        getContentPane().setLayout(new CardLayout(0, 0));

        // setIconImage(Toolkit.getDefaultToolkit().getImage(TheMainScreen.class.getResource("Res\\background1.jpg")));

        JLabel MainScreenBackground2 = new JLabel("");
        MainScreenBackground2.setBounds(0, 0, 971, 600);
        
        MainScreenBackground2.setIcon(new ImageIcon("Res\\wallapper.jpg"));
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // contentPane.add(MainScreen, "name_105507840453942");

        // setContentPane(MainScreen);

        JPanel LoadScreen = new JPanel();
        // contentPane.add(LoadScreen, "name_105520608355714");
        LoadScreen.setLayout(null);

        JLabel LoadScreenBackground = new JLabel("");
        LoadScreenBackground.setBounds(0, 0, 665, 460);
        LoadScreen.add(LoadScreenBackground);

        JPanel BattleGrounds = new JPanel();
        // contentPane.add(BattleGrounds, "name_105513240979308");
        BattleGrounds.setLayout(null);

        JPanel Shop = new JPanel();
        // contentPane.add(Shop, "name_105515411433300");
        Shop.setLayout(null);

        JPanel Credits = new JPanel();
        // contentPane.add(Credits, "name_105517153537989");
        Credits.setLayout(null);

        JPanel SaveScreen = new JPanel();
        // contentPane.add(SaveScreen, "name_105519005027867");
        SaveScreen.setLayout(null);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        addKeyListener(new KeyAdapter() {
            @Override
            // Record User Key Selections
            public void keyPressed(KeyEvent e) {
                movement2(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                movement1(e);
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ActionListener taskPerformer = new ActionListener() {

            // Any method here will be called once 60 times a second
            public void actionPerformed(ActionEvent evt) {
                // only checks if you are playing a level, thus shop and menu do
                // not count
                if (inGame) {

                }

            }
        };
        new Timer(1000 / 60, taskPerformer).start();// timer calls every frame

        // Setting the BackScreen
        // setContentPane(startScreen);

    }

    // Save File Setting
    public static void SaveMySoul(int FileNo) {

        try {
            FileOutputStream fileOut = new FileOutputStream("SaveData/save" + FileNo + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject((currentSave));

            fileOut = new FileOutputStream("SaveData/save2.ser");
            out = new ObjectOutputStream(fileOut);
            out.writeObject((currentSave));

            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /SaveData/love.ser");
        } catch (IOException i) {
            i.printStackTrace();

        }
        try {
            FileOutputStream fileOut = new FileOutputStream("SaveData/save2.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject((currentSave));

            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /SaveData/love.ser");
        } catch (IOException i) {
            i.printStackTrace();

        }

    }

    // Load all files into temp files
    public static void RetakeMySoul() {

        try {
            FileInputStream fileIn = new FileInputStream("SaveData/save1.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            save1 = (SaveType) in.readObject();

            fileIn = new FileInputStream("SaveData/save2.ser");
            in = new ObjectInputStream(fileIn);
            save2 = (SaveType) in.readObject();

            fileIn = new FileInputStream("SaveData/save3.ser");
            in = new ObjectInputStream(fileIn);
            save3 = (SaveType) in.readObject();

            fileIn = new FileInputStream("SaveData/save4.ser");
            in = new ObjectInputStream(fileIn);
            save4 = (SaveType) in.readObject();

            fileIn = new FileInputStream("SaveData/save5.ser");
            in = new ObjectInputStream(fileIn);
            save5 = (SaveType) in.readObject();

            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("File Not Found");
            c.printStackTrace();
            return;
        }

    }

    // load desired file
    public void loadFile(int FileNo) {
        if (FileNo == 1) {
            currentSave = save1;
        } else if (FileNo == 2) {
            currentSave = save2;
        } else if (FileNo == 3) {
            currentSave = save3;
        } else if (FileNo == 4) {
            currentSave = save4;
        } else {
            currentSave = save5;
        }

    }

    // KeyRelease
    public void movement1(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_W:
            upPressed = false;
            break;
        case KeyEvent.VK_S:
            downPressed = false;
            break;
        case KeyEvent.VK_A:
            leftPressed = false;
            break;
        case KeyEvent.VK_D:
            rightPressed = false;
            break;

        }
    }

    // KeyPress
    public void movement2(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_W:
            upPressed = true;
            break;
        case KeyEvent.VK_S:
            downPressed = true;
            break;
        case KeyEvent.VK_A:
            leftPressed = true;
            break;
        case KeyEvent.VK_D:
            rightPressed = true;
            break;

        }
    }

    // Setting theXursor
    public void setCursor() {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("Res\\New Canvas.png").getImage(),
                new Point(0, 0), "custom cursor"));
    }

    // create All content Panes
    public void createPane() {
        createMainScreen();
    }

    // create Main screen
    public void createStartScreen() {
        startScreen = new JPanel();

        startScreen.setLayout(null);
        startScreen.add(new JLabel(new ImageIcon("Res\\background1.jpg")));
        startScreen.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public void createMainScreen() {
        JPanel MainScreen = new JPanel();
        MainScreen.setLayout(null);
    }

}
