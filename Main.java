import javax.swing.*;
import java.awt.*;
//any component name which statrs with j belongs to swing
public class Main {
    public static void main(String[]args) {
        JFrame f = new JFrame("Snake Game");//to make frame//availabe in swing package
        f.setBounds(10, 10, 905, 700);//x-distance from left,y-from top
         f.setResizable(false);//permission to set size of screen
         //this function makes jframe visible
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//TO make user exit program manually by clicking close button.
         f.setBackground(Color.darkGray);//here Color-class from package awt,darkGray-class or static varible
         GamePlay gameplay =new GamePlay();
         f.add(gameplay);/*//add function is used to add  (class)components (objects) in jframe.here add function takes
         only component class obj,so we need to make gameplay as component class subclass.so extend gameplay
         with component class.it takes only objects argument*/
        f.setVisible(true);
    }
}
