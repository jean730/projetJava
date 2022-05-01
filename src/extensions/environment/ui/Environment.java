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
