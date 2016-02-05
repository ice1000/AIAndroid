package brain.castle.database;


import android.content.Context;
import android.content.SharedPreferences;

import brain.castle.cells.Player;
import brain.castle.map.GameMap;
import brain.castle.util.NameGenerator;

/**
 * 封装数据库操作
 * Created by asus1 on 2016/1/28.
 */
public class Database {
//	private static String savePath ;
	private Context context;
	private SharedPreferences preferences;

	private String playerName = "";
	private char[] roomsState ;
	private String roomName;
	private int blood = 0;
	private int strike = 0;
	private int defence = 0;
	private int level = 0;
	private int experience = 0;

	public static final String BLOOD = "BLOOD";
	public static final String STRIKE = "STRIKE";
	public static final String DEFENCE = "DEFENCE";
	public static final String LEVEL = "LEVEL";
	public static final String EXPERIENCE = "EXPERIENCE";
	public static final String ROOM_NAME = "ROOM_NAME";
	public static final String ROOM_STATE = "ROOM_STATE";
	public static final String PLAYER_NAME = "PLAYER_NAME";

	public static final String PREFERENCE = "PREFERENCE";

	public Database(Context context) {
		this.context = context;
		preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);

			roomName   = preferences.getString(ROOM_NAME, "旅馆");
			roomsState = preferences.getString(ROOM_STATE, "").toCharArray();
			playerName = preferences.getString(PLAYER_NAME, NameGenerator.generate());
			blood      = preferences.getInt(BLOOD, 100);
			strike     = preferences.getInt(STRIKE, 20);
			defence    = preferences.getInt(DEFENCE, 10);
			level      = preferences.getInt(LEVEL, 1);
			experience = preferences.getInt(EXPERIENCE, 0);

	}

	public void loadMap(GameMap map, String defaultName){
		map.setRoomsState(roomsState);
		if(roomName == null)
			roomName = defaultName;
		map.loadRoom(roomName);
	}

	public void saveMap(GameMap map){
		SharedPreferences.Editor editor = preferences.edit();
		this.roomName = map.getCurrentRoom().toString();
		this.roomsState = map.getRoomsState();
		editor.putString(ROOM_NAME, roomName);
		editor.putString(ROOM_STATE, String.valueOf(roomsState));
		editor.commit();
	}

	public void saveMapAndState(GameMap map, Player player){
		saveMap(map);
		saveState(player);
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

	public void saveState(Player player) {

		this.playerName = player.toString();
		this.blood      = player.getBlood();
		this.strike     = player.getStrike();
		this.defence    = player.getDefence();
		this.level      = player.getLevel();
		this.experience = player.getExperience();

		SharedPreferences.Editor editor = preferences.edit();

		editor.putString(PLAYER_NAME, playerName);
		editor.putInt(BLOOD, blood);
		editor.putInt(STRIKE, strike);
		editor.putInt(DEFENCE, defence);
		editor.putInt(LEVEL, level);
		editor.putInt(EXPERIENCE, experience);

		editor.commit();
	}

	public static boolean isFileExists(Context context){
		SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
		return preferences.contains(PLAYER_NAME);
	}
}
