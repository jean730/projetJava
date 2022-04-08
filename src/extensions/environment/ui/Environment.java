package extensions.environment.ui;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import extensions.environment.GameModel;
import extensions.environment.Loader;
import extensions.environment.TileMap;
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
		this.gview.setPreferredSize(new Dimension(480,360));
		this.getContentPane().add(this.gview, java.awt.BorderLayout.CENTER);

	}


	private void buildModel()
	{
		this.model = new GameModel(new Loader("assets/Level1").getTileMap());
		Player p = new Player(new Point2D.Double(100,100), this.model);
                StaticEntity fire = new StaticEntity(new Point2D.Double(100,192),"assets/Details/fire.png"); // Entité de test
                fire.getSprite().registerAnimation("default",new Animation(32,48,256,48,0,8,80));
                fire.getSprite().setAnimation("default");
		this.model.addEntity(p);
		this.model.addEntity(fire);
		this.model.addPlayer(p);
	}
	
	public static void main(String[] args)
	{
		Environment self = new Environment();
		self.pack();
		self.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		self.gameLoop();
	}
	
	private void gameLoop() {
		while (true) {
			this.gview.repaint();
			this.model.applyPhysics();
		}
	}
}
