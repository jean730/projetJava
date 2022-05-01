package extensions.menu;

import java.awt.Point;

import graphics.shapes.SText;

public class Button extends SText {

	private String id;

	public Button(Point point, String string, String id) {
		super(point, string);
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
