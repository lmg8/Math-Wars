/*
This is the first thing that is shown when the player runs the game.
Its the main menu of the game. It has three options of Play, Help, and Exit.
Clicking the play button will start the game, the help button will lead to the instruction screen,
the exit button will end the game.
*/

package cpt;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Menu {

    boolean startHover, helpHover, quitHover, gameStarted, helpStarted, menuStarted;
    Image menu;
    Rectangle startButton = new Rectangle(1063, 100, 192, 50);
    Rectangle helpButton = new Rectangle(1000, 200, 192, 50);
    Rectangle quitButton = new Rectangle(1063, 300, 192, 50);
    Point pos;

    public Menu(boolean menuStart, boolean helpStart, boolean gameStart, Point pos) {
        this.menuStarted = menuStart;//call menustarted from main
        this.helpStarted = helpStart;
        this.gameStarted = gameStart;
        this.pos = pos;
    }

    //getters
    public boolean getGameStart() {
        return gameStarted;
    }

    public boolean getMenuStart() {
        return menuStarted;
    }

    public boolean getHelpStart() {
        return helpStarted;
    }

    public void draw(Graphics g) {

        ImageIcon b = new ImageIcon(getClass().getClassLoader().getResource("Images/Menu.gif"));
        menu = b.getImage();
        g.drawImage(menu, 0, 0, 1255, 890, null);

        //menu bar
        g.setColor(new Color(247, 147, 29));
        g.fillRect(1000, 0, 255, 830);

        //buttons
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(new Font("Times New Roman", Font.PLAIN, 75));
        //if hovering over a button, change colour
        if (!startHover) {
            g.setColor(new Color(253, 250, 219));
        } else {
            g.setColor(new Color(247, 147, 29));

        }
        g2.fill(startButton);
        if (!startHover) {
            g.setColor(new Color(247, 147, 29));
        } else {
            g.setColor(new Color(253, 250, 219));

        }
        g.drawString("Play", 1063, 150);

        if (!quitHover) {
            g.setColor(new Color(253, 250, 219));
        } else {
            g.setColor(new Color(247, 147, 29));

        }
        g2.fill(quitButton);
        if (!quitHover) {
            g.setColor(new Color(247, 147, 29));
        } else {
            g.setColor(new Color(253, 250, 219));

        }
        g.drawString("Exit", 1063, 350);
        if (!helpHover) {
            g.setColor(new Color(253, 250, 219));
        } else {
            g.setColor(new Color(247, 147, 29));

        }
        g2.fill(helpButton);

        if (!helpHover) {
            g.setColor(new Color(247, 147, 29));
        } else {
            g.setColor(new Color(253, 250, 219));

        }
        g.drawString("Help", 1000, 250);
    }

    public void mouseMoved(MouseEvent e) {

        //check if cursor is hovering over a button
        int mx = e.getX();
        int my = e.getY();
        if (mx > startButton.x && mx < startButton.x + startButton.width
                && my > startButton.y && my < startButton.y + startButton.height) {
            startHover = true;
        } else {
            startHover = false;
        }
        if (mx > quitButton.x && mx < quitButton.x + quitButton.width
                && my > quitButton.y && my < quitButton.y + quitButton.height) {
            quitHover = true;
        } else {
            quitHover = false;
        }
        if (mx > helpButton.x && mx < helpButton.x + helpButton.width
                && my > helpButton.y && my < helpButton.y + helpButton.height) {
            helpHover = true;
        } else {
            helpHover = false;
        }

    }

    public void mouseClicked(MouseEvent e) {
        pos = e.getPoint();
        if (startButton.contains(pos))//starts game
        {
            menuStarted = false;
            helpStarted = false;
            gameStarted = true;
        } else if (helpButton.contains(pos)) //goes to help screen
        {
            menuStarted = false;
            gameStarted = false;
            helpStarted = true;
        } else if (quitButton.contains(pos)) //exits game
        {
            System.exit(0);
        } else //does nothing
        {
            menuStarted = true;
            helpStarted = false;
            gameStarted = false;
        }

    }
}
