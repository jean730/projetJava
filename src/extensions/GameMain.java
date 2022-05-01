package extensions;

import java.awt.Dimension;

import javax.swing.JFrame;

import extensions.environment.GameModel;
import extensions.environment.Loader;
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
		this.model = new GameModel(new Loader("assets/Level1").getTileMap());
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
		Thread t = new Thread() {
			public void run() {
				((GameModel)model).gameLoop();
				}};
		t.start();
	}


	public void quit() {

	}
}