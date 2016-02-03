package brain.castle.funcs.using;

import castle.Game;
import funcs.FuncSrc;

/**
 * 回城堡
 * Created by asus1 on 2016/2/1.
 */
public class FuncHome extends FuncSrc {

	public FuncHome(Game game) {
		super(game);
	}

	@Override
	public void DoFunc(String cmd) {
		game.echoln("您与女仆的契约发动。回到了旅馆。");
		game.getMap().setCurrentRoom(game.getMap().getHome());
		game.echoln(game.getMap().getCurrentRoom().getPrompt());
	}
}
