package extensions.menu.menus;

import java.awt.Color;
import java.awt.Point;

import extensions.GameMain;
import extensions.menu.Button;
import extensions.menu.Menu;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.SImage;

public class MainMenu extends Menu {
	
	public MainMenu(GameMain gameMain) {
		super(gameMain);
                SImage img = new SImage("assets/menu/background.png", new Point(0,0));
                this.add(img);
                SImage title = new SImage("assets/menu/title.png", new Point(335,10));
                this.add(title);
		Button play = new Button(new Point(330,165),"assets/menu/nouvelle_partie.png","play");
		this.add(play);
		Button load = new Button(new Point(330,290),"assets/menu/charger.png","load");
		this.add(load);
		Button quit = new Button(new Point(330,415),"assets/menu/quitter.png","quit");
		this.add(quit);
	}
}
