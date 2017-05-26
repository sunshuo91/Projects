/**
* Names: Shuo Sun
* Computing IDs: ss8ee
* Lab Section:  1111
* Date: April 11
*/

public class Zombie {
	
	private Human human;
	
	private double x1;
	private double y1;
	
	public Zombie (double x1, double y1, Human human){
		this.x1 = x1;
		this.y1 = y1;
		this.human = human;
	}
	
	public void set_x1(double x1){
		this.x1 = x1;
	}
	
	public void set_y1(double y1){
		this.y1 = y1;
	}
	
	public double get_x1(){
		return x1;
	}
	
	public double get_y1(){
		return y1;
	}
	
	public void move(float elapsedTime){
		
		if (human.get_x2() > x1)
    		x1 = x1 + 10 * elapsedTime; 

    	if (human.get_x2() < x1) 
    		x1 = x1 - 10 * elapsedTime;     	
    	
    	if (human.get_y2() > y1)
    		y1 = y1 + 10 * elapsedTime;     	
    	
    	if (human.get_y2() < y1) 
    		y1 = y1 - 10 * elapsedTime; 
	}
	
	
	public void collision(int x_a, int x_b, int y_a, int y_b){
		if (x_a == x_b && y_a == y_b)
			set_x1(get_x1()+5);
	}
	
	public boolean gameover(int a, int b, int c, int d){
		if (a < b + 5 && a > b - 5 && c < d + 5 && c > d -5)
			return true;	
		else
			return false;
	}
	
}


