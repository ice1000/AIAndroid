package brain.castle.funcs.using;

import brain.castle.castle.Game;
import brain.castle.cells.Item;
import brain.castle.funcs.FuncSrc;

public class FuncPack extends FuncSrc {

	public FuncPack(Game game) {
		super(game);
	}

	@Override
	public void DoFunc(String cmd) {
		game.echoln("背包中物品如下：");
		for (Item item : game.getTheItems()) {
			game.echoln(item.toString());
		}
	}

}
