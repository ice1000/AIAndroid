package brain.castle.util;

/**
 * 一对方向
 * Created by asus1 on 2016/1/30.
 */
public class DirectionPair {

	private Direction direction1;
	private Direction direction2;

	public DirectionPair(Direction direction1, Direction direction2) {
		this.direction1 = direction1;
		this.direction2 = direction2;
	}

	public Direction getDirection1() {
		return direction1;
	}

	public Direction getDirection2() {
		return direction2;
	}
}
