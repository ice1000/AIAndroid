package com.tesla.ai;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import brain.CerebralCortex;
import util.MyMessage;
import util.OnItemClickListener;
import util.OnMessageChangedListener;
import util.T;

public class MainActivity extends AppCompatActivity {

	private int nowBackgroundColor;
	private RecyclerView messageRecycler;
	private MessageAdapter adapter;
	private EditText editMessage;
	private CerebralCortex brain;
	private DrawerLayout drawerLayout;
//	private Handler brainMessageHandler;
//		brainMessageHandler = new Handler(){
//			@Override
//			public void handleMessage(Message msg) {
//				super.handleMessage(msg);
//				switch (msg.what){
//					case T.ANSWER_MESSAGE_SENT:
//						adapter.notifyItemInserted(brain.getDataSize()-1);
//						break;
//					default:
//						Log.d(toString(), T.NO_MESSAGE_FOUND);
//						break;
//				}
//			}
//		};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 一定最先构造大脑皮层
		brain = new CerebralCortex(this, new OnMessageChangedListener() {
			@Override
			public void onMessageChanged(int position, int action) {
				switch (action) {
					case T.ANSWER_MESSAGE_SENT:
					case T.ANSWER_MESSAGE_RECIEVED:
						adapter.notifyItemInserted(position);
						break;
					case T.ANSWER_MESSAGE_DELETED:
						adapter.notifyItemRemoved(position);
						break;
					case T.WHOLE_DATASET_CHANGED:
						adapter.notifyDataSetChanged();
						break;
					default:
						break;
				}
			}
		});

		initViews();

//		brain.callMaster();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		switch (id) {
			case R.id.action_settings:
				startActivity(new Intent(
						MainActivity.this, SettingsActivity.class));
				return true;
			case R.id.action_refresh:
				brain.refreshData();
				return true;
			case R.id.action_removeAll:
				brain.clearData();
				if(nowBackgroundColor != T.BACKGROUND_COLOR_IS_0) {
					changeBackgroundColor(
							nowBackgroundColor ==
									T.BACKGROUND_COLOR_IS_1
									? R.animator.background_color_1_to_0
									: R.animator.background_color_5_to_0
					);
					nowBackgroundColor = T.BACKGROUND_COLOR_IS_0;
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode){
			case T.DELETE_REQUEST:
				switch (resultCode){
					case DeleteActivity.resultCode:
						if(data.getBooleanExtra(T.DELETE_OR_NOT, false)){
							// 因为我传递过去的时候是把position传递过去的
							// 所以我猜再传递回来并且拿给adapter是没有问题的
							int position = data.getIntExtra(T.POSITION, 0);

							// 先让大脑删除message
							brain.deleteMessage(position);
							if(brain.isDataEmpty()){
								changeBackgroundColor(
										nowBackgroundColor ==
												T.BACKGROUND_COLOR_IS_1
												? R.animator.background_color_1_to_0
												: R.animator.background_color_5_to_0
								);
							}
							Log.d(this.toString(), "data.getIntExtra(T.POSITION, 0) = " +
									data.getIntExtra(T.POSITION, 0));
						}
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}

	}

	/**
	 * 更换背景颜色
	 * @param id 背景颜色
	 */
	private void changeBackgroundColor(int id) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			ObjectAnimator objectAnimator;
			objectAnimator = (ObjectAnimator) AnimatorInflater
					.loadAnimator(MainActivity.this, id);
			//用于动画计算的需要，
			// 如果开始和结束的值不是基本类型的时候，这个方法是需要的。
			objectAnimator.setEvaluator(new ArgbEvaluator());
			//设置动画的设置目标
			objectAnimator.setTarget(messageRecycler);
			objectAnimator.start();
		}
	}

	/**
	 * 初始化一大堆View
	 */
	private void initViews(){
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		drawerLayout = (DrawerLayout) findViewById(R.id.mainDrawer);

		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
				this,
				drawerLayout,
				toolbar,
				R.string.open_draw,
				R.string.close_draw
		);
		drawerToggle.syncState();
		drawerLayout.setDrawerListener(drawerToggle);

		NavigationView navigationView =
				(NavigationView) findViewById(R.id.navigation);
		if (navigationView != null) {
			navigationView.setNavigationItemSelectedListener(
					new NavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem) {
					menuItem.setChecked(true);
					switch (menuItem.getItemId()){
						case R.id.makeSummary:
							Toast.makeText(MainActivity.this,
									"暂时没做完！",
									Toast.LENGTH_SHORT).show();
							break;
						case R.id.seeGithub:
							startActivity(new Intent(
									MainActivity.this,
									GithubActivity.class
							));
							finish();
							break;
						case R.id.goSettings:
							startActivity(new Intent(
									MainActivity.this,
									SettingsActivity.class
							));
							finish();
							break;
						default:
							break;
					}
					drawerLayout.closeDrawers();
					return false;
				}
			});
		}

		editMessage = (EditText) findViewById(R.id.sendBox);

		adapter = new MessageAdapter();

		messageRecycler = (RecyclerView) findViewById(R.id.messagesRecycler);
		nowBackgroundColor = T.BACKGROUND_COLOR_IS_0;

		messageRecycler.setLayoutManager(new LinearLayoutManager(this));
		messageRecycler.setItemAnimator(new DefaultItemAnimator());

		adapter.setOnItemClickListener(new OnItemClickListener() {
			@Override public void onItemClick(View view, int position) {}
			@Override
			public void onItemLongClick(View view, int position) {
				Intent intent = new Intent(
						MainActivity.this, DeleteActivity.class
				);
				intent.putExtra(T.POSITION, position);
				startActivityForResult(intent, T.DELETE_REQUEST);
			}
			@Override
			public void onItemTouch(View view, int position, MotionEvent event) {

				boolean isFromSaber =
						brain.getData().get(position).isFromSaber();
				int[] colorId;

				Log.d(this.toString(),
						"event.getAction() = " + event.getAction());

				switch (event.getAction()){
					case 0:
					case 2:
						colorId = isFromSaber ? new int[]{
								R.color.cardColor1Pressed,
								R.color.cardColor1
						} : new int[]{
								R.color.cardColor5Pressed,
								R.color.cardColor5
						};
						int id = isFromSaber
								? R.animator.background_color_1_to_5
								: R.animator.background_color_5_to_1;
						// 如果现在的颜色已经正常了那就不用变化了
						if(nowBackgroundColor == T.BACKGROUND_COLOR_IS_5)
							if(isFromSaber)
								break;
							else
								nowBackgroundColor = T.BACKGROUND_COLOR_IS_1;
						else
						if (nowBackgroundColor == T.BACKGROUND_COLOR_IS_1)
							if(!isFromSaber)
								break;
							else
								nowBackgroundColor = T.BACKGROUND_COLOR_IS_5;
						else
						if(nowBackgroundColor == T.BACKGROUND_COLOR_IS_0) {
							nowBackgroundColor = isFromSaber
									? T.BACKGROUND_COLOR_IS_5
									: T.BACKGROUND_COLOR_IS_1;

							// 重新给它赋值，从白色渐变过去
							id = isFromSaber
									? R.animator.background_color_0_to_5
									: R.animator.background_color_0_to_1;
						}
						changeBackgroundColor(id);
						break;
					default:

						colorId = isFromSaber ? new int[]{
								R.color.cardColor1,
								R.color.cardColor1Pressed
						} : new int[]{
								R.color.cardColor5,
								R.color.cardColor5Pressed
						};
						break;
				}

				CardView cardView = ((CardView)
						((FrameLayout) view).getChildAt(0));
				TextView textView = ((TextView)
						((CardView)((FrameLayout) view).getChildAt(0))
								.getChildAt(0));

				// cardView.setCardElevation(0);
				cardView.setCardBackgroundColor(
						getResources().getColor(colorId[0]));
				textView.setTextColor(
						getResources().getColor(colorId[1]));

			}
		});

		messageRecycler.setAdapter(adapter);

//		ImageView Saber = (ImageView) findViewById(R.id.saberShake);
//		Saber.setBackgroundResource(R.drawable.saber_shake);
//		((AnimationDrawable) Saber.getBackground()).start();
	}

	/**
	 * 接收一个消息
	 * @param view 监听器必备
	 */
	public void commitMessage(View view){
		String msg = editMessage.getText().toString();
		brain.giveMessage(msg);
		editMessage.setText("");
	}

	class MessageAdapter extends RecyclerView.Adapter {

		private OnItemClickListener onItemClickListener;

		public void setOnItemClickListener(
				OnItemClickListener onItemClickListener){
			this.onItemClickListener = onItemClickListener;
		}

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new MessageViewHolder(LayoutInflater.from(MainActivity.this)
					.inflate(R.layout.message, parent, false));
		}

		@Override
		public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

			((MessageViewHolder)holder).init(
					brain.getMessageByPosition(position)
			);

			if (onItemClickListener != null) {
				holder.itemView.setOnClickListener(
						new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						onItemClickListener.onItemClick(holder.itemView,
								holder.getLayoutPosition());
					}
				});

				holder.itemView.setOnLongClickListener(
						new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						onItemClickListener.onItemLongClick(holder.itemView,
								holder.getLayoutPosition());
						return false;
					}
				});

				holder.itemView.setOnTouchListener(
						new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						onItemClickListener.onItemTouch(holder.itemView,
								holder.getLayoutPosition(), event);
						return false;
					}
				});
			}
		}
		@Override
		public int getItemCount() {
			return brain.getDataSize();
		}

	}

	class MessageViewHolder extends RecyclerView.ViewHolder {

		private CardView cardView;
		private TextView textView;

		public MessageViewHolder(View itemView) {
			super(itemView);
			cardView = (CardView)
					itemView.findViewById(R.id.messageCard);
			textView = (TextView)
					itemView.findViewById(R.id.messageText);
		}

		public void init(MyMessage message){
			FrameLayout.LayoutParams params =
					new FrameLayout.LayoutParams(
							RelativeLayout.LayoutParams.WRAP_CONTENT,
							RelativeLayout.LayoutParams.WRAP_CONTENT
					);


			if(message.isFromSaber()){
				params.gravity = Gravity.LEFT;
				params.setMargins(10,11,30,11);

				cardView.setCardBackgroundColor(
						getResources().
								getColor(R.color.cardColor1));
				textView.setTextColor(
						getResources().
								getColor(R.color.cardColor1Pressed));
			}

			else {
				params.gravity = Gravity.RIGHT;
				params.setMargins(30,11,10,11);

				cardView.setCardBackgroundColor(
						getResources().
								getColor(R.color.cardColor5));
				textView.setTextColor(
						getResources().
								getColor(R.color.cardColor5Pressed));
			}

			cardView.setLayoutParams(params);
			textView.setText(message.getMessage());
		}
	}

}
