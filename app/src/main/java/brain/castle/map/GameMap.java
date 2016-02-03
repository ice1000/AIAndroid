package brain.castle.map;

import castle.Game;
import util.Direction;
import util.DirectionPair;

import java.util.ArrayList;

/**
 * 地图类
 * Created by asus1 on 2016/1/27.
 */
public class GameMap {

	private ArrayList<Room> theRooms;
	private Room currentRoom;
	private static final DirectionPair[] pairs ={
		new DirectionPair(Direction.UP,   Direction.DOWN ),
		new DirectionPair(Direction.NORTH,Direction.SOUTH),
		new DirectionPair(Direction.EAST, Direction.WEST ),
	};

	public GameMap() {
		theRooms = new ArrayList<>();
		//	构造地图结构
        /*0*/theRooms.add(new Room("城堡外","英俊的小偷头目",
				200,25,10,15,"小偷头目的钱全掉出来了！"));
        /*1*/theRooms.add(new Room("一楼大堂","欢迎来到城堡！","礼貌的大堂经理",
				100,15,12,8,"大堂经理的帐算错了！"));
        /*2*/theRooms.add(new Room("小酒吧","一大股酒香飘来。","潇洒的酒吧流氓",
				150,10,5,5,"酒吧流氓喝醉了！"));
        /*3*/theRooms.add(new Room("书房","读书的气氛很浓厚。" , "优雅的读书人",
				100,7,5,3,"读书人的书掉出来了！"));
        /*4*/theRooms.add(new Room("旅馆", "周围干净整洁。", "可爱的女仆",
				10,6,3,2,"女仆被你推倒了！"));
        /*5*/theRooms.add(new Room("二楼睡房","公主的管家",
				300,20,5,25,"管家扑街、公主被你推倒了！"));
        /*6*/theRooms.add(new Room("负一楼","奇怪的男人",
				200,30,15,25,"男人身边站出来一名浑身是伤的女孩。。"));
        /*7*/theRooms.add(new Room("负二楼","穿着霸气的绅士",
				100,50,35,35,"绅士的衣服脏了！"));
        /*8*/theRooms.add(new Room("负三楼","身穿铠甲的战士",
				300,30,25,45,"战士被自己绊倒了！"));
        /*9*/theRooms.add(new Room("负四楼","持剑的骑士",
				400,40,35,60,"骑士的剑断了！"));
        /*10*/theRooms.add(new Room("三楼阳台"));
        /*11*/theRooms.add(new Room("城堡顶部瞭望塔", "瞭望塔守卫",
				150, 20, 2, 20, "守卫倒下了！"));
        /*12*/theRooms.add(new Room("羊肠小道", "街边小混混",
				100,30,1,20 ,null));
        /*13*/theRooms.add(new Room("日出村大门","欢迎来到城堡西边的日出村！", "和善的门卫",
				150,20,20,30 ,"门卫露出了和善的笑容"));
        /*14*/theRooms.add(new Room("神秘空间祭坛","冰封",
				1000,150,100,200,"冰封认真地写着客户端。。。"));
        /*15*/theRooms.add(new Room("神秘空间西","无",
				1000,150,100,200,"无认真地写这服务器端。。。"));
        /*16*/theRooms.add(new Room("神秘空间东","奶茶",
				1000,150,100,200,"奶茶去复习考试了。。。"));
        /*17*/theRooms.add(new Room("神秘空间北","果冻",
				1000,150,100,200,"果冻正在打酱油。。。"));
        /*18*/theRooms.add(new Room("日出村民居"));
        /*19*/theRooms.add(new Room("日出村教堂","你瞬间被这里神圣的气息闪瞎了。" , "聆听忏悔的牧师",
				200, 30, 20, 40, "牧师聆听着忏悔。"));
        /*20*/theRooms.add(new Room("神秘的井", "打水的熊孩子",
				50, 10, 1, 5, "熊孩子掉头就跑。"));
        /*21*/theRooms.add(new Room("井底", "这里很潮湿，阴森恐怖。", "青蛙怪",
				300, 80, 50, 70, "青蛙怪被烤熟了！"));
        /*22*/theRooms.add(new Room("井底北", "更加潮湿了。", "戴皇冠的青蛙怪王",
				500, 100, 40, 100, "青蛙怪王的皇冠掉了下来！"));
        /*23*/theRooms.add(new Room("井底密室", "空气中弥漫着阴冷潮湿的气息。", "戴头灯的探险家",
				400, 80, 30, 80, "探险家的头灯没电了！"));

		setExit(1, 5, pairs[0]);
		setExit(5, 10,pairs[0]);
		setExit(10,11,pairs[0]);
		setExit(6, 1, pairs[0]);
		setExit(7, 6, pairs[0]);
		setExit(8, 7, pairs[0]);
		setExit(9, 8, pairs[0]);
		setExit(20,21,pairs[0]);
		setExit(3, 0, pairs[1]);
		setExit(20,19,pairs[1]);
		setExit(4, 1, pairs[1]);
		setExit(21,22,pairs[0]);
		setExit(22,23,pairs[0]);
		setExit(14,17,pairs[1]);
		setExit(0, 1, pairs[2]);
		setExit(2, 0, pairs[2]);
		setExit(3, 4, pairs[2]);
		setExit(12,2, pairs[2]);
		setExit(13,12,pairs[2]);
		setExit(15,14,pairs[2]);
		setExit(14,16,pairs[2]);
		setExit(17,14,pairs[2]);
		setExit(18,13,pairs[2]);
		setExit(19,18,pairs[2]);

		// 从女仆那里开始
		currentRoom = theRooms.get(4);
	}

	private void setExit(int index_a, int index_b, DirectionPair pair){
		theRooms.get(index_a).setExit(String.valueOf(pair.getDirection1()), index_b);
		theRooms.get(index_b).setExit(String.valueOf(pair.getDirection2()), index_a);
	}

	public void setCurrentRoom(Room room){
		currentRoom = room;
	}

	public boolean goRoom(String direction){
		if( currentRoom.checkExit(direction) ) {
			currentRoom = theRooms.get(currentRoom.showRoomId(direction));
			return true;
		}
		else
			return false;
	}

	public boolean isRoomExists(String roomName){
		for (Room room : theRooms) {
			if(room.equals(roomName)){
				return true;
			}
		}
		return false;
	}

	public Room getHome(){
		return theRooms.get(4);
	}

	public void setRoomsState(char[] state){
		for (int i = 0; i < theRooms.size(); i++) {
			char c;
			try{c = state[i];}
			catch (Exception e){c = 1;}
			theRooms.get(i).setBossGetItem(c == '1');
		}
	}

	public void loadRoom(String room_){
		for (Room room : theRooms) {
			if(room.equals(room_)){
				currentRoom = room;
				break;
			}
		}
	}

	public char[] getRoomsState(){
		char[] roomsState = new char[theRooms.size()];
		for (int i = 0; i < theRooms.size(); i++)
			roomsState[i] = theRooms.get(i).isBossGetItem() ? '1' : '0';
		return roomsState;
	}

	public String wildRoom(){
		int index = (int) (Math.random()*2000);
		index %= theRooms.size();
		currentRoom = theRooms.get(index);
		return currentRoom.getPrompt();
	}

	public void fightBoss(Game game){
		game.setPlayer(currentRoom.fightBoss(game.getPlayer(), game));
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}
}
