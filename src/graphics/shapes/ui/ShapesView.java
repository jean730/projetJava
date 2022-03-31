package graphics.shapes.ui;

import java.awt.Graphics;

import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View {

	private ShapeDraftman draftman;
	
	public ShapesView(Object model) {
		super(model);
	}

	@Override
	public Controller defaultController(Object model){
		return new ShapesController(model);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		this.draftman = new ShapeDraftman(g);
		g.setColor(getBackground());
		g.fillRect(0, 0, 600, 600);
		Shape shape = (Shape) this.getModel();
		shape.accept(draftman);
	}
}
