package extensions.environment.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

import extensions.environment.TileMap;
import extensions.environment.entities.Entity;
import extensions.environment.entities.Player;
import graphics.shapes.SCollection;

public class Environment extends JFrame {
	
	private TileMap tileMap = new TileMap();
	private ArrayList<Entity> entities = new ArrayList<>();
	
	EnvironmentView gview;
	SCollection model;
	
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
		this.gview.repaint();
	}

	
	private void buildModel()
	{
		this.model = new SCollection();
		
		this.model.add(this.tileMap);
		
		this.model.add(new Player(new Point(100,100)));
	}
	
	public static void main(String[] args)
	{
		Environment self = new Environment();
		self.pack();
		self.setVisible(true);
	}
}
