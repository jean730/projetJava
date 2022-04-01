package extensions.environment.ui;

import java.awt.Graphics;

import graphics.shapes.Shape;
import graphics.shapes.ui.ShapesController;
import graphics.ui.Controller;
import graphics.ui.View;

public class EnvironmentView extends View {

	private EnvironmentDraftman draftman;
	private EnvironmentPhysicsman physicsman;
	
	public EnvironmentView(Object model) {
		super(model);
		physicsman = new EnvironmentPhysicsman(model);
	}

	@Override
	public Controller defaultController(Object model){
		return new EnvironmentController(model);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		physicsman.updateTime();
		this.draftman = new EnvironmentDraftman(g);
		g.setColor(getBackground());
		g.fillRect(0, 0, getHeight(), getHeight());
		Shape shape = (Shape) this.getModel();
		shape.accept(draftman);
		shape.accept(physicsman);
		this.repaint();
	}
}
