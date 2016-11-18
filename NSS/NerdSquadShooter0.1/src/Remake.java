import java.awt.BorderLayout;
import java.awt.EventQueue;
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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*Note To self

1:revalidate() will redraw the container itself, thus required after setContentPane
2:content.add(new JLabel(new ImageIcon("image"))); will only work if the content pane has no more than one label

*/

public class Remake extends JFrame {

    private JPanel nextScreen, startScreen, startScreen2;
    private static SaveType currentSave, save1, save2, save3, save4, save5;
    private JLabel lblNewLabel, transi;

    // KeyBoardMovement control
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    // Boolean to make sure you are playing the level for the game to run
    private boolean inGame = false;
    private boolean transiting = false;

    // int to check current Screen
    private int currentScreen = 0;
    private int ChangeProgress=-1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Remake frame = new Remake();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Remake() {
        //game looks bad when size is changed
        //this makes it impossible for the user to resize
        this.setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 833, 551);

        setCursor();
        createAllPane();
        setContentPane(startScreen);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {

                //lblNewLabel.setLocation(55, 44);
                if (!transiting){
                    addProgress();
                }
                

            }
        });

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
                // if(upPressed){
                // changeBackground();
                // }
                if (ChangeProgress > 0) {
                    ChangeProgress--;
                    MoveTransi();
                } else if (ChangeProgress == 0) {
                    
                    getContentPane().remove(transi);                    
                    revalidate();
                    repaint();                    
                    ChangeProgress--;
                    if(getContentPane().equals(startScreen)){
                        nextScreen=startScreen2;
                    }else{
                        nextScreen=startScreen;
                    }
                    
                    nextScreen();
                    transiting = false;
                }

               

            }
        };
        new Timer(1000 / 60, taskPerformer).start();// timer calls every frame

    }

    public void createAllPane() {
        createStartPane();
        createStartPane2();
    }

    public void createStartPane() {
        startScreen = new JPanel();
        startScreen.setBorder(new EmptyBorder(0, 0, 0, 0));
        startScreen.setLayout(null);
        JLabel label = new JLabel(new ImageIcon("Res\\Touhou-Wallpaper-High-Quality-HD.png"));
        label.setBounds(0, 0, 833, 512);
        
        startScreen.add(label);

    }

    public void createStartPane2() {
        startScreen2 = new JPanel();
        startScreen2.setBorder(new EmptyBorder(0, 0, 0, 0));
        startScreen2.setLayout(null);

        lblNewLabel = new JLabel("New labelsagdiuygufy kgdgv kgdgv  ");
        lblNewLabel.setBounds(296, 46, 527, 14);
        startScreen2.add(lblNewLabel);
        JLabel label = new JLabel(new ImageIcon("Res\\wallapper.jpg"));
        label.setBounds(0, 0, 833, 512);
        startScreen2.add(label);

    }

    // Setting the cursor
    public void setCursor() {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("Res\\New Canvas.png").getImage(),
                new Point(0, 0), "custom cursor"));
    }

    // KeyRelease
    public void movement1(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_W:
            upPressed = false;
            changeBackground();
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

    public void changeBackground() {
        if (currentScreen == 0) {

            currentScreen = 1;
            //transition();
            

        } else if (currentScreen == 1) {

            currentScreen = 0;
            //transition();

        } else if (currentScreen == 2) {

            currentScreen = 0;

        }
        revalidate();
        repaint();
    }

    public void addProgress(){
        ChangeProgress=142;
        transi = new JLabel("New label");
        transi.setBounds(-2900, 0, 2900, 512);
        transi.setIcon(new ImageIcon("Res\\SSHOOTEr.png"));
        getContentPane().add(transi, 0);
        transiting=true;
    }
    

    public void MoveTransi() {
        transi.setLocation(transi.getX() + 20 , 0);
        revalidate();
        repaint();
        

    }
    
    public void removeTransi(){
        
    }

    public void nextScreen() {
        setContentPane(nextScreen);
        revalidate();
        repaint();
    }

    
    

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

}
