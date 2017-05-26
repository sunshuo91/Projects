/**
* Names: Shuo Sun
* Computing IDs: ss8ee
* Lab Section:  1111
* Date: April 11
*/

public class Human {
	
	private double x2;
	private double y2;
	private double x3;
	private double y3;
	private int bombnum = 3;
	
	public Human(double x2, double y2){
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void set_x3(double x3){
		this.x3 = x3;
	}
	
	public void set_y3(double y3){
		this.y3 = y3;
	}
	
	public void set_lessbomb(int bombnum){
		this.bombnum = bombnum - 1;
	}
	
	public void set_morebomb(int bombnum){
		this.bombnum = bombnum + 1;
	}
	
	public double get_x2(){
		return x2;
	}
	
	public double get_y2(){
		return y2;
	}
	
	public int get_bombnum(){
		return bombnum;
	}
	
	public void move(float elapsedTime){
		if (x3 > x2)
    		x2 = x2 + 20 * elapsedTime; 
    		
    	if (x3 < x2)
    		x2 = x2 - 20 * elapsedTime; 
    	
    	if (y3 > y2) 
    		y2 = y2 + 20 * elapsedTime; 
    	
    	if (y3 < y2)
    		y2 = y2 - 20 * elapsedTime; 
	}

}
