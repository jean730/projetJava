package extensions.menu;

import java.awt.Point;

import graphics.shapes.SImage;

public class Button extends SImage {

	private String id;

	public Button(Point point, String fileName, String id) {
		super(fileName,point);
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
