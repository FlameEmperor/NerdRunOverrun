import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bullet extends JLabel {
    private double currentX, currentY, targetY, targetX, ratio, iniX, iniY;

    private boolean Xratio;
    private int life = 90;

    Bullet(double currentX, double currentY, double targetX, double targetY) {
        this.setBounds((int) currentX, (int) currentY, 8, 8);
        this.setIcon(new ImageIcon("Res\\bullet.png"));
        this.currentX = currentX;
        this.currentY = currentY;
        iniX = currentX;
        iniY = currentY;
        this.targetY = targetY;
        this.targetX = targetX;

        if (Math.abs((targetX - currentX)) > Math.abs((targetY - currentY))) {
            ratio = Math.abs((targetY - currentY) / (targetX - currentX));

            Xratio = false;
        } else {
            ratio = Math.abs((targetX - currentX) / (targetY - currentY));
            Xratio = true;
        }

    }

    public void Move() {
        if (Xratio) {
            if (iniX < targetX) {
                currentX += 2 * ratio;
            } else {
                currentX -= 2 * ratio;
            }

            if (iniY < targetY) {
                currentY += 2;
            } else {
                currentY -= 2;
            }
        } else {
            if (iniX < targetX) {
                currentX += 2;
            } else {
                currentX -= 2;
            }

            if (iniY < targetY) {
                currentY += 2 * ratio;
            } else {
                currentY -= 2 * ratio;
            }

        }

        this.setLocation((int) currentX, (int) currentY);
        life--;

    }

    public boolean Destroy() {
        if (life < 1) {
            return true;
        }else{
            return false;
        }
    }

}
