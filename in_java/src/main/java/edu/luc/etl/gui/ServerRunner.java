package edu.luc.etl.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ServerRunner {
	public static void main(String[] args) {
		JFrame frame = new JFrame("HelloWorldSwing");
		final JLabel label = new JLabel("Hello World");
		frame.getContentPane().add(label);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
