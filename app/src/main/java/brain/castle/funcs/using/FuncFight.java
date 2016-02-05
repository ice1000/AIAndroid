package brain.castle.funcs.using;

import brain.castle.castle.Game;
import brain.castle.funcs.FuncSrc;

public class FuncFight extends FuncSrc {
	
	public FuncFight(Game game) {
		super(game);
	}

	@Override
	public void DoFunc(String cmd) {
		game.Fight();
	}

}
