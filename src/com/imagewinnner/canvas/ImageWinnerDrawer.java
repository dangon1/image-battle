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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ImageWinnerDrawer extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stack<BufferedImage> imagesStack;
	private JFrame masterFrame;
	private JButton leftButton;
	private JButton rightButton;
	private JLabel leftImageLabel;
	private JLabel rightImageLabel;
	private GridBagConstraints gridConstraints;

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
					drawGUI(imagesStack.pop(), imagesStack.pop());

				} catch (Exception exp) {
					exp.printStackTrace();
				}

			}
		});
	}

	private void initializeGUI() {
		masterFrame = new JFrame();
		leftButton = new JButton("Left");
		rightButton = new JButton("Right");
		masterFrame.setLayout(new GridBagLayout());
		gridConstraints = new GridBagConstraints();
	}

	private void drawGUI(BufferedImage leftImage, BufferedImage rightImage) {
		
		Container pane = masterFrame.getContentPane();
		
		gridConstraints.fill = GridBagConstraints.HORIZONTAL;

		drawLeftButton(pane);
		drawRightButton(pane);

		drawLeftImage(leftImage, pane);
		drawRightImage(rightImage, pane);

		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!imagesStack.empty()) {
					pane.remove(rightImageLabel);
					drawRightImage(imagesStack.pop(), pane);
				}
			}
		});

		// Process the Apply gaps button press
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!imagesStack.empty()) {
					pane.remove(leftImageLabel);
					drawLeftImage(imagesStack.pop(), pane);
				}
			}
		});

		masterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		masterFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		masterFrame.setVisible(true);
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
		SwingUtilities.updateComponentTreeUI(masterFrame);
	}

	private void drawLeftImage(BufferedImage leftImage, Container pane) {
		leftImageLabel = new JLabel(new ImageIcon(leftImage));
		gridConstraints.ipady = 40; // make this component tall
		gridConstraints.gridwidth = 1;
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		pane.add(leftImageLabel, gridConstraints);
		SwingUtilities.updateComponentTreeUI(masterFrame);
	}

}