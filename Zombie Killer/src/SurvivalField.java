/**
* Names: Shuo Sun
* Computing IDs: ss8ee
* Lab Section:  1111
* Date: April 11
*/

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class SurvivalField {

	private SimpleCanvas canvas;
	
	private InfoFrame output;
	
	private ArrayList<Zombie> Zombie = new ArrayList<Zombie>();
	private Human human;
	private int score;
	private boolean gameover = false;
	private int time = 0;
	
	private int bombtime = 0;
	private boolean bombon = false;

	private int zombiescore = 0;
	private int zombiedead = 0;

	public SurvivalField() {
		canvas = new SimpleCanvas(500, 500, this);
		
		output = new InfoFrame(this);

		human = new Human (250, 250);
		
		for (int i = 0; i < 4; i ++)
       		Zombie.add(new Zombie ((Math.random()*500), (Math.random()*500), human));
	}

	public void mouseAction(float x, float y, int button) {
		if (gameover == false){
			if (button == -1) {
				human.set_x3(x);
				human.set_y3(y);
			}
	
			if (button == 1) {
				detonateBomb();
			}
		}
	}

	public void detonateBomb() {
		if (human.get_bombnum() > 0)
		{
			bombon = true;
			
			int zombiekilled = 0;
			
			human.set_lessbomb(human.get_bombnum());
			output.println("BOOM! You dropped a bomb!  You now have " + human.get_bombnum()+ " bombs!");
			for (int i = 0; i < Zombie.size(); i++)
			{
				double radius = Math.sqrt(Math.pow((human.get_y2()-Zombie.get(i).get_y1()),2) + Math.pow((human.get_x2() - Zombie.get(i).get_x1()),2));
				if (radius <= 50)
				{
					Zombie.remove(i);
					i--;
					zombiekilled++;
				}
			}
			
			int[] z_array = new int[zombiekilled];
			int zombiebonus = 2;
			
			if (zombiekilled == 0){
				zombiebonus = 0;
				output.println("Uhhhh... No zombie is killed.. Try hard dude...!!");
			}

			else{ 
				if (zombiekilled == 1)
				zombiebonus = 1;
				
				else if (zombiekilled > 2){
				z_array[0] = z_array[1] = 1;
				
				for (int i = 2; i < z_array.length; i++){
					z_array[i] = z_array[i-1] + z_array[i-2];
					zombiebonus = zombiebonus + z_array[i];
					}
				}
			output.println("Wowww!! you killed " + zombiekilled + " this time and got " + zombiebonus * 10 + " bonus point ");			
			}
			zombiescore = zombiescore  + zombiebonus * 10;
			zombiedead = zombiedead  + zombiekilled;
		}
		else
			output.println("Out of bombs!");
	}

	public void draw(Graphics2D g, float elapsedTime) {
		
		if (gameover == false)
		{	
			human.move(elapsedTime);
			for (int i=0; i<Zombie.size(); i++)
				Zombie.get(i).move(elapsedTime);
			
			time ++;
			
			if (time % 100 == 0)
				score ++;
			
			if (time % 5000 == 0)
				Zombie.add(new Zombie ((Math.random()*500), (Math.random()*500), human));
			
			if (time % 100000 == 0)
			{
				human.set_morebomb(human.get_bombnum());
				output.println("You've earned another bomb!  You now have " + human.get_bombnum() + " bombs!");
			}
			
			for (int i = 0; i < Zombie.size() - 1; i++)
			{
				for(int j = i+1; j < Zombie.size(); j++)
				{
					int a = (int)Zombie.get(i).get_x1();
					int b = (int)Zombie.get(j).get_x1();
					int c = (int)Zombie.get(i).get_y1();
					int d = (int)Zombie.get(j).get_y1();
					
					Zombie.get(i).collision(a,b,c,d);
				}
			}						
			
			for (int i = 0; i < Zombie.size(); i ++)
				if (gameover == false)
					gameover = Zombie.get(i).gameover((int)Zombie.get(i).get_x1(), (int)human.get_x2(), (int)Zombie.get(i).get_y1(), (int)human.get_y2());
		
			if (gameover == true)
			{
				output.println("NOMNOMNOMNOMNOMNOM\n");
				output.println("Game Over!");
				output.println("Your Score: " + score);
				output.println("You Totally Killed " + zombiedead + " zombies!");
				output.println("You Got " + zombiescore + " Points Added on Your Score for Killing " + zombiedead + " Zombies!");
				output.println("Your Total Score: " + (score + zombiescore));
			}
		
		}
		canvas.drawBombFrame(g,human.get_x2(), human.get_y2(), Color.red);
		canvas.drawField(g, human.get_x2(), human.get_y2(), Color.black);
		
		if (bombon == true){
			canvas.drawField(g, human.get_x2(), human.get_y2(), Color.orange);
			bombtime++;
		}
		
		if (bombtime == 100){
			bombon = false;
			bombtime = 0;
		}
		
		canvas.drawDot(g, human.get_x2(), human.get_y2(), Color.green);

		
		for (int i = 0; i < Zombie.size(); i++)
				canvas.drawDot(g, Zombie.get(i).get_x1(), Zombie.get(i).get_y1(), Color.red);
		}
	

	public static void main(String[] args) throws Exception {
		SurvivalField simulator = new SurvivalField();
		simulator.play();		
	}

	public void play() {
		output.println("New Player Created!  You start with 3 bombs!");
		canvas.setupAndDisplay();
	}
}
