package brain.castle.cells;

public class Cell {
	
	String name = "";

	Cell(String name) {
//		super();
		this.name = name;
	}
	
	public Cell(){
		name = "unKown";
	}

	public String getName() {
		return name;
	}
}
