package extensions.menu.menus;

import java.awt.Color;
import java.awt.Point;

import extensions.GameMain;
import extensions.menu.Button;
import extensions.menu.Menu;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;

public class MainMenu extends Menu {
	
	public MainMenu(GameMain gameMain) {
		super(gameMain);
		Button play = new Button(new Point(10,10),"Play","play");
		play.addAttributes(new ColorAttributes(true,true,Color.GRAY,Color.BLACK));
		play.addAttributes(new FontAttributes());
		this.add(play);
		Button quit = new Button(new Point(100,10),"X","quit");
		quit.addAttributes(new ColorAttributes(true,true,Color.GRAY,Color.BLACK));
		quit.addAttributes(new FontAttributes());
		this.add(quit);
	}
}
