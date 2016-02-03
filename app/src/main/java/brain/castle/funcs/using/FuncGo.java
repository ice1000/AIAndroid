package brain.castle.funcs.using;

import castle.Game;
import funcs.FuncSrc;

public class FuncGo extends FuncSrc {

	public FuncGo(Game game) {
		super(game);
	}

	@Override
	public void DoFunc(String cmd) {
		// 调用移动。
		game.goRoom(cmd);
	}

}
