package brain.castle.util;

/**
 * 字符串枚举
 * Created by asus1 on 2016/1/27.
 */
public enum Direction {

	UP("up"),
	DOWN("down"),
	EAST("east"),
	WEST("west"),
	NORTH("north"),
	SOUTH("south")
	;

	String s;
	Direction(String s){
		this.s = s;
	}

	@Override
	public String toString() {
		return s;
	}
}
