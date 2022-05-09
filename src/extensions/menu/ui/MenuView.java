package extensions.menu.ui;

import graphics.shapes.ui.ShapesView;
import graphics.ui.Controller;

public class MenuView extends ShapesView {

  public MenuView(Object newModel) {
    super(newModel);
    this.setFocusable(true);
    this.requestFocusInWindow();
  }

  @Override
  public Controller defaultController(Object model) {
    return new MenuController(model);
  }
}
