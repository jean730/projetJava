package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {

  public static String ID = "selection";

  private Boolean selected = false;

  @Override
  public String getId() {
    return ID;
  }

  public Boolean isSelected() { return selected; }

  public void select() { this.selected = true; }

  public void unselect() { this.selected = false; }

  public void toggleSelection() { this.selected = !this.selected; }
}
