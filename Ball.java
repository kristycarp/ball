//Kristy Carpenter, Computer Science II, Fall 2014, Section C (4th period)
//Assignment 4--Ball Option
//
//This program makes a ball move in a pop-up window according to user specifications. When it hits
//the boundary of the panel, it bounces off and changes to a random color. Through the use of a
//scanner, the user controls the dimensions of the panel, the initial position of the ball, and the
//initial x and y velocities of the ball. If statements and method calling with parameters are used
//to eliminate redundancy, and a for loop and if/else statements are used to repeatedly draw the
//ball in the next correct position.

import java.awt.*; //for drawingpanel
import java.util.*; //for scanner

public class Ball
{
   /**
     *This constant controls rate at which the screen catches up with the program
     */
   public static final int FRAMERATE = 40;
   
   /**
     *This constant is the diameter of the ball
     */
   public static final int BALL_SIZE = 5;
   
   /**
     *This constant is the number of times the ball is drawn when the program runs
     */
   public static final int NUMBER_TO_LOOP = 10000;
   
   /**
     *this is the main method, where the program begins
     *
     *@param args
     */  
   public static void main(String[]args)
   {
      intro();
      Scanner input = new Scanner(System.in);
      int panelWidth = getUserInput("panel size", "wide", input);
      int panelHeight = getUserInput("panel size", "tall", input);
      int xPosition = getUserInput("position", "X position", input);
      int yPosition = getUserInput("position", "Y position", input);
      int xVelocity = getUserInput("velocity", "X velocity", input);
      int yVelocity = getUserInput("velocity", "Y velocity", input);
      DrawingPanel panel = new DrawingPanel(panelWidth, panelHeight);
      Graphics g = panel.getGraphics();
      panel.setBackground(Color.WHITE);
      drawBall(xPosition, yPosition, xVelocity, yVelocity, panelWidth, panelHeight, g, panel);
   }
   
   /**
     *This method prints the introductory statement
     */
   public static void intro()
   {
      System.out.println("This program will simulate a ball bouncing in a window");
   }
   
   /**
     *This method prints a prompt for the user and returns the user's input so it can be stored in
     *a variable. In order to eliminate redundancy in print statements, parameters are used.
     *
     *@param inputType - either panel size, position, or velocity (the type of input put in
     *by the user)
     *@param uniquePrompt - the unique portion of the general prompt (used to eliminate redundancy)
     *@param input - the scanner, passed in from the main method
     *
     *@return user input - the next integer typed by the user as a response to the prompt
     */
   public static int getUserInput(String inputType, String uniquePrompt, Scanner input)
   {
      if (inputType == "panel size")
      {
        System.out.print("How " + uniquePrompt + " would you like the panel (in pixels)? ");
        return input.nextInt();
      }
      else //must be position or velocity
      {
         System.out.print("What is the initial " + uniquePrompt + " of the ball? ");
         return input.nextInt();
      }
   }
   
   /**
     *This method draws the ball "moving". The ball is first drawn in the specified place in black,
     *then its position is changed according to the given velocity. If the ball is touching the
     *border of the panel, its velocity changes sign, which makes it "bounce" correctly according
     *to physics, and its color also changes to a random color. The panel.sleep statement allows
     *the screen to catch up with the code.
     *
     *@param xPosition - the initial x position of the ball, given by the user
     *@param yPosition - the initial y positoin of the ball, given by the user
     *@param xVelocity - the intial x velocity of the ball, given by the user
     *@param yVelocity - the initial y velocity of the ball, given by the user
     *@param panelWidth - the width of the panel, given by the user
     *@param panelHeight - the height of the panel, given by the user
     *@param g - the graphics context, passed from main
     *@param panel - the drawingpanel, passed from main
     */
   public static void drawBall(int xPosition, int yPosition, int xVelocity, int yVelocity, int panelWidth, int panelHeight, Graphics g, DrawingPanel panel)
   {
      int red = 0;
      int green = 0;
      int blue = 0;
      Color ballColor = new Color(red, green, blue);
      g.setColor(ballColor);
      g.fillOval(xPosition, yPosition, BALL_SIZE, BALL_SIZE);
      for (int counter = 1; counter <= NUMBER_TO_LOOP; counter++)
      {
         //g.setColor(Color.WHITE);
         //g.fillRect(0, 0, panelWidth, panelHeight); //clear the entire screen so the old ball dissapears
         int possibleXPosition = xPosition + xVelocity;//don't actually know if this will be the real position
         int possibleYPosition = yPosition + yVelocity;
         if (possibleXPosition < 0 || possibleXPosition > panelWidth - BALL_SIZE) //test to see if it's touching boundary
         {
            xVelocity = -xVelocity; //make it bounce
            xPosition = xPosition + xVelocity; //now the position can be changed for real
            yPosition = yPosition + yVelocity;
            red = (int)(Math.random() * 256);
            green = (int)(Math.random() * 256);
            blue = (int)(Math.random() * 256);
            Color newBallColor = new Color(red, green, blue); //the color of the ball has been randomized
            g.setColor(newBallColor);
            g.fillOval(xPosition, yPosition, BALL_SIZE, BALL_SIZE); //draw the ball in new position, new color
         }
         if (possibleYPosition < 0 || possibleYPosition > panelHeight - BALL_SIZE) //test to see if it's touching boundary
         {
            yVelocity = -yVelocity; //make it bounce
            xPosition = xPosition + xVelocity; //now the position can be changed for real
            yPosition = yPosition + yVelocity;
            red = (int)(Math.random() * 256);
            green = (int)(Math.random() * 256);
            blue = (int)(Math.random() * 256);
            Color newBallColor = new Color(red, green, blue); //the color of the ball has been randomized
            g.setColor(newBallColor);
            g.fillOval(xPosition, yPosition, BALL_SIZE, BALL_SIZE);//draw the ball in new position, new color
         }
         else //if the ball isn't touching any sides
         {
            xPosition = xPosition + xVelocity; //position can be changed for real
            yPosition = yPosition + yVelocity;
            Color newBallColor = new Color(red, green, blue); //stays the same color as it was last time (red, green, blue are the same)
            g.setColor(newBallColor);
            g.fillOval(xPosition, yPosition, BALL_SIZE, BALL_SIZE);
         }
         panel.sleep(1000 / FRAMERATE); //so the screen can catch up with the program
      }
   }
}