package per.maingame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JTextField;

public class IndexBox extends JFrame {

	/**
	 * p
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtHi;
//	private Image image=(Image)new ImageIcon("crow.png").getImage();
	static IndexBox indexFrame = new IndexBox();
	JPanel mainPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					indexFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IndexBox() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel btnPanel = new JPanel();
		mainPanel.add(btnPanel, BorderLayout.SOUTH);
		
		JButton startBtn = new JButton("start");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtHi.setText("H E L L O !");
				indexFrame.setVisible(false);
				GameBox.mainFrame.setVisible(true);
			}
		});
		btnPanel.add(startBtn);
		
		JButton quitBtn = new JButton("quit");
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtHi.setText("S E E  Y O U !");
				System.exit(0);
			}
		});
		btnPanel.add(quitBtn);
		
		JPanel txtPanel = new JPanel();
		mainPanel.add(txtPanel, BorderLayout.CENTER);
		
		txtHi = new JTextField();
		txtHi.setText("Hi\uFF01\uFF01");
		txtPanel.add(txtHi);
		txtHi.setColumns(10);
	}


}