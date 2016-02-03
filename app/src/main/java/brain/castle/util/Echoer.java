package brain.castle.util;

/**
 * 用于传递echo方法，控制反转
 * Created by asus1 on 2016/1/29.
 */
public interface Echoer {
	void echo(String words);
	void echoln(String words);
	void closeScreen();
}
