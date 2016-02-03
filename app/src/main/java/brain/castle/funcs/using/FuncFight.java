package brain.castle.funcs.using;

import castle.Game;
import funcs.FuncSrc;

public class FuncFight extends FuncSrc {
	
	public FuncFight(Game game) {
		super(game);
	}

	@Override
	public void DoFunc(String cmd) {
		game.Fight();
	}

}
