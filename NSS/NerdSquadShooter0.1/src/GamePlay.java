import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePlay extends JFrame {

    private JPanel contentPane;
    // KeyBoardMovement control
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GamePlay frame = new GamePlay();
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
    public GamePlay() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                contentPane.add(new BulletMk1(200, 200, MouseInfo.getPointerInfo().getLocation().getX(),
                        MouseInfo.getPointerInfo().getLocation().getY()));
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        

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
        
        ActionListener taskPerformer = new ActionListener() {

            // Any method here will be called once 60 times a second
            public void actionPerformed(ActionEvent evt) {
                // only checks if you are playing a level, thus shop and menu do
                // not count
                // if(upPressed){
                // changeBackground();
                // }
                Component[] jo = contentPane.getComponents();
                
                for(Component ko: jo){
                    if (ko instanceof BulletMk1){
                        ((BulletMk1)ko).move();
                    }
                    
                }

               

            }
        };
        new Timer(1000 / 500, taskPerformer).start();// timer calls every frame
    }

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

}
