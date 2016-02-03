package brain.castle.map;

import java.util.ArrayList;
import java.util.HashMap;

import cells.Boss;
import cells.NPC;
import cells.Player;
import com.sun.istack.internal.Nullable;
import util.Echoer;

public class Room {

	private Boss boss = null;
	private String description;
	private String welcomeWord;
	private HashMap<String, Integer> exits;
	private ArrayList<NPC> NPCs;

	//构造方法
	Room(String description) {
		this.description = description;
		exits = new HashMap<>();
		boss = null;
	}

	//构造方法
	Room(String description, String welcomeWord) {
		this(description);
		this.welcomeWord = welcomeWord;
	}

	Room(String description,
	     String BossName, int blood, int strike, int miss, int experience,@Nullable String dieText) {
		this(description,"欢迎来到这里。", BossName, blood, strike, miss, experience, dieText);
	}

	Room(String description, String welcomeWord,
	     String BossName, int blood, int strike, int miss, int experience,@Nullable String dieText) {
		this(description, welcomeWord);
		if(dieText != null){
			boss = new Boss(BossName,blood,strike,miss,experience,dieText);
		}
		else {
			boss = new Boss(BossName,blood,strike,miss,experience);
		}
		NPCs = new ArrayList<>();
		exits = new HashMap<>();
//		NPCs.add(boss);
	}

	//返回房间名
	@Override
	public String toString() {
		return description;
	}

	//检查房间名
	@Override
	public boolean equals(Object anotherOne) {
		return description.equals(anotherOne);
	}

	//    设置一个出口。
	void setExit(String str, int targetRoomId){
		exits.put(str, targetRoomId);
	}

	//   显示房间的详情。
	public String getPrompt() {
		StringBuilder sb = new StringBuilder();
		String ifaBoss = "这里安全。";
		sb.append(welcomeWord).append('\n');
		sb.append("你在").append(this.description).append('\n');
		sb.append("出口有: ");
		for ( String str : exits.keySet() ){
			sb.append(str).append(' ');
		}
		sb.append('\n');
		if(boss != null) {
			if( boss.ifGet() )
				ifaBoss = "冰封".equals(boss.toString()) ?
						"你来到了神秘空间。这里只能通过\\wild传送离开。冰封正坐在这写码呢。"
						: "这里的Boss是"+ boss +",正准备接受你的挑战呢！";
			else
				ifaBoss = "这里的Boss是"+ boss +",已经被你打败过啦O(∩_∩)O哈哈~";
		}
		sb.append(ifaBoss);
		if(NPCs != null && NPCs.size() > 0) {
			sb.append("这里还有：\n");
			for (NPC npc : NPCs) {
				sb.append(npc.getName());
			}
		}
		return sb.toString();
	}
	//   使用此类的返回值，赋给原本的Room。
	int showRoomId(String direction) {
		return exits.get(direction);
	}
	//   战斗函数
	Player fightBoss(Player player, Echoer echoer) {
		return boss.fight(player, echoer);
	}

	//    检查Boss是否已经被挑战过
	public boolean isBossGetItem() {
		try {
			return boss.ifGet();
		} catch (NullPointerException e){
			return true;
		}
	}

	void setBossGetItem(boolean isGet){
		if(boss != null){
			boss.setGetItem(isGet);
		}
	}

	public NPC isNPCExists(String name) {
		for (NPC npc : NPCs) {
			if(name.equals(npc.getName()))
				return npc;
		}
		return null;
	}

	boolean checkExit(String exit) {
		return exits.containsKey(exit);
	}
}
