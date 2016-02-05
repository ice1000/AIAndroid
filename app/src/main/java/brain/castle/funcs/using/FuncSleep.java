package brain.castle.funcs.using;

import brain.castle.castle.Game;
import brain.castle.funcs.FuncSrc;

public class FuncSleep extends FuncSrc {

	public FuncSleep(Game game) {
		super(game);
	}

	public FuncSleep() {}

	@Override
	public void DoFunc(String cmd) {
//		int bloodMore = Integer.parseInt(cmd);
		if( game.getMap().getCurrentRoom().toString().matches("旅馆|卧室") ){
			if( !game.getMap().getCurrentRoom().isBossGetItem() ){
				game.echo("女仆顺从地送你进入梦乡。睡觉中");
				for(int i = 0; i < 8; i ++ ) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				game.echo("\n已经睡觉，体力");
				if( game.getPlayer().treat() )
					game.echoln("恢复至120.");
				else
					game.echoln("超过120不用恢复的~");
			}
			else
				game.echoln("睡觉需要女仆服侍，然而她看起来不大愿意啊。。。");
		}
		else
			game.echoln("只有宾馆或卧室能睡觉。");
	}

}
