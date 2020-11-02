package com.imagewinnner.canvas;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.AbstractMap.SimpleEntry;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ImageWinnerDrawer extends Canvas {

	private static final int MESSAGE_SIZE= 100;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stack<SimpleEntry<String,BufferedImage>> imagesStack;
	private JFrame battleFrame;
	private JButton leftButton;
	private JButton rightButton;
	private JLabel leftImageLabel;
	private JLabel rightImageLabel;
	private GridBagConstraints gridConstraints;
	private SimpleEntry<String,BufferedImage> rightImageEntry;
	private SimpleEntry<String,BufferedImage> leftImageEntry;
	private SimpleEntry<String,BufferedImage> lastWinner;

	public static void main(String[] args) {
		new ImageWinnerDrawer();
	}

	public ImageWinnerDrawer() {
		
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
					initializeGUI();
					drawGUI();

				} catch (Exception exp) {
					exp.printStackTrace();
				}

			}
		});
	}

	private void initializeGUI() {
		battleFrame = new JFrame();
		leftButton = new JButton("Left");
		rightButton = new JButton("Right");
		battleFrame.setLayout(new GridBagLayout());
		battleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		battleFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		battleFrame.setVisible(true);
		gridConstraints = new GridBagConstraints();
		gridConstraints.fill = GridBagConstraints.HORIZONTAL;
		leftImageEntry = imagesStack.pop();
		rightImageEntry = imagesStack.pop();
	}

	private void drawGUI() {

		drawLeftButton(battleFrame.getContentPane());
		drawRightButton(battleFrame.getContentPane());

		drawLeftImage(leftImageEntry.getValue(), battleFrame.getContentPane());
		drawRightImage(rightImageEntry.getValue(), battleFrame.getContentPane());

		defineButtonEventBehaviour(battleFrame.getContentPane());

	}

	private void defineButtonEventBehaviour(Container pane) {
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!imagesStack.empty()) {
					pane.remove(rightImageLabel);
					rightImageEntry = imagesStack.pop();
					drawRightImage(rightImageEntry.getValue(), pane);
				} else {
					lastWinner = leftImageEntry;
					showWinnerText();
				}
			}
		});

		// Process the Apply gaps button press
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!imagesStack.empty()) {
					pane.remove(leftImageLabel);
					leftImageEntry = imagesStack.pop();
					drawLeftImage(leftImageEntry.getValue(), pane);
				} else {
					lastWinner = rightImageEntry;
					showWinnerText();
				}
			}
		});
	}
	
	private void showWinnerText() {
		
		battleFrame.dispose();
		JFrame winnerFrame = initializeWinnerFrame();

		drawWinnerText(winnerFrame);
		drawWinnerImage(winnerFrame);
		
	}

	private JFrame initializeWinnerFrame() {
		JFrame winnerFrame = new JFrame();
		winnerFrame.setLayout(new GridBagLayout());
		gridConstraints.fill = GridBagConstraints.HORIZONTAL;
		winnerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winnerFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		winnerFrame.setVisible(true);
		return winnerFrame;
	}

	private void drawWinnerImage(JFrame winnerFrame) {
		JLabel messageLabel = new JLabel( new ImageIcon(lastWinner.getValue()));
		gridConstraints.ipady = 40; // make this component tall
		gridConstraints.gridwidth = 1;
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		winnerFrame.getContentPane().add(messageLabel,gridConstraints);
	}

	private void drawWinnerText(JFrame winnerFrame) {
		JTextField tf = new JTextField(MESSAGE_SIZE);
		tf.setText("Winner image! - " + lastWinner.getKey());
		tf.setEditable(false);
		
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		winnerFrame.getContentPane().add(tf,gridConstraints);
	}

	private void drawRightButton(Container pane) {
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		pane.add(rightButton, gridConstraints);
	}

	private void drawLeftButton(Container pane) {
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		pane.add(leftButton, gridConstraints);
	}

	private void drawRightImage(BufferedImage rightImage, Container pane) {
		rightImageLabel = new JLabel(new ImageIcon(rightImage));
		gridConstraints.ipady = 40; // reset to default
		gridConstraints.gridx = 1; // aligned with button 2
		gridConstraints.gridwidth = 1; // 2 columns wide
		gridConstraints.gridy = 1; // third row
		pane.add(rightImageLabel, gridConstraints);
		SwingUtilities.updateComponentTreeUI(battleFrame);
	}

	private void drawLeftImage(BufferedImage leftImage, Container pane) {
		leftImageLabel = new JLabel(new ImageIcon(leftImage));
		gridConstraints.ipady = 40; // make this component tall
		gridConstraints.gridwidth = 1;
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		pane.add(leftImageLabel, gridConstraints);
		SwingUtilities.updateComponentTreeUI(battleFrame);
	}

}