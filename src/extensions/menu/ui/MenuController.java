package extensions.menu.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import extensions.menu.Button;
import extensions.menu.Menu;
import extensions.environment.audio.Audio;
import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.ui.Controller;

public class MenuController extends Controller {

	private SCollection collection;
	private Point mousePosition;
        private Audio audio = new Audio();

	public MenuController(Object newModel) {
		super(newModel);
                audio.loadAudio("assets/Audio/start.wav","start"); 
		this.collection = (SCollection) this.getModel();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Button button  = getTarget(e);
		this.mousePosition = e.getPoint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Button button = getTarget(e);
		if (button != null) execute(button.getId());
	}
	
	public Button getTarget(MouseEvent e) {
		Iterator<Shape> iterator = this.collection.iterator();
		Button target = null;
		while (iterator.hasNext()){
			Shape next = (Shape) iterator.next();
			if (next instanceof Button && next.getBounds().contains(e.getPoint())) {
				target = (Button)next;
			}
		}
		return target;
	}
	
	public void execute(String string) {
		switch(string) {
		case "play":
			((Menu) this.collection).getGameMain().newGame();
                        audio.play("start");
			break;
		case "load":
			((Menu) this.collection).getGameMain().loadGame();
                        audio.play("start");
			break;
		case "quit":
			((Menu) this.collection).getGameMain().quit();
			break;
		}
	}
}
