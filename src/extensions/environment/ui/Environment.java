package extensions.environment.ui;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import extensions.environment.GameModel;
import extensions.environment.Loader;
import extensions.environment.Generator;
import extensions.environment.TreeGenerator;
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
                this.setResizable(false);

		this.gview = new EnvironmentView(this.model);
		this.gview.setPreferredSize(new Dimension(960,540));
		this.getContentPane().add(this.gview, java.awt.BorderLayout.CENTER);

	}


	private void buildModel()
	{

		/*this.model = new GameModel(new Generator("assets/GrassLand/Terrain/Grassland_Terrain_Tileset.png",512,32).getTileMap(),new Point2D.Double(4000,100));
                TreeGenerator.generate((GameModel)this.model);*/
		this.model = new GameModel("assets/save");
	}

	public static void main(String[] args)
	{
		Environment self = new Environment();
		self.pack();
		self.setVisible(true);
		self.model.getAudio().play("background");
		self.model.save("assets/save");
		self.gameLoop();
	}

	private void gameLoop() {
		while (true) {
			this.model.applyPhysics();
		}
	}
}
