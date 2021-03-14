package per.maingame;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class GameBox extends JFrame implements ActionListener {

	/**
	 * add a id 
	 */
	private static final long serialVersionUID = 1L;

	static GameBox mainFrame = new GameBox();
	GameLogic gameLogic=new GameLogic();
	GameControlTask gameCT=new GameControlTask();

	private int row=15;
	private int col=15;
	private int duration=200;
	private int times=100;
	private int personN=3;
	private int personC=0x000000;
	private static final int DEFAULT_DURATION = 200;
	private static final int DEFAULT_TIMES = 100;
	
	private boolean startState=false;
	private boolean waitState=false;
	
	private String word1="Amount：";
	private String word2=" pers";
	
	private JPanel contentPane;
	private JPanel panel;
	private JPanel animationPanel;
	private JButton [][]btns;
	private JLabel amountLabel;
	private JTextField timesField;
	private JTextField durationField;
	JButton continueBtn = new JButton("pause");
	JButton startBtn = new JButton("START");
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public GameBox() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459,478);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu sizeMenu = new JMenu("\u9875\u9762\u5927\u5C0F");
		menuBar.add(sizeMenu);
		
		JMenuItem sizeItem1 = new JMenuItem("15*15");
		sizeMenu.add(sizeItem1);
		sizeItem1.addActionListener(this);
		
		JMenuItem sizeItem2 = new JMenuItem("50*20");
		sizeMenu.add(sizeItem2);
		sizeItem2.addActionListener(this);
		
		JMenuItem sizeItem3 = new JMenuItem("100*50");
		sizeMenu.add(sizeItem3);
		sizeItem3.addActionListener(this);
		
		JMenu layoutMenu = new JMenu("\u63A8\u8350\u5E03\u5C40");
		menuBar.add(layoutMenu);
		
		JMenuItem layout1 = new JMenuItem("1");
		layoutMenu.add(layout1);
		layout1.addActionListener(this);
		
		JMenuItem layout2 = new JMenuItem("2");
		layoutMenu.add(layout2);
		layout2.addActionListener(this);
		
		JMenuItem layout3 = new JMenuItem("3");
		layoutMenu.add(layout3);
		layout3.addActionListener(this);
		
		JMenu customMenu = new JMenu("\u81EA\u5B9A\u4E49\u5E03\u5C40");
		menuBar.add(customMenu);
		
		JMenuItem perItem1 = new JMenuItem("p1");
		customMenu.add(perItem1);
		perItem1.addActionListener(this);
		
		JMenuItem perItem2 = new JMenuItem("p2");
		customMenu.add(perItem2);
		perItem2.addActionListener(this);
		
		JMenuItem perItem3 = new JMenuItem("p3");
		customMenu.add(perItem3);
		perItem3.addActionListener(this);
		
		JMenuItem perItem4 = new JMenuItem("p4");
		customMenu.add(perItem4);
		perItem4.addActionListener(this);
		
		JMenuItem perItem5 = new JMenuItem("p5");
		customMenu.add(perItem5);
		perItem5.addActionListener(this);
		
		JMenuItem perItem6 = new JMenuItem("p6");
		customMenu.add(perItem6);
		perItem6.addActionListener(this);
		
		JMenuItem eItem1 = new JMenuItem("e1");
		customMenu.add(eItem1);
		eItem1.addActionListener(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setToolTipText("");
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		panel.add(buttonPanel, BorderLayout.EAST);
		buttonPanel.setLayout(new GridLayout(9, 1, 2, 8));
		
		
		startBtn.addActionListener(new ActionListener() {
		/**
		 * click, start the game;
		 */
			public void actionPerformed(ActionEvent arg0) {
				if(!startState) {
					gameCT.start();
					startBtn.setText("END");
					continueBtn.setVisible(true);
				}else {
					startBtn.setText("START");
					gameCT.end();
					continueBtn.setVisible(false);
				}
			}
		});
		buttonPanel.add(startBtn);
		
		JButton resetBtn = new JButton("RESET");
		resetBtn.addActionListener(new ActionListener() {
			/**
			 * 数据清零，部分数据初始化
			 */
			public void actionPerformed(ActionEvent arg0) {
				gameLogic.clearWorld();
				showWorld();
				amountLabel.setText(word1+gameLogic.getNum()+word2);
				duration=200;
				times=100;
				durationField.setText(duration+"");
				timesField.setText(times+"");
				
			}
		});
		buttonPanel.add(resetBtn);
		
/*		JButton refreshBtn = new JButton("REFRESH");
		refreshBtn.addActionListener(new ActionListener() {
			*//**
			 * 刷新页面
			 *//*
			public void actionPerformed(ActionEvent arg0) {
				
				showWorld();
				//should i add the amountPanel???????????????????
			}
		});
		buttonPanel.add(refreshBtn);*/
		
		JButton helpBtn = new JButton("HELP");
		helpBtn.addActionListener(new ActionListener() {
			/**
			 * 提示信息
			 */
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null, "○代表无生命，◉代表有生命\n"
					+ "单击○可以安插一个生命，再次点击取消\n"
					+ "社会环境初始化完成后，点击“START”，社会环境开始演变至下一代", "提示：",
					JOptionPane.INFORMATION_MESSAGE);
			}
		});
		buttonPanel.add(helpBtn);
		
		JButton quitBtn = new JButton("QUIT");
		quitBtn.addActionListener(new ActionListener() {
			/**
			 * 退出页面，跳转至index页面
			 */
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.setVisible(false);
				IndexBox.indexFrame.setVisible(true);
			}
		});
		buttonPanel.add(quitBtn);
		
		
		continueBtn.addActionListener(new ActionListener() {
			/**
			 * 线程的暂停、继续
			 */
			public void actionPerformed(ActionEvent arg0) {
				if(!waitState) {
					gameCT.waitState();
					continueBtn.setText("continue");
				}else {
					gameCT.continueGame();
					continueBtn.setText("pause");
				}
			}
		});
		
		JButton tableBtn = new JButton("table");
		tableBtn.addActionListener(new ActionListener() {
			/**
			 * 调用数据库，绘制比对曲线
			 */
			public void actionPerformed(ActionEvent arg0) {
				ConnectDb cdb=new ConnectDb();
				cdb.export();
				
			}
		});
		buttonPanel.add(tableBtn);
		buttonPanel.add(continueBtn);
		continueBtn.setVisible(false);
		
		JPanel txtPanel = new JPanel();
		panel.add(txtPanel, BorderLayout.NORTH);
		txtPanel.setLayout(new BorderLayout(0, 0));
		
		amountLabel = new JLabel(word1+gameLogic.getNum()+word2);
		amountLabel.setBackground(Color.BLACK);
		amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		amountLabel.setLabelFor(this);
		amountLabel.setVerticalAlignment(SwingConstants.TOP);
		txtPanel.add(amountLabel, BorderLayout.SOUTH);
		
		JPanel fieldPanel = new JPanel();
		txtPanel.add(fieldPanel, BorderLayout.CENTER);
		fieldPanel.setLayout(new GridLayout(0, 5, 8, 5));
		
		JLabel durationLabel = new JLabel("\u52A8\u753B\u95F4\u9694(ms)");
		fieldPanel.add(durationLabel);
		
		durationField = new JTextField();
		durationField.setText("200");
		fieldPanel.add(durationField);
		durationField.setColumns(10);
		
		JLabel timesLabel = new JLabel("\u6F14\u5316\u6B21\u6570");
		fieldPanel.add(timesLabel);
		
		timesField = new JTextField();
		fieldPanel.add(timesField);
		timesField.setText("100");
		timesField.setColumns(10);
		
		JButton submitBtn2 = new JButton("submit");
		submitBtn2.addActionListener(new ActionListener() {
		/**
		 * 提交输入的数值
		 * 自定义动画间隔、演化代数
		 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					duration = Integer.parseInt(durationField.getText().trim());
					times = Integer.parseInt(timesField.getText().trim());
				}
				catch(NumberFormatException e1) {
					duration = DEFAULT_DURATION;
					times = DEFAULT_TIMES;
				}
			}
		});
		fieldPanel.add(submitBtn2);
		
		btns=new JButton[row][col];
		animationPanel = new JPanel();
		
		animationPanel.setLayout(new GridLayout(row,col,0,0));
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				btns[i][j]=new JButton();
				btns[i][j].setMargin(new Insets(0,0,0,0));
				btns[i][j].setBackground(Color.white);
				btns[i][j].addActionListener(this);
				btns[i][j].addMouseListener(new MyMouseEvent());
				animationPanel.add(btns[i][j]);
			}
		}
		panel.add(animationPanel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		MyMouseEvent myME = new MyMouseEvent();
		 if(e.getActionCommand()=="15*15") {
				//设置布局
				setSize(460,480);
				row=15;
				col=15;
				gameLogic.setRow(row);
				gameLogic.setCol(col);
				
				setWorld();
				gameLogic.preSet();
				gameLogic.clearWorld();
				
			}else if(e.getActionCommand()=="50*20") {
				//更大布局
				row=20;
				col=50;
				setSize(972,517);
				gameLogic.setRow(row);
				gameLogic.setCol(col);
				
				setWorld();
				gameLogic.preSet();
				gameLogic.clearWorld();
				
			}else if(e.getActionCommand()=="100*50") {
				//最大布局
				row=50;
				col=100;
				setSize(1722,1007);
				gameLogic.setRow(row);
				gameLogic.setCol(col);
				
				setWorld();
				gameLogic.preSet();
				gameLogic.clearWorld();
				
			}else if(e.getActionCommand()=="1") {
				
				int a[][]= {
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,3,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,3,3,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,3,3,3,0,0,0,0,0,0,20,0,0,0,0},
						{0,0,3,3,3,3,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
						{0,0,0,2,0,2,0,0,1,0,0,0,0,0,0},
						{0,0,0,3,0,0,0,0,0,0,0,0,0,0,0},
						{0,20,0,0,0,0,0,3,3,3,3,0,0,0,0},
						{0,0,0,0,3,0,0,3,3,0,3,3,0,0,0},
						{0,0,0,0,3,0,0,3,0,0,0,3,0,0,0},
						{0,0,0,0,3,3,3,3,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,20,0,0,0,0,0,0,20,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
				gameLogic.setA(a);
				showWorld();
			}else if(e.getActionCommand()=="p1") {
				personN=1;
				personC=0x4B0082;//靛青
				myME.mouseClick(e);
				
			}else if(e.getActionCommand()=="p2") {
				personN=2;
				personC=0xDC143C;//猩红
				myME.mouseClick(e);
				
			}else if(e.getActionCommand()=="p3") {
				personN=3;
				personC=0x000000;//black
				myME.mouseClick(e);
				
			}else if(e.getActionCommand()=="p4") {
				personN=4;
				personC=0x6495ED;//矢车菊的蓝色
				myME.mouseClick(e);
				
			}else if(e.getActionCommand()=="p5") {
				personN=5;
				personC=0xAFEEEE;//苍白的绿宝石
				myME.mouseClick(e);
				
			}else if(e.getActionCommand()=="p6") {
				personN=6;
				personC=0xFAFAD2;//浅秋麒麟黄
				myME.mouseClick(e);
			}else if(e.getActionCommand()=="e1") {
				personN=20;
				personC=0x9400D3;//深紫罗兰色
				myME.mouseClick(e);
			}else {
				myME.mouseClick(e);
			}
		
	}
	
	/**
	 * 刷新页面
	 */
	public void showWorld() {
		int[][] a=gameLogic.getA();
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				switch(a[i][j]) {
				case 0://无生命
					btns[i][j].setBackground(Color.WHITE);
					break;
				case 1://智者，紫罗兰的淡紫色代表
					btns[i][j].setBackground(new Color(0x4B0082));
					break;
				case 2://现充，红色代表
					btns[i][j].setBackground(new Color(0xDC143C));
					break;
				case 3://p3，黑色代表
					btns[i][j].setBackground(new Color(0x000000));
					break;
				case 4://孤独者，矢车菊的蓝色代表
					btns[i][j].setBackground(new Color(0x6495ED));
					break;
				case 5://伪孤独，苍白的宝石绿色代表
					btns[i][j].setBackground(new Color(0xAFEEEE));
					break;
				case 6://心影幻想品 ，亮灰色代表
					btns[i][j].setBackground(new Color(0xFAFAD2));
					break;
				case 11://深紫罗兰色代表环境资源
					btns[i][j].setBackground(new Color(0x9400D3));
					break;
				case 12:
					btns[i][j].setBackground(new Color(0x9400D3));
					break;
				case 13:
					btns[i][j].setBackground(new Color(0x9400D3));
					break;
				case 14:
					btns[i][j].setBackground(new Color(0x9400D3));
					break;
				case 15:
					btns[i][j].setBackground(new Color(0x9400D3));
					break;
				case 16:
					btns[i][j].setBackground(new Color(0x9400D3));
					break;
				case 17:
					btns[i][j].setBackground(new Color(0x9400D3));
					break;
				case 18:
					btns[i][j].setBackground(new Color(0x9400D3));
					break;
				case 19:
					btns[i][j].setBackground(new Color(0x9400D3));
					break;
				case 20:
					btns[i][j].setBackground(new Color(0x9400D3));
					break;
					
				default:
					btns[i][j].setBackground(Color.WHITE);
				}
			}
	    }
		amountLabel.setText(word1+gameLogic.getNum()+word2);
	}
	/**
	 * 初始化页面版式
	 */
	public void setWorld() {
		btns=new JButton[row][col];
		panel.remove(animationPanel);
		animationPanel=new JPanel();
		animationPanel.setLayout(new GridLayout(row,col,0,0));
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				btns[i][j]=new JButton();
				btns[i][j].setMargin(new Insets(0,0,0,0));
				btns[i][j].setBackground(Color.white);
				btns[i][j].addActionListener(this);
				btns[i][j].addMouseListener(new MyMouseEvent());
				animationPanel.add(btns[i][j]);
			}
		}
		panel.add(animationPanel,BorderLayout.CENTER);
	}
	/**
	 * 控制线程
	 */
	public class GameControlTask implements Runnable{
		private Thread a;
		@Override
		public void run() {
			
			gameLogic.setTimes(times);
			gameLogic.preCount();
			
			while(startState) {
				for(int i=0;i<times && startState;i++) {
					
					gameLogic.getNeighbors();
					gameLogic.transform();
					gameLogic.getNumber();
					showWorld();
					gameLogic.getEn();
					
					try {
						Thread.sleep(duration);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized(this) {
						while(waitState) {
							try {
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				//更新数据库
				gameLogic.setdb();
				//提示信息
				JOptionPane.showMessageDialog(null, "You have finished a game!","massage", JOptionPane.INFORMATION_MESSAGE);
				startState=false;
				//初始参数
				startBtn.setText("START");
				continueBtn.setVisible(false);
			}
		}
		//开始一个线程
		public void start() {
			
				a=new Thread(this);
				a.start();
				startState=true;
			
		}
		//改变条件，终结一个线程
		public void end() {
			startState=false;
		}
		//暂停一个线程
		public void waitState() {
			waitState=true;
		}
		//唤醒一个线程
		synchronized void continueGame() {
			waitState=false;
			notify();
		}
		
	}
	/**
	 * 鼠标点击更新素材排布
	 * @return
	 */
	class MyMouseEvent extends MouseAdapter{
		public void mouseClick(ActionEvent e) {
			int[][] a=gameLogic.getA();
			int num=gameLogic.getNum();
			for(int i=0;i<row;i++){
				for(int j=0;j<col;j++){
					if(e.getSource()==btns[i][j]){
						if((btns[i][j].getBackground()).equals(Color.WHITE)){
							a[i][j]=personN;
							btns[i][j].setBackground(new Color(personC));
							num++;
						}else {
							btns[i][j].setBackground(Color.WHITE);
							a[i][j]=0;
							num--;
						}
	 
					}
				}
				amountLabel.setText(word1+num+word2);
			}
			gameLogic.setA(a);
			gameLogic.setNum(num);
		}
	}
	
	public int getRow() {
		return this.row;
	}
	public void setRow(int row) {
		this.row=row;
	}
	public int getCol() {
		return this.col;
	}
	public void setCol(int col) {
		this.col=col;
	}
	public int getTimes() {
		return this.times;
	}
	public void setTimes(int times) {
		this.times=times;
	}
	public int getDuration() {
		return this.duration;
	}
	public void setDuration(int duration) {
		this.duration=duration;
	}

}
