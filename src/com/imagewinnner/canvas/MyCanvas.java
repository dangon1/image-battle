package com.imagewinnner.canvas;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MyCanvas extends Canvas {

	private Stack<BufferedImage> imagesStack;
	private BufferedImage imageWinner;

	public static void main(String[] args) {
		new MyCanvas();
	}

	public MyCanvas() {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}

				try {
					imagesStack = ImagesGetter.getImages();

					drawGUI(imagesStack.pop(), imagesStack.pop());

				} catch (Exception exp) {
					exp.printStackTrace();
				}

			}
		});
	}

	private void drawGUI(BufferedImage leftImage, BufferedImage rightImage) {
		JFrame masterFrame = new JFrame();
		JButton leftButton = new JButton("Left");
		JButton rightButton = new JButton("Right");
		JLabel leftPositionImage;
		JLabel rightPositionImage;
		
		Container pane = masterFrame.getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gridConstraints = new GridBagConstraints();
		// natural height, maximum width
		gridConstraints.fill = GridBagConstraints.HORIZONTAL;

		leftButton = new JButton("Left");
		gridConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		pane.add(leftButton, gridConstraints);

		rightButton = new JButton("Right");
		gridConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		pane.add(rightButton, gridConstraints);

		leftPositionImage = new JLabel(new ImageIcon(leftImage));
		rightPositionImage = new JLabel(new ImageIcon(rightImage));

		gridConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridConstraints.ipady = 40; // make this component tall
		gridConstraints.gridwidth = 1;
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		pane.add(leftPositionImage, gridConstraints);

		gridConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridConstraints.ipady = 40; // reset to default
		gridConstraints.gridx = 1; // aligned with button 2
		gridConstraints.gridwidth = 1; // 2 columns wide
		gridConstraints.gridy = 1; // third row
		pane.add(rightPositionImage, gridConstraints);

		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageWinner = leftImage;
				if(!imagesStack.empty()) {
					masterFrame.dispose();
					drawGUI(imageWinner, imagesStack.pop());
				}
			}
		});

		// Process the Apply gaps button press
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageWinner = rightImage;
				if(!imagesStack.empty()) {
					masterFrame.dispose();
					drawGUI(imagesStack.pop(), imageWinner);
				}
			}
		});

		masterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		masterFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		masterFrame.setVisible(true);

	}

}