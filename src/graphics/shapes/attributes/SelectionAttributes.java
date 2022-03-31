package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {
	
	private Boolean selected = false;
	
	@Override
	public String getId() {
		return "selection";
	}
	
	public Boolean isSelected() {
		return selected;
	}
	
	public void select() {
		this.selected = true;
	}
	
	public void unselect() {
		this.selected = false;
	}
	
	public void toggleSelection() {
		this.selected = !this.selected;
	}
}
