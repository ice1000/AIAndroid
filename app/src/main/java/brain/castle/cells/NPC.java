package brain.castle.cells;

import java.util.ArrayList;

public class NPC extends Cell {

	private String chat = "";
	private ArrayList<Item> items = new ArrayList<>();

	public NPC(String name,String chat) {
		super(name);
		this.chat = chat;
	}

	public void itemGet(String name,int num){
		items.add(new Item(name, num));
	}

//	public void itemGive(int index,int num){
//		items.get(index).getNumOf(num);
//	}

	public String getChat() {
		return chat;
	}
}
