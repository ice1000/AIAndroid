package brain.castle.database;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import brain.castle.cells.Player;
import brain.castle.map.GameMap;

/**
 * 封装数据库操作
 * Created by asus1 on 2016/1/28.
 */
public class Database {
	private static String savePath ;
	private String playerName = "";
	private char[] roomsState ;
	private String roomName;
	private int blood = 0;
	private int strike = 0;
	private int defence = 0;
	private int level = 0;
	private int experience = 0;

	public Database(String savePath) {
		Database.savePath = savePath;
		File file = new File(savePath);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));

			roomName = reader.readLine();
			roomsState = reader.readLine().toCharArray();
			playerName = reader.readLine();
			blood      =  Integer.parseInt(reader.readLine());
			strike     =  Integer.parseInt(reader.readLine());
			defence    =  Integer.parseInt(reader.readLine());
			level      =  Integer.parseInt(reader.readLine());
			experience =  Integer.parseInt(reader.readLine());

			reader.close();
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public void loadMap(GameMap map, String defaultName){
		map.setRoomsState(roomsState);
		if(roomName == null)
			roomName = defaultName;
		map.loadRoom(roomName);
	}

	public void saveMap(GameMap map) throws IOException {
		File file = new File(savePath);
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		this.roomName = map.getCurrentRoom().toString();
		this.roomsState = map.getRoomsState();
		writer.write(this.toString());
		writer.close();
	}

	public void saveMapAndState(GameMap map, Player player) throws IOException{
		File file = new File(savePath);
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		this.roomName = map.getCurrentRoom().toString();
		this.roomsState = map.getRoomsState();
		this.playerName = player.toString();
		this.blood      = player.getBlood();
		this.strike     = player.getStrike();
		this.defence    = player.getDefence();
		this.level      = player.getLevel();
		this.experience = player.getExperience();

		writer.write(this.toString());
		writer.close();
	}

	@Override
	public String toString() {
		return
				this.roomName   +  "\r\n" +
				String.valueOf(this.roomsState) +  "\r\n" +
				this.playerName +  "\r\n" +
				this.blood      +  "\r\n" +
				this.strike     +  "\r\n" +
				this.defence    +  "\r\n" +
				this.level      +  "\r\n" +
				this.experience +  "\r\n"
				;
	}

	public void loadState(Player player){
		player.setValues(
				playerName,
				blood,
				strike,
				defence,
				level,
				experience
		);
	}

	public void saveState(Player player) throws IOException {
		File file = new File(savePath);
		BufferedWriter writer;
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		writer = new BufferedWriter(new FileWriter(file));
		this.playerName = player.toString();
		this.blood      = player.getBlood();
		this.strike     = player.getStrike();
		this.defence    = player.getDefence();
		this.level      = player.getLevel();
		this.experience = player.getExperience();

		writer.write(this.toString());

		writer.close();
	}

	public static boolean isFileExists(){
		return new File(savePath).exists();
	}
}
