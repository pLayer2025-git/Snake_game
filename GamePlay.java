import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//ImageIcon belongs to java swing.icon in cts is visiual represnetation/symbol of (text)program,files or function
//ImageIcon used to add image to components
public class GamePlay extends JPanel implements ActionListener,KeyListener {
    // The JPanel class is a subclass of javax.swing.JComponent.
    // This means it inherits the properties and methods of JComponent, which in turn is a subclass of
    // java.awt.Container, and java.awt.Component.
    private ImageIcon titleImage;//create title image obj of class image icon for inserting title image
    private int[] sankexlength = new int[750];
    private int[] sankeylength = new int[750];
    private int lengthofsnake = 3;

    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightMouth;
    private ImageIcon upMouth;
    private ImageIcon leftMouth;
    private ImageIcon downMouth;
    private ImageIcon snakeImage;
    private ImageIcon enemyImage;

    private int[] enemyxpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
            600,625,650,675,700,725,750,775,800,825,850};
    private int[] enemyypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
            600,625};
    private Random random=new Random();
     private int xpos= random.nextInt(34);
     private int ypos=random.nextInt(23);

    private Timer timer;
    private int delay = 50;
    private int move = 0;
    private int score=0;
    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {//define snake body,title,etc
        // define border for the title image
        if (move == 0) {
            sankexlength[0] = 100;
            sankeylength[0] = 100;
            sankexlength[1] = 75;
            sankeylength[1] = 100;
            sankexlength[2] = 50;
            sankeylength[2] = 100;
        }
        g.setColor(Color.white);//color of border
        g.drawRect(24, 10, 851, 55);//size of border
        // define border for the gameplay
        titleImage = new ImageIcon("snaketitle.jpg");//here snaketitle file is in current folder.
        titleImage.paintIcon(this, g, 25, 11);//here 25 is length from (x)left and (y)11 is from top,g is graphic argument and this is component graphics
        //this function adds image to specified location
        g.setColor(Color.white);//color of border
        g.drawRect(24, 74, 851, 577);//size of border
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 573);
        //draw the scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Scores : "+score,750,30);
        g.drawString("SnakeLength  : "+lengthofsnake,750,50);
        //g.drawString("delay : "+delay,30,30);
        rightMouth = new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this, g, sankexlength[0], sankeylength[0]);
        for (int a = 0; a < lengthofsnake; a++) {
            if (a == 0 && right) {
                rightMouth = new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(this, g, sankexlength[a], sankeylength[a]);
            }
            if (a == 0 && left) {
                leftMouth = new ImageIcon("leftmouth.png");
                leftMouth.paintIcon(this, g, sankexlength[a], sankeylength[a]);
            }
            if (a == 0 && up) {
                upMouth = new ImageIcon("upmouth.png");
                upMouth.paintIcon(this, g, sankexlength[a], sankeylength[a]);
            }
            if (a == 0 && down) {
                downMouth = new ImageIcon("downmouth.png");
                downMouth.paintIcon(this, g, sankexlength[a], sankeylength[a]);
            }
            if (a != 0) {
                snakeImage = new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this, g, sankexlength[a], sankeylength[a]);
            }

        }
            enemyImage=new ImageIcon("enemy.png");
            enemyImage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);

            if(enemyxpos[xpos]==sankexlength[0] && enemyypos[ypos]==sankeylength[0])
            {   score++;
                lengthofsnake++;
                xpos= random.nextInt(34);
                ypos= random.nextInt(23);
            }
            //enemyImage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);

            for(int b=1;b<lengthofsnake;b++)
            {
                if(sankexlength[b]==sankexlength[0] && sankeylength[b]==sankeylength[0])
                {
                    right=false;
                    left=false;
                    up=false;
                    down=false;
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("arial",Font.BOLD,50));
                    g.drawString("Game Over!",300,300);

                    g.setFont(new Font("arial",Font.BOLD,30));
                    g.drawString("Press space to restart",350,340);
                }
            }
        g.dispose();
    }
    public void actionPerformed(ActionEvent eq) {

        if (right) {
            for (int i = lengthofsnake-1; i>=0; i--) {
                sankeylength[i+1] = sankeylength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    sankexlength[i] = sankexlength[i] + 25;
                }
                else {
                     sankexlength[i]=sankexlength[i-1];
                }
                if(sankexlength[i]>850)
                {
                    sankexlength[i]=25;
                }
            }
            repaint();
        }
        if (left) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                sankeylength[i + 1] = sankeylength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    sankexlength[i] = sankexlength[i]-25;
                }
                else {
                    sankexlength[i]=sankexlength[i-1];
                }
                if(sankexlength[i]<25)
                {
                    sankexlength[i]=850;
                }
            }
            repaint();
        }
        if (up) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                sankexlength[i+1] = sankexlength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    sankeylength[i] = sankeylength[i] - 25;
                }
                else {
                    sankeylength[i]=sankeylength[i-1];
                }
                if(sankeylength[i]<75)
                {
                    sankeylength[i]=625;
                }
            }
            repaint();
        }
        if (down) {
            for (int i = lengthofsnake - 1; i >= 0; i--) {
                sankexlength[i+1] = sankexlength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    sankeylength[i] = sankeylength[i] + 25;
                }
                else {
                    sankeylength[i]=sankeylength[i-1];
                }
                if(sankeylength[i]>625)
                {
                    sankeylength[i]=75;
                }
            }
            repaint();
        }

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            move++;
            if (!left) {
                right = true;
            } else {
                left = true;
                right = false;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            move++;
            if (!right) {
                left = true;
            } else {
                right = true;
                left = false;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            move++;
            if (!down) {
                up= true;
            } else {
                down = true;
                up = false;
            }
            left = false;
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            move++;
            if (!up) {
                down = true;
            } else {
                up = true;
                down = false;
            }
            left = false;
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {   score=0;
            move=0;
            lengthofsnake=3;
            repaint();
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }


}
