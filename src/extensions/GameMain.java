package extensions;

import java.awt.Dimension;

import javax.swing.JFrame;

import extensions.environment.GameModel;
import extensions.environment.Loader;
import extensions.environment.Generator;
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

		buildMainMenu();
		this.getContentPane().add(this.gview, java.awt.BorderLayout.CENTER);

	}

	private void buildMainMenu() {
		this.model = new MainMenu(this);
		this.gview = new MenuView(this.model);
		this.gview.setPreferredSize(new Dimension(960, 540));
	}

	private void buildEnvironnement() {
		this.model = new GameModel(new Generator("assets/GrassLand/Terrain/Grassland_Terrain_Tileset.png",512,32).getTileMap());
		this.gview = new EnvironmentView(this.model);
		this.gview.setPreferredSize(new Dimension(960, 540));
		this.gview.repaint();
	}

	public static void main(String[] args) {
		GameMain self = new GameMain();
		self.pack();
		self.setVisible(true);

	}

	public void launchGame() {
		this.getContentPane().remove(this.gview);
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

	}
}
