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
		switch (evt.getKeyCode()) {
		case KeyEvent.VK_Z: {
			this.gameModel.getPlayers().get(0).conditionalJumpFunctionToJumpOnlyOnGround(this.gameModel.getTileMap());
			break;
		}
		default:
		}
	}

}
