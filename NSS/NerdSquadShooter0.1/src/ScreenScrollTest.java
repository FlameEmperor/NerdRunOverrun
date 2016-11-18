import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScreenScrollTest extends JFrame {

    private JPanel contentPane;
    private int ChangeProgress=-1;
    private  JLabel transi;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ScreenScrollTest frame = new ScreenScrollTest();
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
    public ScreenScrollTest() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                addProgress();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 971, 512);
        contentPane = new JPanel();
        contentPane.setBackground(Color.YELLOW);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
       
        
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
                    //removeTransi();
                    getContentPane().remove(transi);
                    revalidate();
                    repaint();
                    ChangeProgress--;
                }

                

            }
        };
        new Timer(1000 / 60, taskPerformer).start();// timer calls every frame
    }
    
    public void MoveTransi() {
        
        transi.setLocation(transi.getX() + 20 , 0);
        revalidate();
        repaint();
        

    }
    
    public void addProgress(){
        ChangeProgress=142;
        transi = new JLabel("New label");
        transi.setBounds(-2900, 0, 2900, 512);
        transi.setIcon(new ImageIcon("Res\\SSHOOTEr.png"));
        getContentPane().add(transi, 0);
    }
    
    

}
