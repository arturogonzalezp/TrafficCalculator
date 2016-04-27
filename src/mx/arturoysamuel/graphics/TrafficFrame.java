package mx.arturoysamuel.graphics;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class TrafficFrame extends JFrame {
	
	TrafficPanel pnlTraffic = new TrafficPanel(7);
	InfoPanel pnlInfo = new InfoPanel(12);
	InputPanel pnlInput = new InputPanel(12);
	
	public TrafficFrame() {
		super("Traffic Calculator");
		this.add(this.pnlTraffic, BorderLayout.WEST);
		this.add(this.pnlInfo, BorderLayout.CENTER);
		this.add(this.pnlInput, BorderLayout.EAST);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
