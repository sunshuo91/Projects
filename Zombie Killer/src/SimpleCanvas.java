/**
* Names: Shuo Sun
* Computing IDs: ss8ee
* Lab Section:  1111
* Date: April 11
*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SimpleCanvas extends JPanel implements MouseListener,
		MouseMotionListener {

	int width;
	int height;

	long lastTime;

	SurvivalField simulator;

	public SimpleCanvas(int width_, int height_, SurvivalField simulator_) {
		width = width_;
		height = height_;
		simulator = simulator_;
		lastTime = -1L;
	}

	public void setupAndDisplay() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new JScrollPane(this));
		f.setSize(width, height);
		f.setLocation(200, 200);
		f.setVisible(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	protected void paintComponent(Graphics g) {
		boolean first = (lastTime == -1L);
		long elapsedTime = System.nanoTime() - lastTime;
		lastTime = System.nanoTime();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.white);
		simulator.draw((Graphics2D) g, (first ? 0.0f
				: (float) elapsedTime / 1e9f));
		repaint();
	}

	public void drawDot(Graphics2D g, double x, double y, Color color) {
		g.setColor(color);
		g.fillOval((int) (x + 0.5f), (int) (y + 0.5f), 8, 8);
	}
	
	public void drawField(Graphics2D g, double x, double y, Color color){
		g.setColor(color);
		g.fillOval((int)(x - 43.5f), + (int)(y - 43.5f), 95, 95);
		
	}
	
	public void drawBombFrame(Graphics2D g, double x, double y, Color color){
		g.setColor(color);
		g.fillOval((int)(x - 45), + (int)(y - 45), 98, 98);
	}
	
	public void mouseMoved(MouseEvent e) {
		simulator.mouseAction((float) e.getX(), (float) e.getY(), -1);
	}

	public void mouseClicked(MouseEvent e) {
		simulator
				.mouseAction((float) e.getX(), (float) e.getY(), e.getButton());
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent arg0) {
	}

}
