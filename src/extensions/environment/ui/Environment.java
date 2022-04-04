package extensions.environment.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import extensions.environment.Loader;
import extensions.environment.GameModel;
import extensions.environment.TileMap;
import extensions.environment.entities.Entity;
import extensions.environment.entities.Player;

public class Environment extends JFrame {
	

	private TileMap tileMap = new Loader("assets/Level1").getTileMap();
	private ArrayList<Entity> entities = new ArrayList<>();

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
		this.model = new GameModel(new TileMap());
		this.model.addEntity(new Player(new Point2D.Double(100,100)));
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
