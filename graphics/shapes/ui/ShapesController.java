package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {
	
	private SCollection collection;
	private Boolean mousePressedLock = false;
	private Point mousePosition;

	public ShapesController(Object newModel) {
		super(newModel);
		this.collection = (SCollection) this.getModel();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Shape s = getTarget(e);
		if (s == null) this.mousePressedLock = true;
		else {
			SelectionAttributes selectionAttributes = (SelectionAttributes) s.getAttributes().get("selection");
			if (selectionAttributes != null && !selectionAttributes.isSelected()) this.mousePressedLock = true;
		}
		this.mousePosition = e.getPoint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		this.mousePressedLock = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Shape s = getTarget(e);
		if (!((e.getModifiersEx()&InputEvent.SHIFT_DOWN_MASK) == InputEvent.SHIFT_DOWN_MASK)) unselectAll();
		if (s != null) {
			SelectionAttributes selectionAttributes = (SelectionAttributes) s.getAttributes().get("selection");
			if (selectionAttributes != null) {
				selectionAttributes.toggleSelection();
			}
		}
		this.getView().repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!mousePressedLock) {
			translateSelected(e.getX()-mousePosition.x, e.getY()-mousePosition.y);
			this.mousePosition = e.getPoint();
		}
		this.getView().repaint();
	}
	
	public Shape getTarget(MouseEvent e) {
		Iterator<Shape> iterator = this.collection.iterator();
		Shape target = null;
		while (iterator.hasNext()){
			Shape shape = iterator.next();
			if (shape.getBounds().contains(e.getPoint())) {
				target = shape;
			}
		}
		return target;
	}
	
	private void unselectAll() {
		this.collection.iterator().forEachRemaining((shape) -> {
			SelectionAttributes selectionAttributes = (SelectionAttributes) shape.getAttributes().get("selection");
			if (selectionAttributes != null) selectionAttributes.unselect();
		});
	}
	
	private void translateSelected(int x, int y) {
		this.collection.iterator().forEachRemaining((shape) ->{
			SelectionAttributes selectionAttributes = (SelectionAttributes) shape.getAttributes().get("selection");
			if (selectionAttributes != null && selectionAttributes.isSelected()) shape.translate(x, y);
		});
	}
}
