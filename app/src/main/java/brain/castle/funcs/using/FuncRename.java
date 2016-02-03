package brain.castle.funcs.using;

import castle.Game;
import funcs.FuncSrc;

/**
 * 重命名
 * Created by asus1 on 2016/1/29.
 */
public class FuncRename extends FuncSrc {

	public FuncRename(Game game) {
		super(game);
	}

	@Override
	public void DoFunc(String cmd) {
//		game.echoln("请问您的新名字是？");
		if(!cmd.equals("")){
			game.getPlayer().rename(cmd);
			game.echoln("重命名成功。新名字：" + cmd);
		}
		else {
			game.echoln("格式错误。请按照\"rename [新名字]\"的格式重命名！");
		}
	}

}
