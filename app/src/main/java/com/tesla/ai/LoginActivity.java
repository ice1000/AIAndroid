package com.tesla.ai;

import android.content.Intent;
import android.os.Bundle;

import util.BaseActivity;

public class LoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

//		showTitle();

		doStunts();

		new Thread(){

			@Override
			public void run() {
				super.run();
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				startActivity(new Intent(
						LoginActivity.this, MainActivity.class
				));

				finish();
			}
		}.start();
	}

	private void doStunts(){
		playFrameAnimation(
				R.id.ncFloating,
				R.drawable.nc_floating
		);
		playTweenAnimation(
				R.id.roundFore,
				R.anim.progress_turning
		);

//		final Handler handler = new Handler(){
//			@Override
//			public void handleMessage(Message msg) {
//				super.handleMessage(msg);
//				switch (msg.what){
//					case 0:
//						NCCard.setVisibility(View.INVISIBLE);
//						logoTextView.setVisibility(View.INVISIBLE);
//						break;
//					case 1:
//						Saber.setVisibility(View.VISIBLE);
//						saberTextView.setVisibility(View.VISIBLE);
//						break;
//					default:
//						break;
//				}
//			}
//		};

//		new Thread(){
//			@Override
//			public void run() {
//				super.run();
//				Message message;
//				try {
//					sleep(2500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				message = new Message();
//				message.what = 0;
//				handler.sendMessage(message);
//				try {
//					sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				message = new Message();
//				message.what = 1;
//				handler.sendMessage(message);
//			}
//		}.start();
	}

//	private void showTitle(){
//
//		LimbicSystem memories = new LimbicSystem(this);
//
//		TextView title;
//		title = (TextView) findViewById(R.id.meetTitle);
//
//		String text = memories.getAppTitle();
//		Log.d(this.toString(), "text = " + text);
//		title.setText(text);
//
//	}
}
