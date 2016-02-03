package brain.castle.funcs.using;

import castle.Game;
import funcs.FuncSrc;

public class FuncHelp extends FuncSrc {

	public FuncHelp(Game game) {
		super(game);
	}

	@Override
	public void DoFunc(String cmd) {
		game.echoln("迷路了吗？你可以做的命令有：");
		String[] strings = game.getFuncs();
		for(String s : strings){
			game.echoln(s);
		}
		game.echoln("有些需要参数的命令请按如下格式输入：");
		game.echoln("命令 [参数]");
		game.echoln("如：go east");
		game.echoln("如：rename 冰封");
	}
}
