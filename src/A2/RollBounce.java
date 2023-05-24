
//Noga Gottlieb
package A2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Objects;
import java.net.URL;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;



public class RollBounce extends JPanel implements ActionListener {

	protected Timer tm;
	ListNode<Ball> lst = new ListNode<>();
	protected static int window_height, window_width, gravity,friction, minspeed, maxspeed, timerDelay, balls, ball_radius;
	//protected static double  ball_radius ;
	//class ball to create ball object
	public class Ball
	{
		private int x;
		private int y;
		int xVelocity;
		int yVelocity;
		
		//**ball contractor to create new ball
		//**when creating new ball, assign random x and y (position), and random xVelocity.
		public Ball()
		{
			x=ThreadLocalRandom.current().nextInt(0, (window_width-2*ball_radius) + 1);
			y=ThreadLocalRandom.current().nextInt(0, window_height + 1);
			xVelocity=ThreadLocalRandom.current().nextInt(minspeed, maxspeed + 1);
			yVelocity=0;
		}
	}

	//

	//**RollBounce contractor to read the property file and create the ball list
	//@param tring propertyFileName with the file name
	
	public RollBounce(String propertyFileName) { 

		Properties prop = new Properties();
		try {
			ClassLoader classLoader = RollBounce.class.getClassLoader();

			// Make sure that the configuration file exists
			URL res = Objects.requireNonNull(classLoader.getResource(propertyFileName), "Can't find configuration file app.config");

			InputStream is = new FileInputStream(res.getFile());
			prop.load(is);
			
			//read from the property file
			gravity = Integer.parseInt(prop.getProperty("gravity"));
			window_height = Integer.parseInt(prop.getProperty("window_height"));
			window_width = Integer.parseInt(prop.getProperty("window_width"));
			friction= Integer.parseInt(prop.getProperty("friction"));
			minspeed = Integer.parseInt(prop.getProperty("minspeed"));
			maxspeed = Integer.parseInt(prop.getProperty("maxspeed"));
			timerDelay = Integer.parseInt(prop.getProperty("timerDelay"));
			balls = Integer.parseInt(prop.getProperty("balls"));
			ball_radius = Integer.parseInt(prop.getProperty("ball_radius"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<balls; i++)            //adding balls to the list
		{
			Ball ball = new Ball();
			lst.add(ball);
		}


		tm = new Timer(timerDelay, this); 


	}

	//** paintComponent method to paint the balls
	//@param Graphics g 
	//@void method
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 

		//Paint each ball
		//assigning colors to the ball from a color list
		String[] colors = new String[] {"BLUE", "ORANGE", "MAGENTA", "RED", "GREEN", "YELLOW"};
		for(int i=0; i<balls; i++)
		{
			Ball ball= (Ball)lst.get(i);
			
			if(i>colors.length-1)
				g.setColor(Color.PINK);
			else if(colors[i]=="BLUE")
				g.setColor(Color.BLUE);
			else if(colors[i]=="ORANGE")
				g.setColor(Color.ORANGE);
			else if(colors[i]=="MAGENTA")
				g.setColor(Color.MAGENTA);
			else if(colors[i]=="RED")
				g.setColor(Color.RED);
			else if(colors[i]=="GREEN")
				g.setColor(Color.GREEN);
			else if(colors[i]=="YELLOW")
				g.setColor(Color.YELLOW);
		
			
			g.fillOval(ball.x, ball.y, ball_radius, ball_radius);

		}

		tm.start();
	}


	@Override
	//** actionPerformed method to change the x and y values (ball position) and update the 
	//@param ActionEvent actionEvent
	//@void method
	public void actionPerformed(ActionEvent actionEvent) {

		//moving each ball in the list 
		for (int i=0; i<balls; i++)
		{
			Ball ball= (Ball)lst.get(i);
			
			ball.x += ball.xVelocity;         //every time, change the x and y positions with the velocity
			ball.y += ball.yVelocity;
			
			//Checking that the ball stay in the screen 
			if(ball.x<0)//2*ball_radius)
				ball.x=0;//2*ball_radius;
			if(ball.y<2*ball_radius)
				ball.y=2*ball_radius;
			if(ball.x>2*window_width-ball_radius)
				ball.x=2*window_width-ball_radius;
			if(ball.y>window_height-2*ball_radius)
				ball.y=window_height-2*ball_radius;
			
			//stop ball x velocity
			if(Math.abs(ball.xVelocity)<friction)  
				ball.xVelocity=0;
			
			
			//change velocity if the ball reach the bottom/top/right/left of the screen 
			
			if(ball.x+2*(ball_radius)>=window_width)					//right
			{
				if(ball.yVelocity>0)
					ball.yVelocity=ball.yVelocity+gravity-friction;
				if(ball.yVelocity<0)
					ball.yVelocity=ball.yVelocity+gravity+friction;
				ball.xVelocity=-ball.xVelocity-friction;
			}
			
			else if(ball.x<=0)								//left
			{
				if(ball.yVelocity>0)
					ball.yVelocity=ball.yVelocity+gravity-friction;
				if(ball.yVelocity<0)
					ball.yVelocity=ball.yVelocity+gravity+friction;
				ball.xVelocity=-ball.xVelocity+friction;
			}
			
			else if(ball.y+2*(ball_radius)>=window_height) 						//bottom
			{
				
				if(ball.yVelocity<0)
					ball.yVelocity=-ball.yVelocity+friction-gravity;
				if(ball.yVelocity>0)
					ball.yVelocity=-ball.yVelocity+friction+gravity;
				if(ball.xVelocity>0)
					ball.xVelocity=ball.xVelocity-friction;
				if(ball.xVelocity<0)
					ball.xVelocity=ball.xVelocity+friction;
			}

			else if(ball.y+2*(ball_radius)<=0)								//top
			{
				ball.yVelocity=-ball.yVelocity-friction;
				if(ball.xVelocity>0)
					ball.xVelocity=ball.xVelocity-friction;
				if(ball.xVelocity<0)
					ball.xVelocity=ball.xVelocity+friction;

			}

			
			
			//else, add gravity to the y velocity
			else
			{
				ball.yVelocity+=gravity;
			}

		}
		repaint();
	}

	public static void main(String[] args) {
		
		RollBounce rb = new RollBounce("RollBounce.prop");

		JFrame jf = new JFrame();
		jf.setTitle("Roll Bounce");
		jf.setSize(window_width, window_height); 
		jf.add(rb);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
