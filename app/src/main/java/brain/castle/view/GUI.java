package brain.castle.view;

import brain.castle.castle.Game;
import brain.castle.util.Echoer;

/**
 * 视图
 * Created by asus1 on 2016/1/31.
 */
public class GUI extends Game
		implements Echoer {


	public GUI() {
		frame = new JFrame("城堡游戏   by 千里冰封");
		textField = new JTextField("在这里输入指令");
		textField.registerKeyboardAction(e -> {
					HandleMessage(textField.getText());
					textField.setText("");
				},
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
				JComponent.WHEN_FOCUSED
		);
		textArea = new JTextArea();

		textArea.setBackground(new Color(12, 29, 39));
		textArea.setForeground(new Color(151, 212, 30));
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage(
				"." + File.separator + "src" + File.separator + "drawable" + File.separator + "castle-icon.png"
		));
		frame.setSize(FRAME_X, FRAME_Y);
		// 绝对布局
		// frame.setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(textField, BorderLayout.SOUTH);
		scrollPane = new JScrollPane(textArea);
		scrollBar = scrollPane.getVerticalScrollBar();
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setLocation(FRAME_X / 8,FRAME_Y / 8);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		GUI game = new GUI();
		game.onStart();
	}

	@Override
	public void echo(String words){
//		System.out.print(words);
		textArea.append(words);
		int i = textArea.getText().length();
		int MAX_LENGTH = 10000;
		if(i > MAX_LENGTH){
			textArea.setText(textArea.getText().substring(
					i - MAX_LENGTH, i
			));
		}
//		scrollBar.setValue(scrollBar.getMaximum() - 20);
		// 滚动到最底下
		int height=10;
		Point p = new Point();
		p.setLocation(0,this.textArea.getLineCount()*height);
		this.scrollPane.getViewport().setViewPosition(p);
	}

	@Override
	public void echoln(String words){
		echo(words + "\n");
	}

	@Override
	public void closeScreen() {
		frame.dispose();
	}

}
