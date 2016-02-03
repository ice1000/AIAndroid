package brain.castle.cells;

import funcs.using.FuncSleep;
import util.Echoer;

//import java.util.Scanner;

public class Boss extends Player {
	//	和玩家一样，有血、攻防
	private String dieText = "";
	private boolean survive = true;
	private boolean getItem = true;

	public Boss(String name, int blood, int strike, int defence, int experience, String dieText) {
		this(name,blood,strike,defence,experience);
		this.dieText = dieText;
	}

	public Boss(String name, int blood, int strike, int defence, int experience) {
		super(name,blood,strike,defence);
		this.experience = experience;
		dieText = name + "跪着向你哀求，不过你残忍地！";
	}

	public Player fight(Player player, Echoer echoer) {

		StringBuilder stringBuffer = new StringBuilder();
		int bloodSave = this.blood;
		int bloodSave2 = player.blood;
		int beBeat = (this.strike - player.getDefence());
		int Beat = (player.getStrike() - this.defence);

		if( beBeat <= 0 ) {
			beBeat = 0;
		}
//			打不过
		if( Beat <= 0 ){
			player.blood -= 10;
			stringBuffer
					.append("你的攻击力小于")
					.append(this.name)
					.append("的防御力！\n落荒而逃！损失10点体力值！\n");
		}
		else{
			while(survive){
//					互相扣血
				this.blood -= Beat;
				player.blood -= beBeat;
//					判断
				if( player.blood <= 0 ){

					bloodSave2 -= 5;
					player.blood = bloodSave2;

					this.blood = bloodSave;
					stringBuffer.append("以你现有的体力值无法打倒").append(this.name).append("！\n落荒而逃！损失5点体力值！\n");
					break;
				}
				if( this.blood <= 0 ){
//						先把血补回去
					this.blood = bloodSave;
					stringBuffer
							.append(dieText)
							.append("\n胜利而归！你还剩")
							.append(player.blood)
							.append("点体力值！\n")
							.append("本次战斗获得了")
							.append(player.win(getExperience(), echoer))
							.append("点经验值！\n");
					survive = false;
					getItem = false;
					FuncSleep sleep = new FuncSleep();
					stringBuffer.append("战斗结束");
					stringBuffer.append("\n");
				}
			}
		}
		echoer.echo(stringBuffer.toString());
		survive = true;
		return player;
	}

	@Override
	public String toString() {
		return super.getName();
	}

	public boolean ifGet() {
		return getItem;
	}

	public void setGetItem(boolean getItem) {
		this.getItem = getItem;
	}

	public int getExperience(Echoer echoer) {
		if( getItem ){
			echoer.echoln("Boss挑战成功，获得挑战奖励和额外5点经验奖励！");
			return (this.experience+5);
		}
		else
			return this.experience;
	}

	public NPC toNPC(String chat){
		return new NPC(name, chat);
	}
}
