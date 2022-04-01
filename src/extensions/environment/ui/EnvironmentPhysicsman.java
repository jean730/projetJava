package extensions.environment.ui;

import extensions.environment.EnvironmentVisitor;
import extensions.environment.TileMap;
import extensions.environment.entities.Entity;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.ShapeVisitor;

public class EnvironmentPhysicsman implements EnvironmentVisitor, ShapeVisitor {

	private long time = System.currentTimeMillis();
	private long dt = 0;
	private SCollection model;
	
	public EnvironmentPhysicsman(Object model) {
		this.model = (SCollection) model;
	}

	public void updateTime() {
		this.dt = this.time - System.currentTimeMillis();
		this.time = System.currentTimeMillis();		
	}

        public long getDT(){
            return this.dt;
        }
	
	public void visitRectangle(SRectangle rect) {}
	public void visitCollection(SCollection collection) {
		collection.iterator().forEachRemaining((shape)->shape.accept(this));
	}
	public void visitCircle(SCircle sCircle) {}
	public void visitText(SText sText) {}
	public void visitTileMap(TileMap tileMap) {}

	@Override
	public void visitEntity(Entity entity) {
		//entity.applyPhysics(); ???
	}

}
