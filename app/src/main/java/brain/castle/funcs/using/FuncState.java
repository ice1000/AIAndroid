package brain.castle.funcs.using;

import castle.Game;
import funcs.FuncSrc;

public class FuncState extends FuncSrc {
	
	public FuncState(Game game) {
		super(game);
	}

	@Override
	public void DoFunc(String cmd) {
		game.echoln(game.getPlayer().stateToString());
	}

}
