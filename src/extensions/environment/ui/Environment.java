package extensions.environment.ui;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import extensions.environment.GameModel;
import extensions.environment.Loader;
import extensions.environment.entities.*;

public class Environment extends JFrame {
	
	EnvironmentView gview;
	GameModel model;
	
	public Environment()
	{	
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		this.buildModel();
		
		this.gview = new EnvironmentView(this.model);
		this.gview.setPreferredSize(new Dimension(960,540));
		this.getContentPane().add(this.gview, java.awt.BorderLayout.CENTER);

	}


	private void buildModel()
	{
		this.model = new GameModel(new Loader("assets/Level1").getTileMap());
		Player p = new Player(new Point2D.Double(50,100), this.model);
                StaticEntity fire = new StaticEntity(new Point2D.Double(100,192),"assets/Details/fire.png"); // Entité de test
                fire.getSprite().registerAnimation("default",new Animation(32,48,256,48,0,8,80));
                fire.getSprite().setAnimation("default");
                this.model.addEntity(new Cloud(new Point2D.Double(-200,50),-20,"assets/GrassLand/Background/GrassLand_Cloud_1.png"));
                this.model.addEntity(new Cloud(new Point2D.Double(-100,80),-30,"assets/GrassLand/Background/GrassLand_Cloud_2.png"));
                this.model.addEntity(new Cloud(new Point2D.Double(0,20),-20,"assets/GrassLand/Background/GrassLand_Cloud_3.png"));
                this.model.addEntity(new Cloud(new Point2D.Double(100,100),-40,"assets/GrassLand/Background/GrassLand_Cloud_1.png"));
                this.model.addEntity(new Cloud(new Point2D.Double(250,30),-10,"assets/GrassLand/Background/GrassLand_Cloud_2.png"));
                this.model.addEntity(new Cloud(new Point2D.Double(400,60),-50,"assets/GrassLand/Background/GrassLand_Cloud_3.png"));
		this.model.addEntity(p);
		this.model.addEntity(fire);
		this.model.addPlayer(p);
		Enemy q = new Enemy(new Point2D.Double(100,150),this.model);
		this.model.addEntity(q);
	}
	
	public static void main(String[] args)
	{
		Environment self = new Environment();
		self.pack();
		self.setVisible(true);
                self.model.getAudio().play("background");
		self.gameLoop();
	}
	
	private void gameLoop() {
		while (true) {
			this.model.applyPhysics();
		}
	}
}
