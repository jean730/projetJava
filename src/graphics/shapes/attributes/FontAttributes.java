package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FontAttributes extends Attributes {

	public static String ID = "font";
	
	private Font font;
	public Color fontColor;
	private FontMetrics metrics;
	
	public FontAttributes() {
		setFont(new Font("Noto Mono",Font.PLAIN,20));
		this.fontColor = Color.BLACK;
	}

	public Font getFont() {
		return this.font;
	}
	public void setFont(Font font) {
		this.font = font;
		Graphics g = (new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)).getGraphics();
		g.setFont(this.font);
		this.metrics = g.getFontMetrics();		
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	public Rectangle getBounds(String string) {
		return new Rectangle(0,0,this.metrics.stringWidth(string),this.metrics.getHeight());
	}
}
