package extensions.environment.ui;

import java.awt.event.KeyEvent;

import extensions.environment.GameModel;
import graphics.ui.Controller;

public class EnvironmentController extends Controller {

	private GameModel gameModel;

	public EnvironmentController(Object newModel) {
		super(newModel);
		this.gameModel = (GameModel) this.getModel();
	}

	public void keyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_Z) {
			this.gameModel.getPlayers().get(0).press(2);
		}
		if (evt.getKeyCode() == KeyEvent.VK_D) {
			this.gameModel.getPlayers().get(0).press(1);
		}
		if (evt.getKeyCode() == KeyEvent.VK_Q) {
			this.gameModel.getPlayers().get(0).press(0);
		}
	}

	public void keyReleased(KeyEvent evt) {
		switch (evt.getKeyCode()) {
		case KeyEvent.VK_D: {
			this.gameModel.getPlayers().get(0).release(1);
			break;
		}
		case KeyEvent.VK_Q: {
			this.gameModel.getPlayers().get(0).release(0);
			break;
		}
		case KeyEvent.VK_Z: {
			this.gameModel.getPlayers().get(0).release(2);
			break;
		}
		default:
		}
	}
}
