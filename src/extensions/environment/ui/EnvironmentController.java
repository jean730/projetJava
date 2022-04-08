package extensions.environment.ui;

import java.awt.event.KeyEvent;

import extensions.environment.GameModel;
import graphics.ui.Controller;

public class EnvironmentController extends Controller{

	private GameModel gameModel; 
	
	public EnvironmentController(Object newModel) {
		super(newModel);
		this.gameModel = (GameModel) this.getModel();
	}
	
	public void keyPressed(KeyEvent evt){

		if (evt.getKeyCode() == KeyEvent.VK_Z) {
			this.gameModel.getPlayers().get(0).conditionalJumpFunctionToJumpOnlyOnGround(this.gameModel.getTileMap());
		}
		if (evt.getKeyCode() == KeyEvent.VK_D) {
			this.gameModel.getPlayers().get(0).startWalk(1, false);
		}
		if (evt.getKeyCode() == KeyEvent.VK_Q) {
			this.gameModel.getPlayers().get(0).startWalk(-1, false);
		}
	}

	public void keyReleased(KeyEvent evt) {
		switch(evt.getKeyCode()) {
		case KeyEvent.VK_D:
		case KeyEvent.VK_Q: {
			this.gameModel.getPlayers().get(0).stopWalk();
		}
		default:
		}
	}
}
