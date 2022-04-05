package extensions.environment.ui;

import java.awt.Graphics;

import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;

public class EnvironmentView extends View {

	private EnvironmentDraftman draftman;
	private Camera camera = new Camera(getWidth(), getHeight());

	public EnvironmentView(Object model) {
		super(model);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	@Override
	public Controller defaultController(Object model) {
		return new EnvironmentController(model);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (getWidth() != camera.getWidth() || getHeight() != camera.getHeight())
			camera = new Camera(getWidth(), getHeight());
		this.camera.setTransform(0, 0, getWidth() / 480.0d, 0);
		Graphics cameraGraphics = this.camera.graphics();
		this.draftman = new EnvironmentDraftman(cameraGraphics);
		cameraGraphics.setColor(getBackground());
		cameraGraphics.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		Shape shape = (Shape) this.getModel();
		shape.accept(draftman);
		g.drawImage(this.camera.getImage(), 0, 0, null);
	}
}
