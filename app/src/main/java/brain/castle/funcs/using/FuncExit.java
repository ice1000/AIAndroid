package brain.castle.funcs.using;

import brain.castle.castle.Game;
import brain.castle.funcs.FuncSrc;

public class FuncExit extends FuncSrc {
	
	public FuncExit(Game game) {
		super(game);
	}

	private boolean Bye() {
		isGameEnded = true;
		return true;
	}

	@Override
	public void DoFunc(String cmd) {
		game.saveData();
		Bye();
	}

}
