/*
This class is called to show the help screen.
It tells the user the instructions of the game.
The user may wish to go back to the main menu or play the game by clicking a button with their mouse
*/

package cpt;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Help {

    boolean menuStarted = false, gameStarted = false, menuHover, playHover, helpStarted;
    Image help;
    Rectangle menuButton = new Rectangle(25, 765, 150, 50);
    Rectangle playButton = new Rectangle(1080, 765, 150, 50);
    Point pos;

    public Help(boolean helpStart, Point pos) {
        this.helpStarted = helpStart;
        this.pos = pos;
    }

    //methods to return booleans
    public boolean getGameStart() {
        return gameStarted;

    }

    public boolean getMenuStart() {
        return menuStarted;

    }

    public boolean getHelpStart() {
        return helpStarted;
    }

    //method to draw help screen
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        ImageIcon b = new ImageIcon(getClass().getClassLoader().getResource("Images/InstructionsMenu.png"));
        help = b.getImage();
        g.drawImage(help, 0, 20, null);

        b = new ImageIcon(getClass().getClassLoader().getResource("Images/Help.gif"));
        help = b.getImage();
        g.drawImage(help, 304, 108, 623, 369, null);

        g.setColor(new Color(208, 74, 86));
        g2.fill(menuButton);
        g2.fill(playButton);

        g.setFont(new Font("Courier New", Font.PLAIN, 30));
        //if player hover overs a button, change colour of the letter
        if (menuHover == false) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.CYAN);
        }
        g.drawString("Back", 65, 795);

        if (playHover == false) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.CYAN);
        }
        g.drawString("Play", 1120, 795);
    }

    public void mouseMoved(MouseEvent e) {
        //method to check if mouse is hovering on the buttons
        int mx = e.getX();
        int my = e.getY();
        if (mx > menuButton.x && mx < menuButton.x + menuButton.width
                && my > menuButton.y && my < menuButton.y + menuButton.height) {
            menuHover = true;
        } else {
            menuHover = false;
        }
        if (mx > playButton.x && mx < playButton.x + playButton.width
                && my > playButton.y && my < playButton.y + playButton.height) {
            playHover = true;
        } else {
            playHover = false;
        }

    }

    public void mouseClicked(MouseEvent e) {
        pos = e.getPoint();
        //check if player clicks one of the buttons, if not keep the help screen the same
        if (menuButton.contains(pos)) //if clicked, goes back to menu
        {
            menuStarted = true;
            gameStarted = false;
            helpStarted = false;
        } else if (playButton.contains(pos)) //if clicked, starts game
        {
            gameStarted = true;
            menuStarted = false;
            helpStarted = false;
        } else //if clicked somewhere else, do nothing
        {
            helpStarted = true;
            menuStarted = false;
            gameStarted = false;
        }

    }

}
