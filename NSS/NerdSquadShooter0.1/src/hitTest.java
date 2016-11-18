import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class hitTest extends JFrame {

    private JPanel contentPane;
    private int spawnrate = 0;
    private Component[] jo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    hitTest frame = new hitTest();
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
    public hitTest() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                contentPane.add(new Bullet(200, 200, contentPane.getMousePosition().getX(),
                        contentPane.getMousePosition().getY()));
                jo = contentPane.getComponents();
                repaint();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        ActionListener taskPerformer = new ActionListener() {

            // Any method here will be called once 60 times a second
            public void actionPerformed(ActionEvent evt) {
                // only checks if you are playing a level, thus shop and menu do
                // not count
                // if(upPressed){
                // changeBackground();
                // }
                if (spawnrate > 120) {
                    spawnrate -= 120;
                    if(contentPane.getMousePosition()!= null && contentPane.getMousePosition().getX()>0 &&contentPane.getMousePosition().getY()>0  ){
                        JLabel mo = new JLabel();
                        mo.setIcon(new ImageIcon("Res\\zombie.gif"));
                        mo.setBounds((int) contentPane.getMousePosition().getX(), (int) contentPane.getMousePosition().getY(), 32, 32);
                        contentPane.add(mo);
                        repaint();
                    }
                    
                }
                spawnrate++;

                if (jo != null) {
                    for (Component ko : jo) {
                        if (ko instanceof Bullet) {
                            ((Bullet) ko).Move();
                            if(((Bullet) ko).Destroy()){
                                contentPane.remove(ko);
                                
                            }
                        }
                    }
                }

            }
        };
        new Timer(1000 / 60, taskPerformer).start();// timer calls every frame
    }

}
