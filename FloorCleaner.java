// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102/112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2022T1, Assignment 7
 * Name: Tom Green
 * Username: greenthom
 * ID: 300536064
 */

import ecs100.*;
import java.awt.Color;

/** Runs a simulation of a robot vacuum cleaner that moves around a floor area,
 *      changing to a new random direction every time it hits the edge of the
 *      floor area.
 */
public class FloorCleaner{

    // Constants for drawing the robot and the floor.
    public static final double DIAM = 60;  //diameter of the robot.
    public static final double LEFT = 50;  // borders of the floor area.
    public static final double TOP = 50;
    public static final double RIGHT = 550;
    public static final double BOT = 420;

    /* Simulation loop.
     * The method should draw a dirty floor (a gray rectangle), and then
     * create one robot (core) or two robots (completion) and make them run around for ever.
     * Each time step, each robot will erase the "dirt" under it, and then
     *  move forward a small amount.
     * After it has moved, the program should ask for the robot's
     *  position and check the position against the edges of the floor area.
     * If it has gone over the edge, it will make the robot step back onto the floor
     *  and change its direction.
     * For the completion, it will also check if the robots have hit each other, and
     *  if so, make them both back off and change direction
     * 
     * Hint: A robot should start in a "safe" initial position (not over the edge):
     *  its x position should be between  LEFT+DIAM/2 and RIGHT-DIAM/2
     *  its y position should be between  TOP+DIAM/2 and BOT-DIAM/2
     * Hint: For the completion, you have to make sure that starting positions of
     *  the robots are not on top of each other (otherwise they get "stuck" to each other!)
     */
    public void cleanFloorCore(){
    double minX = LEFT+(DIAM/2);
    double maxX = RIGHT-(DIAM/2);
    double minY = TOP/1.8;
    double maxY = BOT-(DIAM/2);
    UI.setColor(Color.black);
    UI.drawRect(LEFT, TOP, RIGHT-LEFT, BOT-TOP/25);
    UI.setColor(Color.gray);
    UI.fillRect(LEFT, TOP, RIGHT-LEFT, BOT-TOP/25);
    Color color = Color.getHSBColor((float)(Math.random()), 1.0f, 1.0f);
    Robot R1 = new Robot(DIAM, 100, 100, color);
    R1.draw();
    
    while(true){
        R1.erase();
        R1.step();
        R1.draw();  
        if(R1.getX()>=maxX || R1.getX() <= minX ||R1.getY() >= maxY || R1.getY() <= minY){
            R1.changeDirection();
            R1.draw(); 
        }
        UI.sleep(2);
    }
 }        
    
  
public void cleanFloorCompletion(){
    double minX = LEFT+(DIAM/2);
    double maxX = RIGHT-(DIAM/2);
    double minY = TOP/1.8;
    double maxY = BOT-(DIAM/2);
    UI.setColor(Color.black);
    UI.drawRect(LEFT, TOP, RIGHT-LEFT, BOT-TOP/25);
    UI.setColor(Color.gray);
    UI.fillRect(LEFT, TOP, RIGHT-LEFT, BOT-TOP/25);
    Color color = Color.getHSBColor((float)(Math.random()), 1.0f, 1.0f);
    Robot R1 = new Robot(DIAM, 100, 100, color);
    Robot R2 = new Robot(DIAM, 300, 300, color);
    R1.draw();
    R2.draw();
    
    while(true){
        R1.erase();
        R1.step();
        R1.draw();
        R2.erase();
        R2.step();
        R2.draw();
        if(R1.getX()>maxX || R1.getX() < minX || R1.getY() > maxY || R1.getY() < minY){
            R1.changeDirection();
            R1.draw();
        }
        if(R2.getX()>maxX || R2.getX() < minX || R2.getY() > maxY || R2.getY() < minY){
            R2.changeDirection();
            R2.draw();
        }
        if(Math.hypot(R1.getX()-R2.getX(), R1.getY()-R2.getY()) < DIAM){
            R1.changeDirection();
            R1.draw();
            R2.changeDirection();
            R2.draw();
            
        }
        UI.sleep(2);
    }
}


 
public boolean RobotCollide(Robot R1, Robot R2) {
    //if(R1.getX() + DIAM == R2.getX()|| R2.getX() + DIAM == R2.getX());
    //if(R1.getY() + DIAM == R2.getY()|| R2.getY() + DIAM == R2.getY());
   if(R1.getX() + DIAM/2 < R2.getX() - DIAM/2 || R1.getX()-DIAM/2 > R2.getX()+DIAM/2) return false;
   if(R1.getY() + DIAM/2 < R2.getY() - DIAM/2 || R1.getY()-DIAM/2 > R2.getY()+DIAM/2) return false;
   return true;
}


    //------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI(){
        UI.addButton("Core", this::cleanFloorCore);
        UI.addButton("Completion", this::cleanFloorCompletion);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(650,500);
        UI.setDivider(0);
    }    

    // Main
    public static void main(String[] arguments){
        FloorCleaner fc = new FloorCleaner();
        fc.setupGUI();
    }    

}
