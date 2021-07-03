/*
Programmed by Lucia Guintu
Due Date: 19 January 2016

This is the main program that calls the other classes. It stores the constructor which creates a JFrame, a method for double buffering, drawing method, main method which calls the JFrame,
and the main mouse handler for all the mouse actions the player makes. It also stores the restart method incase the player wishes to restart the game after game over and calls the sound
class for background music.

This game tests the players multiplication abilities from 0 to 12. The player will use a mouse to click the right answer. If the player clicks the wrong answer 3 times, the player loses.
However, if the player clicks the right answer 8 times before clicking it 3, the player will win.

---READ BEFORE RUNNING---
It is best if the program is run through the jar file because Ready to Program With Java cannot handle it. It causes a java.lang.OutOfMemoryError
 */
package cpt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame
{

    //rounded rectangles for buttons
    //Double Buffering & images
    Image dbImage, background, bu1, bu2, bu3;
    Graphics dbg;

    //Point referencing click of mouse
    Point pos;

    //booleans
    boolean menuStarted = true;
    boolean gameStarted = false;
    boolean helpStarted = false;
    boolean gameOver = false;
    boolean rw[] [] = new boolean [3] [2]; //three buttons all false, column 1 contains if right, column 2 contains if wrong
    //variable for screen size
    int GWIDTH = 1255,
	GHEIGHT = 830;
    //dimension of GWIDTH*GHEIGHT
    Dimension screenSize = new Dimension (GWIDTH, GHEIGHT);

    //restart button for end of game
    Rectangle retry = new Rectangle (25, 765, 150, 50);
    //class
    Game game = new Game (rw, pos);
    Menu menu = new Menu (menuStarted, helpStarted, gameStarted, pos);
    Help help = new Help (helpStarted, pos);

    //count for music so it doesn't replay over again
    int count = 0, count2 = 0;

    //create constructior to spawn window
    public Main ()
    {
	//starts main sound
	Sound.MAIN.loop ();

	this.setTitle ("Math Wars"); //title of the game
	this.pack ();
	this.setSize (screenSize);
	this.setResizable (false);
	this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo (null);
	this.setVisible (true);

	this.addMouseListener (new MouseHandler ());
	this.addMouseMotionListener (new MouseHandler ());

    }


    //method to restart whole game, leads back to main menu
    public void restart ()
    {
	Main.this.dispose ();
	Main main = new Main ();
	//end gameover sound
	Sound.OVER.stop ();
	//restart main sound
	Sound.MAIN.loop ();
    }


    //paint for double buffering
    public void paint (Graphics g)
    {
	dbImage = createImage (getWidth (), getHeight ());
	dbg = dbImage.getGraphics ();
	draw (dbg);
	g.drawImage (dbImage, 0, 0, this);
    }


    //method for drawing each page
    public void draw (Graphics g)
    {
	//draws menu
	if (menuStarted == true)
	{
	    menu.draw (g);
	} //draws help screen
	else if (helpStarted == true)
	{
	    help.draw (g);
	} //draws game
	else if (gameStarted == true)
	{
	    if (count == 0) //if never called before
	    {
		//stop main menu sound and start gameplay sound
		Sound.MAIN.stop ();
		Sound.GAME.loop ();
		//add to count to make sure sound doesn't restart over again
		count++;
	    }
	    ImageIcon b = new ImageIcon (getClass ().getClassLoader ().getResource ("Images/background.png"));
	    background = b.getImage ();
	    g.drawImage (background, 0, 0, this);
	    game.draw (g);
	}

	repaint ();
    }


    //main method, calls main frame
    public static void main (String[] args)
    {
	Main main = new Main ();

    }


    //method for mouse handler
    public class MouseHandler extends MouseAdapter implements MouseListener, MouseMotionListener
    {

	public void mouseMoved (MouseEvent e)
	{
	    menu.mouseMoved (e);
	    help.mouseMoved (e);

	}

	public void mouseClicked (MouseEvent e)
	{
	    pos = e.getPoint ();
	    //calls mouse method for menu
	    if (menuStarted == true)
	    {
		menu.mouseClicked (e);
		menuStarted = menu.getMenuStart ();
		helpStarted = menu.getHelpStart ();
		gameStarted = menu.getGameStart ();
	    } //calls mouse method for help screen
	    else if (helpStarted == true)
	    {
		help.mouseClicked (e);
		menuStarted = help.getMenuStart ();
		helpStarted = help.getHelpStart ();
		gameStarted = help.getGameStart ();
	    } //calls mouse method after gameover
	    else if (gameOver == true)
	    {
		//stop gameplay sound and start gameover sound
		if (count2 == 0)
		{
		    Sound.GAME.stop ();
		    Sound.OVER.loop ();
		    count2++;
		}
		if (retry.contains (pos))
		{
		    restart ();
		}
	    }
	}

	//MOUSE METHODS FOR MAIN GAME
	public void mousePressed (MouseEvent e)
	{
	    if (gameStarted == true)
	    {
		menuStarted = false;
		helpStarted = false;
		game.mousePressed (e);
	    }
	}

	public void mouseReleased (MouseEvent e)
	{
	    if (gameStarted == true)
	    {
		menuStarted = false;
		helpStarted = false;
		game.mouseReleased (e);
		gameOver = game.getGameOver ();

	    }
	}

	public void mouseDragged (MouseEvent e)
	{
	}
    }
}
