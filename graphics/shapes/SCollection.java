package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class SCollection extends Shape {

	private ArrayList<Shape> shapes = new ArrayList<>();
	
	public Iterator<Shape> iterator(){
		return shapes.iterator();
	}	
	
	@Override
	public Point getLoc() {
		return null;
	}

	@Override
	public void setLoc(Point point) {		
	}

	@Override
	public void translate(int x, int y) {
		for(Shape shape : shapes) {
			shape.translate(x, y);
		}
	}

	@Override
	public Rectangle getBounds() {
		Rectangle rectangle = shapes.get(0).getBounds();
		for(Shape shape : shapes){
			rectangle = rectangle.union(shape.getBounds());
		}
		return rectangle;
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitCollection(this);
	}

	public void add(Shape shape) {
		shapes.add(shape);
	}

}
