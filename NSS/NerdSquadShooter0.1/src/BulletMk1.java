import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BulletMk1 extends JLabel {
    double thisX, thisY, targetX, targetY;
    double ratio;
    boolean Xratio, Xnegative, Ynegative, bothNegative;

    BulletMk1(double thisX, double thisY, double targetX, double targetY) {
        this.setBounds((int) thisX, (int) thisY, 8, 8);
        this.setIcon(new ImageIcon("Res\\bullet.png"));

        this.thisX = thisX;
        this.thisY = thisY;
        this.targetX = targetX;
        this.targetY = targetY;
        if (targetX < thisX && targetY < thisY) {
            bothNegative = true;
        } else {
            bothNegative = false;
        }

        if ((targetX - thisX) > (targetY - thisY)) {
            ratio = (targetY - thisY) / (targetX - thisX);
            if(ratio >= 1) {
                
            }
            Xratio = true;

        } else {
            Xratio = false;
            ratio = (targetX - thisX) / (targetY - thisY);

        }

        if (targetX < thisX) {
            Xnegative = true;
        } else {
            Xnegative = false;
        }
        if (targetY < thisY) {
            Ynegative = true;
        } else {
            Ynegative = false;
        }
        
        if(Math.abs(ratio)>1){
            /*if(Xratio){
                Xratio=false;
            }else{
                Xratio=true;
            }
            ratio = 1/ratio;*/
        }
        System.out.println(ratio);
        System.out.println(thisX + " "+ targetX + " , "+ thisY +" " + targetY );

    }

    public void move() {
        if (bothNegative) {
            if(Xratio){
                thisX-=1;
                thisY-=ratio;
            }else{
                
                thisY-=1;
                thisX-=ratio;
            }
            
            
        } else if (Xratio) {

            if (Xnegative) {
                thisX -= 1;
            } else {
                thisX += 1;
            }

            thisY += ratio;

        } else {
            if (Ynegative) {
                thisY -= 1;
            } else {
                thisY += 1;
            }

            thisX += ratio;

        }
        this.setLocation((int) thisX, (int) thisY);
        
        
        

    }

}
