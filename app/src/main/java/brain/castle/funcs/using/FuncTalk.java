package brain.castle.funcs.using;

import castle.Game;
import cells.NPC;
import funcs.FuncSrc;

/**
 * 与NPC对话
 * Created by asus1 on 2016/1/30.
 */
public class FuncTalk extends FuncSrc {

	public FuncTalk(Game game) {
		super(game);
	}

	@Override
	public void DoFunc(String cmd) {
		NPC npc = game.getMap().getCurrentRoom().isNPCExists(cmd);
		if(npc != null){
			game.echoln(npc.getChat());
		}
		else{
			game.echoln("你指定的名字不存在。注：Boss要在被打败之后才能对话。");
		}
	}
}
