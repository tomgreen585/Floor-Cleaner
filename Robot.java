// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102/112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2022T1, Assignment 7
 * Name: Tom Green
 * Username: greenthom
 * ID: 3005360360
 */

import ecs100.*;
import java.awt.Color;

/** The robot is a circular vacuum cleaner than runs around
 * a floor, erasing any "dirt".
 * The parameters of the constructor should include the initial position,
 * and possibly its size and color.
 * It has methods to make it step and change direction:
 *  step() makes it go forward one step in its current direction.
 *  changeDirection() makes it go backward one step, and then turn to a new
 *     (random) direction.
 * It has methods to report its current position (x and y) with the
 *     getX() and getY() methods.
 * It has methods to erase and draw itself
 *  draw() will make it draw itself,
 *  erase() will make it erase itself (cleaning the floor under it also!)
 *
 * Hint: if the current direction of the robot is d (expressed in
 *  degrees clockwise from East), then it should step
 *     cos(d * pi/180) in the horizontal direction, and
 *     sin(d * pi/180) in the vertical direction.
 * Hint: see the Math class documentation!
 */

public class Robot{
    // Fields to store the state of the robot.
    
    public double DIAM;
    public double xpos;
    public double ypos;
    public double direction;
    public double xstep = 0;
    public double ystep = 0;
    public double Center;
    public Color color;
    
    /** Construct a new Robot object.
     *  set its direction to a random direction, 0..360
     */
    public Robot(double diam, double xpos, double ypos, Color color){
        this.xpos = xpos;
        this.ypos = ypos;
        this.DIAM = diam;
        this.color = color;
        this.direction = Math.random()*360;
    }

    // Methods to return the x and y coordinates of the current position 
    public double getX(){
        this.xpos = xpos;
        return this.xpos;
    }
    
    public double getY(){
        this.ypos = ypos;
        return this.ypos;
    }

    /** Step one unit in the current direction (but don't redraw) */
    public void step(){
        this.erase();
        UI.setColor(color);
        this.xstep = 1*Math.cos(this.direction * Math.PI/180);
        this.ystep = 1*Math.sin(this.direction * Math.PI/180);
        this.xpos += xstep;
        this.ypos += ystep;
        this.draw();
    }

    /** changeDirection: move backwards one unit and change direction randomly */
    public void changeDirection(){
        xpos = xpos - xstep;
        ypos = ypos - ystep;
        this.direction = Math.random()*360;
        if (this.direction<0){
            direction = 360 + direction;
        }
    }

    /** Erase the robot */
    public void erase(){
        UI.setColor(Color.white);
        UI.eraseOval(xpos - DIAM/2, FloorCleaner.TOP + ypos - DIAM/2, DIAM, DIAM); 
    }

    /** Draw the robot */
    public void draw(){
        UI.setColor(color);
        double left = xpos - DIAM/2;
        double top = FloorCleaner.TOP + ypos - DIAM/2;
        UI.fillOval(left, top, DIAM, DIAM);
        //couldnt hack the dot ignore my working its here because i wanna try it later
        //double dotx =(xpos + DIAM/2) + (0.4*this.DIAM)*Math.cos(this.direction*Math.PI/180);
        //double doty =(ypos + DIAM/2) +(0.4*this.DIAM)*Math.sin(this.direction*Math.PI/180);
        //UI.setColor(Color.black);
        //UI.drawOval(dotx, doty, 10, 10);
        //double diamx = xpos - DIAM/4 *(Math.cos(this.direction*Math.PI/180)); //(0.4 * DIAM/6)
        //double diamy = ypos - DIAM/4 *(Math.sin(this.direction*Math.PI/180));//(0.4 * DIAM/12)
        //UI.setColor(Color.black);
        //UI.drawOval(diamx, diamy, 10, 10);
        //UI.fillOval(xpos - 1*(Math.sin(this.direction*Math.PI/180)), (ypos - 1*(Math.sin(this.direction*Math.PI/180))));
    }
}
