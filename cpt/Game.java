/*
This class is called for the game itself to run.
It mainly has the calculations in order to run the game, the drawings, and mouse events of the game screen. 
 */
package cpt;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;

public class Game {

    //variables for calculation
    int n1, n2, max = 12, min = 0, maxa = 144;
    int rand[] = new int[3];//array to store random numbers generated
    int pos[] = new int[3];//array to store position of random numbers
    boolean answer[] = new boolean[3];//array to store which pos is the right answer
    boolean gameover, w;//check if gameover, and if winner
    int score[] = new int[2];//array to store number times right and wrong 

    Point position;

    int x1 = 0, xd1, x2 = 250, xd2;//integers for movement of images
    Image kylo, bb8, rey, bu1, bu2, bu3, winner, loser;//images
    RoundRectangle2D b1, b2, b3;//buttons
    //restart button
    Rectangle retry = new Rectangle(25, 765, 150, 50);

    boolean rw[][] = new boolean[3][2];//array to store whether the person clicked the right button, if true = shows green button, false = red button

    public boolean getGameOver() {//returns gameover variable to main class
        return gameover;
    }

    public Game(boolean rw[][], Point pos) {
        this.rw = rw;
        this.position = pos;
        
        //call images
        ImageIcon a = new ImageIcon(getClass().getClassLoader().getResource("Images/Kylo.png"));
        kylo = a.getImage();
        ImageIcon c = new ImageIcon(getClass().getClassLoader().getResource("Images/BB8.png"));
        bb8 = c.getImage();
        ImageIcon d = new ImageIcon(getClass().getClassLoader().getResource("Images/Rey.png"));
        rey = d.getImage();

        ImageIcon b = new ImageIcon(getClass().getClassLoader().getResource("Images/b1.png"));
        bu1 = b.getImage();
        b1 = new RoundRectangle2D.Float(100, 620, b.getIconWidth(), b.getIconHeight(), 50, 50);

        b = new ImageIcon(getClass().getClassLoader().getResource("Images/b2.png"));
        bu2 = b.getImage();
        b2 = new RoundRectangle2D.Float(480, 650, b.getIconWidth(), b.getIconHeight(), 50, 50);

        b = new ImageIcon(getClass().getClassLoader().getResource("Images/b3.png"));
        bu3 = b.getImage();
        b3 = new RoundRectangle2D.Float(850, 620, b.getIconWidth(), b.getIconHeight(), 50, 50);

        b = new ImageIcon(getClass().getClassLoader().getResource("Images/Winner.png"));
        winner = b.getImage();

        b = new ImageIcon(getClass().getClassLoader().getResource("Images/Loser.png"));
        loser = b.getImage();

        //call randomly generated calculations
        calculation();

    }

    //methods for movement of images
    public void setXDirectBB8(int xdir) {
        xd2 = xdir;
    }

    public void setXDirectKylo(int xdir) {
        xd1 = xdir;
    }

    public void move() {
        x1 = x1 + xd1;
        x2 = x2 + xd2;
    }

    public void draw(Graphics g) {
        //if gameover is true show gameover images
        
        
        if (gameover == true) {
            if (w == true) {
                g.drawImage(winner, 0, 0, 1255, 830, null);
            } else {
                g.drawImage(loser, 0, 0, null);
            }

            g.setColor(Color.WHITE);
            g.fillRect(retry.x, retry.y, retry.width, retry.height);
            g.setColor(Color.RED);
            g.setFont(new Font("Courier New",Font.BOLD,25));
            g.drawString("Restart",retry.x+22,retry.y+30);
        } else if (gameover
                == false)//else show game
        {
            g.drawImage(kylo, x1, 380, null);
            g.drawImage(bb8, x2, 480, null);
            g.drawImage(rey, 1050, 405, null);

            Graphics2D g2 = (Graphics2D) g;

            //draw buttons, if pressed one, change the colour of button to red if wrong, change the colour of the button to green if right
            if (rw[0][0] == false && rw[0][1] == false) {
                g.drawImage(bu1, 100, 620, null);
            } else if (rw[0][0] == true) {
                g.drawImage(bu3, 100, 620, null);
            } else if (rw[0][1] == true) {
                g.drawImage(bu2, 100, 620, null);
            }
            if (rw[1][0] == false && rw[1][1] == false) {
                g.drawImage(bu1, 480, 650, null);
            } else if (rw[1][0] == true) {
                g.drawImage(bu3, 480, 650, null);
            } else if (rw[1][1] == true) {
                g.drawImage(bu2, 480, 650, null);
            }
            if (rw[2][0] == false && rw[2][1] == false) {
                g.drawImage(bu1, 850, 620, null);
            } else if (rw[2][0] == true) {
                g.drawImage(bu3, 850, 620, null);
            } else if (rw[2][1] == true) {
                g.drawImage(bu2, 850, 620, null);
            }

            g.setFont(new Font("Courier New", Font.BOLD, 100));
            g.setColor(new Color(17, 40, 51));

            //Draw questions
            g.drawString(n1 + "", 400, 240);
            g.drawString("x", 590, 240);
            g.drawString(n2 + "", 780, 240);
            //Draw answers to each button
            g.setFont(new Font("Courier New", Font.BOLD, 40));
            g.setColor(Color.WHITE);
            g.drawString(pos[0] + "", 225, 690);
            g.drawString(pos[1] + "", 605, 720);
            g.drawString(pos[2] + "", 975, 690);

        }

    }

    private void calculation() {
        for (int i = 0; i < 3; i++) {
            answer[i] = false;
        }//declare all answers as false

        //generate random numbers between 0 to 12
        n1 = (int) ((max - min + 1) * Math.random() + min);
        n2 = (int) ((max - min + 1) * Math.random() + min);

        //place answer in first spot and generate random numbers for the other two spots
        rand[0] = n1 * n2;
        do {
            rand[1] = (int) ((maxa - min + 1) * Math.random() + min);
        } while (rand[0] == rand[1]);
        do {
            rand[2] = (int) ((maxa - min + 1) * Math.random() + min);
        } while (rand[1] == rand[2] || rand[0] == rand[2]);

        //generate random position for the random numbers and answer
        pos[0] = rand[(int) ((2 + 1) * Math.random())];
        do {
            pos[1] = rand[(int) ((2 + 1) * Math.random())];
        } while (pos[0] == pos[1]);
        do {
            pos[2] = rand[(int) ((2 + 1) * Math.random())];
        } while (pos[1] == pos[2] || pos[0] == pos[2]);

        for (int check = 0; check < 3; check++) {
            if (pos[check] == rand[0]) {
                answer[check] = true;//find which position has the right answer
            }
        }
    }

    //method to check if win or lose
    public void checkWinner() {
        if (score[0] == 8 || score[1] == 3) {
            gameover = true;
            if (score[0] == 8) {
                w = true;
            }

        }
    }

    public void mousePressed(MouseEvent e) {

        position = e.getPoint();
        if (b1.contains(position)) {
            if (answer[0] == true) //if button 1 is right, 
            {
                //make colour of button green
                rw[0][0] = true;
                //move BB8 and Kylo
                setXDirectBB8(100);
                setXDirectKylo(100);
                //add to winning point
                score[0]++;
            } else if (answer[0] == false) //if not right
            {
                //make colour of button red
                rw[0][1] = true;
                //move Kylo, not BB8
                setXDirectBB8(0);
                setXDirectKylo(75);
                //add to losing point
                score[1]++;
            }
            //move the characters
            move();
            //make new calculation
            calculation();
        } else if (b2.contains(position)) {
            if (answer[1] == true) {
                rw[1][0] = true;
                setXDirectBB8(100);
                setXDirectKylo(100);
                score[0]++;

            } else if (answer[1] == false) {
                rw[1][1] = true;
                setXDirectBB8(0);
                setXDirectKylo(75);
                score[1]++;
            }
            move();
            calculation();
        } else if (b3.contains(position)) {
            if (answer[2] == true) {
                rw[2][0] = true;
                setXDirectBB8(100);
                setXDirectKylo(100);
                score[0]++;

            } else if (answer[2] == false) {
                rw[2][1] = true;
                setXDirectBB8(0);
                setXDirectKylo(75);
                score[1]++;
            }
            move();
            calculation();
        }
        //always check if there is a winner after clicking a button
        checkWinner();
    }

    public void mouseReleased(MouseEvent e) {

        //when mouse is released change colour of buttons back to original
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                rw[i][j] = false;
            }
        }

    }

}
