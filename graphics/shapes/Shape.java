package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import graphics.shapes.attributes.Attributes;

public abstract class Shape {
	
	private Map<String,Attributes> attributes;
	
	public Shape(){
		this.attributes = new HashMap<>();
	}
	
	public abstract Point getLoc();
	
	public abstract void setLoc(Point point);
	
	public void translate(int dx, int dy) {
		this.setLoc(new Point(this.getLoc().x + dx, this.getLoc().y+dy));
	}
	
	public abstract Rectangle getBounds();
	
	public abstract void accept(ShapeVisitor visitor);

	public Map<String,Attributes> getAttributes() {
		return attributes;
	}
	
	public void addAttributes(Attributes attribute) {
		this.attributes.put(attribute.getId(),attribute);
	}
}
