package brain.castle.funcs.using;

import castle.Game;
import funcs.FuncSrc;

public class FuncSave extends FuncSrc {

	public FuncSave(Game game) {
		super(game);
	}
	
	@Override
	public void DoFunc(String cmd) {
		game.saveData();
	}

}
