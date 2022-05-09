package graphics.shapes;

public interface ShapeVisitor {

  public abstract void visitRectangle(SRectangle rect);

  public abstract void visitImage(SImage image);

  public abstract void visitCollection(SCollection collection);

  public abstract void visitCircle(SCircle sCircle);

  public abstract void visitText(SText sText);
}
