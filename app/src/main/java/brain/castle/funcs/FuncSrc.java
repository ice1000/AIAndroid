package brain.castle.funcs;

import brain.castle.castle.Game;

public abstract class FuncSrc {
	
	protected Game game;
	protected boolean isGameEnded = false;
	
	public FuncSrc(Game game) {
		this.game = game;
	}
	
	protected FuncSrc(){}
	
	public boolean isGameEnded() {
		return isGameEnded;
	}

	public abstract void DoFunc(String cmd);

}
