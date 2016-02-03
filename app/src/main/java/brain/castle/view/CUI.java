package brain.castle.view;

import castle.Game;

import java.util.Scanner;

/**
 * 这个是针对没有GUI的OS的CUI版
 * Created by asus1 on 2016/1/31.
 */
public class CUI extends Game {

	private CUI() {
		super();
	}

	@Override
	public void echo(String words) {
		System.out.print(words);
	}

	@Override
	public void echoln(String words) {
		echo(words + '\n');
	}

	@Override
	public void closeScreen() {
		System.exit(0);
	}

	private void onResume() {
		String line;
		boolean loop = true;
		Scanner in = new Scanner(System.in);
		while ( loop ) {
			echoln("");
			line = in.nextLine();
			loop = HandleMessage(line);
		}
		in.close();
	}

	public static void main(String[] args) {
		CUI game = new CUI();
		game.onStart();
		game.onResume();
	}
}
