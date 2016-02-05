package brain.castle.funcs.using;

import brain.castle.castle.Game;
import brain.castle.funcs.FuncSrc;

public class FuncSave extends FuncSrc {

	public FuncSave(Game game) {
		super(game);
	}
	
	@Override
	public void DoFunc(String cmd) {
		game.saveData();
	}

}
