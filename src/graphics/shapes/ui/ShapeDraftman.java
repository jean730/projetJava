package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements ShapeVisitor {

	private final ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes(true,false,Color.WHITE,Color.WHITE);
	private final FontAttributes DEFAULTFONTATTRIBUTES = new FontAttributes();
	private final int SELECTIONRECTSIZE = 5;
	
	protected Graphics g;	
	
	public ShapeDraftman(Graphics g) {
		this.g = g;
	}
	
	@Override
	public void visitCollection(SCollection sCollection) {
		sCollection.iterator().forEachRemaining((shape)->shape.accept(this));
		SelectionAttributes selectionAttributes = (SelectionAttributes) sCollection.getAttributes().get("selection");
		if (selectionAttributes != null && selectionAttributes.isSelected()) drawSelectionSquares(sCollection.getBounds());
	}
	
	@Override
	public void visitRectangle(SRectangle sRect) {
		Rectangle rect = sRect.getRect();
		ColorAttributes color = (ColorAttributes) sRect.getAttributes().get("color");
		if (color == null) color = DEFAULTCOLORATTRIBUTES; 
		if (color.filled) {
			g.setColor(color.filledColor);
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
		if (color.strocked) {
			g.setColor(color.strockedColor);
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
		}
		SelectionAttributes selectionAttributes = (SelectionAttributes) sRect.getAttributes().get("selection");
		if (selectionAttributes != null && selectionAttributes.isSelected()) drawSelectionSquares(sRect.getBounds());
	}

	@Override
	public void visitCircle(SCircle sCircle) {
		int radius = sCircle.getRadius();
		Point loc = sCircle.getLoc();
		ColorAttributes color = (ColorAttributes) sCircle.getAttributes().get("color");
		if (color == null) color = DEFAULTCOLORATTRIBUTES; 
		if (color.filled) {
			g.setColor(color.filledColor);
			g.fillOval(loc.x-radius, loc.y-radius, 2*radius, 2*radius);
		}
		if (color.strocked) {
			g.setColor(color.strockedColor);
			g.drawOval(loc.x-radius, loc.y-radius, 2*radius, 2*radius);
		}
		SelectionAttributes selectionAttributes = (SelectionAttributes) sCircle.getAttributes().get("selection");
		if (selectionAttributes != null && selectionAttributes.isSelected()) drawSelectionSquares(sCircle.getBounds());
	}

	@Override
	public void visitText(SText sText) {
		Point loc = sText.getLoc();
		Rectangle rect = sText.getBounds();
		FontAttributes fontAttributes = (FontAttributes)sText.getAttributes().get("font");
		if (fontAttributes == null) fontAttributes = DEFAULTFONTATTRIBUTES;
		ColorAttributes color = (ColorAttributes) sText.getAttributes().get("color");
		if (color == null) color = DEFAULTCOLORATTRIBUTES; 
		if (color.filled) {
			g.setColor(color.filledColor);
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
		if (color.strocked) {
			g.setColor(color.strockedColor);
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
		}
		g.setFont(fontAttributes.getFont());
		g.setColor(fontAttributes.fontColor);
		g.drawString(sText.getText(), loc.x, loc.y + rect.height);
		SelectionAttributes selectionAttributes = (SelectionAttributes) sText.getAttributes().get("selection");
		if (selectionAttributes != null && selectionAttributes.isSelected()) drawSelectionSquares(sText.getBounds());
	}
	
	private void drawSelectionSquares(Rectangle bounds) {
		g.setColor(Color.BLACK);
		g.drawRect(bounds.x - SELECTIONRECTSIZE, bounds.y - SELECTIONRECTSIZE, SELECTIONRECTSIZE, SELECTIONRECTSIZE);
		g.setColor(Color.BLACK);
		g.drawRect(bounds.x + bounds.width, bounds.y + bounds.height, SELECTIONRECTSIZE, SELECTIONRECTSIZE);
	}

}