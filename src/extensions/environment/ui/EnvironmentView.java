package extensions.environment.ui;

import java.awt.Graphics;
import java.awt.Color;

import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;
import extensions.environment.GameModel;
import extensions.environment.entities.Player;

public class EnvironmentView extends View {

	private EnvironmentDraftman draftman;
	private Camera camera = new Camera(getWidth(), getHeight());
        private double dt;
        private double time;

        public void printFPS() {
                
                this.dt = ((double)(System.nanoTime() - this.time))/1000000000.0;
                this.time = System.nanoTime();
                System.out.println(1/dt+"FPS");
        }


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
//                printFPS();
                Player player = ((GameModel)getModel()).getPlayers().get(0);
		if (getWidth() != camera.getWidth() || getHeight() != camera.getHeight())
			camera = new Camera(getWidth(), getHeight());
                double zoom = getWidth() / 480.0d;
		this.camera.setTransform(-player.getLoc().x*zoom+getWidth()/2, -player.getLoc().y*zoom+(1.0/3.0)*getWidth(), zoom, 0);
		Graphics cameraGraphics = this.camera.graphics();
		this.draftman = new EnvironmentDraftman(cameraGraphics);
		cameraGraphics.setColor(new Color(128,227,255));
                cameraGraphics.fillRect(player.getLoc().x-240, 0, 480,(int)(getHeight()*zoom));
		g.setColor(new Color(128,227,255));
                g.fillRect(0, 0,getWidth(),getHeight());
		Shape shape = (Shape) this.getModel();
		shape.accept(draftman);
		g.drawImage(this.camera.getImage(), 0, 0, null);
                repaint();
	}
}
