package brain.castle.funcs.using;

import brain.castle.castle.Game;
import brain.castle.funcs.FuncSrc;

public class FuncWild extends FuncSrc {

	public FuncWild(Game game) {
		super(game);
	}

	@Override
	public void DoFunc(String cmd) {
		// 传送
		game.WildRoom();
	}

}
