import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui3C {
	JFrame frame;
	JLabel label;
	JPanel drawPanel;
	int x = 70;
	int y = 70;
	public static void main (String[] args) {
		SimpleGui3C gui = new SimpleGui3C();
		gui.go();
	}
	class MyDrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0,  0,  this.getWidth(), this.getHeight());
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			
			Color randomColor = new Color(red, green, blue);
			g.setColor(randomColor);
			g.fillOval(x, y, 100, 100);
		}
	}
	class ColorListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frame.repaint();
		}
	}
	class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			label.setText("Ouch!");
		}
	}
	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button = new JButton("Change colors");
		button.addActionListener(new ColorListener());
		
		JButton labelButton = new JButton("Change Label");
		labelButton.addActionListener(new LabelListener());
		
		drawPanel = new MyDrawPanel();
		label  = new JLabel("I'm a label");
		
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.getContentPane().add(BorderLayout.WEST,label);
		frame.getContentPane().add(BorderLayout.EAST,labelButton);
		frame.setSize(600, 600);
		frame.setVisible(true);
		
		for (int i = 0; i < 100; ++i) {
			++x;
			++y;
			drawPanel.repaint();
			try {
				Thread.sleep(50);
			}catch(Exception ex) {}
		}
	}
}


