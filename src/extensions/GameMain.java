package extensions;

import java.awt.Dimension;

import javax.swing.JFrame;

import java.awt.geom.Point2D;

import extensions.environment.GameModel;
import extensions.environment.Loader;
import extensions.environment.Generator;
import extensions.environment.TreeGenerator;
import extensions.environment.ui.EnvironmentView;
import extensions.menu.menus.MainMenu;
import extensions.menu.ui.MenuView;
import graphics.ui.View;

public class GameMain extends JFrame {

	View gview;
	Object model;

	public GameMain() {

		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});
                this.setResizable(false);
		buildMainMenu();
		this.getContentPane().add(this.gview, java.awt.BorderLayout.CENTER);

	}

	public void buildMainMenu() {
		this.model = new MainMenu(this);
		this.gview = new MenuView(this.model);
		this.gview.setPreferredSize(new Dimension(960, 540));
	}
	
	public void rebuildMainMenu() {
		this.getContentPane().remove(this.gview);
		buildMainMenu();
		this.getContentPane().add(this.gview, java.awt.BorderLayout.CENTER);
		this.pack();
        this.gview.requestFocusInWindow();
	}
	
	private void buildEnvironnement() {
		this.gview = new EnvironmentView(this.model);
		this.gview.setPreferredSize(new Dimension(960, 540));
	}

	public static void main(String[] args) {
		GameMain self = new GameMain();
		self.pack();
		self.setVisible(true);

	}

	public void newGame() {
		this.getContentPane().remove(this.gview);
		this.model = new GameModel(new Generator("assets/GrassLand/Terrain/Grassland_Terrain_Tileset.png",512,32).getTileMap(),new Point2D.Double(4000,100),this);
                ((GameModel)model).getAudio().play("background");
                TreeGenerator.generate((GameModel)this.model);
		this.buildEnvironnement();
		this.getContentPane().add(this.gview, java.awt.BorderLayout.CENTER);
		this.pack();
                this.gview.requestFocusInWindow();
		Thread t = new Thread() {
			public void run() {
				((GameModel)model).gameLoop();
				}};
		t.start();
	}

	public void loadGame() {
		this.getContentPane().remove(this.gview);
		this.model = new GameModel(new Loader("assets/save").getTileMap(),new Point2D.Double(50,100),this);
                ((GameModel)model).getAudio().play("background");
		this.buildEnvironnement();
		this.getContentPane().add(this.gview, java.awt.BorderLayout.CENTER);
		this.pack();
                this.gview.requestFocusInWindow();
		Thread t = new Thread() {
			public void run() {
				((GameModel)model).gameLoop();
				}};
		t.start();
	}

	public void quit() {
            this.dispose();
	}


}
