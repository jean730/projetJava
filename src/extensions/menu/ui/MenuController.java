package extensions.menu.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import extensions.menu.Button;
import extensions.menu.Menu;
import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.ui.Controller;

public class MenuController extends Controller {

	private SCollection collection;
	private Point mousePosition;

	public MenuController(Object newModel) {
		super(newModel);
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
			Button next = (Button) iterator.next();
			if (next.getBounds().contains(e.getPoint())) {
				target = next;
			}
		}
		return target;
	}
	
	public void execute(String string) {
		switch(string) {
		case "play":
			((Menu) this.collection).getGameMain().launchGame();
			break;
		case "quit":
			((Menu) this.collection).getGameMain().quit();
			break;
		}
	}
}
