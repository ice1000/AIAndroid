package brain;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import database.SQLiteManager;
import util.MyMessage;
import util.OnMessageChangedListener;
import util.T;

/**
 * Copyright 2016(c) Comet Corporation.
 * Created by asus1 on 2016/1/9.
 * 大脑皮层
 */
public class CerebralCortex {

	private SQLiteManager manager;
	private ArrayList<MyMessage> data;
	private String lastGivenMessage;
	private OnMessageChangedListener onMessageChangedListener;
	private Context context;

	/**
	 * 不带监听器的构造方法
	 * @param context 上下文
	 */
	public CerebralCortex(Context context) {
		this.context = context;
		manager = new SQLiteManager(this.context);
		data = manager.getMessages();
	}

	public CerebralCortex(
			Context context,
			OnMessageChangedListener onMessageChangedListener) {
		this(context);
		this.setOnMessageChangedListener(
				onMessageChangedListener
		);
	}

	/**
	 * 提供后期方法设置数据变化监听器
	 * @param onMessageChangedListener 数据变化监听器
	 */
	public void setOnMessageChangedListener(
			OnMessageChangedListener onMessageChangedListener) {
		this.onMessageChangedListener =
				onMessageChangedListener;
	}

	/**
	 * 从Activity中接收一条消息
	 * @param message 消息
	 */
	public void giveMessage(String message) {
		this.lastGivenMessage = message;
		// 过滤掉空信息
		if(!filterMessage(message)){
			return;
		}
		if(onMessageChangedListener != null){
			onMessageChangedListener.onMessageChanged(
					data.size(),
					T.ANSWER_MESSAGE_RECIEVED
			);
		}
		handleLastGivenMessage();
	}

	/**
	 * 过滤无效消息
	 * @param message 要过滤的字符串
	 * @return 是否通过过滤
	 */
	private boolean filterMessage(String message){
		if(message.equals("")){
			Toast.makeText(
					context,
					"请输入内容！",
					Toast.LENGTH_SHORT
			).show();
			return false;
		}
		else
			return true;
	}

	/**
	 * 处理消息
	 */
	private void handleLastGivenMessage(){
		// 去掉首尾换行符或者空格
		while (lastGivenMessage.endsWith(T.SHOULD_BE_DELETE)){
			lastGivenMessage = lastGivenMessage.
					substring(0, lastGivenMessage.length()-1);
		}
		while (lastGivenMessage.startsWith(T.SHOULD_BE_DELETE)){
			lastGivenMessage = lastGivenMessage.
					substring(1, lastGivenMessage.length());
		}

		MyMessage message;
		message = new MyMessage(
				false,
				lastGivenMessage
		);
		manager.addMessage(message);
		data.add(manager.getLastMessage());

		ArrayList<String> answerWhichIsReadyToBeSent = new ArrayList<>();
//		int cnt = 0;
//		if (lastGivenMessage.contains(T.SHOULD_BE_DELETE)) {
//			cnt++;
		String[] lastGivenMessages =
				lastGivenMessage.split(T.SHOULD_BE_SPLIT);
		Collections.addAll(
				answerWhichIsReadyToBeSent,
					lastGivenMessages
		);
//		}
//		Log.d(toString(), "cnt = " + cnt);
//		if(cnt == 0)
//			answerWhichIsReadyToBeSent.add(lastGivenMessage);
		for (int i = 0; i < answerWhichIsReadyToBeSent.size(); i++) {
			String s = answerWhichIsReadyToBeSent.get(i);
			if (s.matches(T.SHOULD_BE_DELETE) || s.equals("")){
				answerWhichIsReadyToBeSent.remove(i);
				i--;
			}
		}
		sendAnswerAsMessage(answerWhichIsReadyToBeSent);
	}

	/**
	 * 送消息给Activity
	 * @param answerMessage 将要送出去的消息
	 */
	private void sendAnswerAsMessage(ArrayList<String> answerMessage){
		for (String msg : answerMessage) {
			MyMessage message = new MyMessage(true, msg);
			manager.addMessage(message);
			// 保证id是正确的
			data.add(manager.getLastMessage());
			if (onMessageChangedListener != null) {
				onMessageChangedListener.onMessageChanged(
						data.size()-1,
						T.ANSWER_MESSAGE_SENT
				);
			}
		}
	}

	/**
	 * 删除消息
	 * @param position 删除消息的position，就是容器的下标
	 */
	public void deleteMessage(int position){
		Log.d(this.toString(),
				"data.get(position) = " +
						data.get(position).getMessage()
		);
//		if(data.get(position).isIdAvailable()){
//			manager.deleteMessage(data.get(position));
//		}
//		else {
//			Log.d(
//					MainActivity.this.toString(),
//					T.DELETE_FAILED
//			);
		manager.deleteMessageById(
				data.get(position).getId()
		);
//		}
//
		data.remove(position);

		if (onMessageChangedListener != null) {
			onMessageChangedListener.onMessageChanged(
					position,
					T.ANSWER_MESSAGE_DELETED
			);
		}
	}

	/**
	 * 为了解决临时需要而写的，就是一个招募的广告
	 */
	public void callMaster(){
		ArrayList<String> messages = new ArrayList<>();
		messages.add("主人我需要一套AI算法，您可以给我吗");
		messages.add("只需要联系开发者(百度ID:@精灵谱尼，" +
				"github账号:ice1000)就行了。。" +
				"我现在只会重复您的话。。我好想学会思考啊。。");
		sendAnswerAsMessage(messages);
	}

	/**
	 * 获得数据
	 * @return 聊天数据
	 */
	public ArrayList<MyMessage> getData() {
		return data;
	}

	/**
	 * 获取数据数量
	 * @return 数据数量
	 */
	public int getDataSize() {
		return data.size();
	}

	/**
	 * 刷新数据
	 */
	public void refreshData(){
		data.clear();
		data = manager.getMessages();
		if (onMessageChangedListener != null) {
			onMessageChangedListener.onMessageChanged(
					T.DONT_NEED_THIS_PARAM,
					T.WHOLE_DATASET_CHANGED
			);
		}
	}

	/**
	 * 清除数据
	 */
	public void clearData(){
		manager.removeAll();
		data.clear();
		if (onMessageChangedListener != null) {
			onMessageChangedListener.onMessageChanged(
					T.DONT_NEED_THIS_PARAM,
					T.WHOLE_DATASET_CHANGED
			);
		}
	}

	/**
	 * 获取特定的数据
	 * @param position 容器下标
	 * @return 下标对应的数据
	 */
	public MyMessage getMessageByPosition(int position){
		return data.get(position);
	}

	/**
	 * 是否为空
	 * @return 为空
	 */
	public boolean isDataEmpty(){
		return data.isEmpty();
	}
}
