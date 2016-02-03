package brain.castle.cells;

public class Item {
	private String name = "";
	private boolean get = false;
	private int num = 1;
	
	public Item(String name) {
		super();
		this.name = name;
	}

	public Item(String name, int num) {
		this.name = name;
		this.num = num;
	}

//	public void getNumOf(int num) {
//		this.num -= num;
//	}

	public void get() {
		get = true;
	}
	
	public boolean check() {
		return get;
	}

	@Override
	public String toString() {
		return name;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
