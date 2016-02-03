package brain.castle.util;

/**
 * 命名器
 * Created by asus1 on 2016/1/29.
 */
public class NameGenerator {
	public NameGenerator() {}
	private static String[] names = {
			"金木研",
			"赤羽业",
			"泉新一",
			"千里冰封",
			"奶茶",
			"3A",
			"无",
			"SpiderKing",
			"Direction.D.",
			"Timothy",
	};

	public static String generate(){
		double i = Math.random();
		return names[(int) (names.length * i)];
	}
}
