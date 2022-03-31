package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes {

	@Override
	public String getId() {
		return "color";
	}
	
	public Boolean filled;
	public Boolean strocked;
	public Color filledColor;
	public Color strockedColor;
	
	public ColorAttributes(Boolean filled ,Boolean strocked, Color filledColor, Color strockedColor) {
		this.filled = filled;
		this.strocked = strocked;
		this.filledColor = filledColor;
		this.strockedColor = strockedColor;
	}
}
