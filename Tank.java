import java.awt.*;
import java.util.*;

public class Tank{

   private String name;
   private Color color;
   private Point position;
   private int health;
   //private boolean state;

   //Will construct a tank with the designated name color and position. Health will start at 100 
   //state will start as true or alive 
   public Tank(String name, Color color, Point position){
      this.name = name;
      this.color = new Color(color.getRed(), color.getGreen(), color.getBlue());
      this.position = new Point(position.getX(), position.getY());
      health = 100;
      //state = true; 
   }
   
   // gets the color of the tank
   public Color getColor(){
      return color;
   }

   //Will check if tank is still alive
   public boolean isAlive(){
      return health > 0; 
   }

   //Returns the tanks name
   public String getName(){
      return name;
   }
   
   //Returns health   
   public int getHealth(){
      return health;
   }
   
   //Returns the point at which the Tank resides
   public Point getPosition(){
      return position;
   }
   
   //Takes a point. If the tank is within the radius it loses health.
   public boolean computeDamage(Point blast){
      int dx = Math.abs(position.getX() - blast.getX());
            
      if(dx < 30){
         health = health - 50; 
         return true;
      }
      
      return false; 
   }   
   
   // method for a shot. Calculates where the shot lands
   public Point shoot(int angle, int power, ArrayList<Point> ground){
      Point shot = new Point();
      if(angle == 90){
         return position;
      }
      
      else if(angle < 90){
         for(int i = 1; i <= 10000; i++){
            double time = i * 0.1;
            shot.setX((int) (position.getX() +  Math.cos(angle) * power * time));
            shot.setY((int) (position.getY() + (Math.sin(angle)) * power - (10/2) * (Math.pow(time, 2))));
            //ground must have order from 0 - x based on x
            if(ground.get(shot.getX()).getY() >= shot.getY()){
               return ground.get(shot.getX());
            }
         }
      }
      else {
         for(int i = 1; i <= 10000; i++){
            double time = i * 0.1;
            int x = (int) (position.getX() +  Math.cos(180 - angle) * power * time);
            int dx = x - position.getX();
            shot.setX(position.getX() - dx);
            shot.setY((int) (position.getY() + (Math.sin(180 - angle)) * power - (10/2) * (Math.pow(time, 2))));
         
            //ground must have order from 0 - x based on x
            if(ground.get(shot.getX()).getY() >= shot.getY()){
               return ground.get(shot.getX());
            }
         }
      }
      return new Point();   
   }
}