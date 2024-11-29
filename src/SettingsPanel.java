import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class SettingsPanel extends JPanel implements ActionListener{
	final int singlefieldSize = 20;
	ButtonGroup difficulty;
	JRadioButton easy;
	JRadioButton normal;
	JRadioButton hard;
	JRadioButton custom;
	JLabel difficultyLabel;
	JLabel widthLabel;
	JLabel heightLabel;
	JLabel minesLabel;
	SpinnerModel model;
	JSpinner width;
	JSpinner height;
	JSpinner mines;
	JButton back;
	JButton select;
	JPanel difficultySettings;
	MineSweeperFrame frame;
	public SettingsPanel(int fieldWidth, int fieldHeight, int minesNum, MineSweeperFrame frame) {
		
		this.frame = frame;
		
		model = new SpinnerNumberModel(fieldWidth,9,32,1);
		width = new JSpinner(model);
		model = new SpinnerNumberModel(fieldHeight,9,32,1);
		height = new JSpinner(model);
		model = new SpinnerNumberModel(minesNum,10,300,1);
		mines = new JSpinner(model);
		enableDisableCustomSettings(false);
		
		easy = new JRadioButton("easy ");
		easy.setBackground(Color.LIGHT_GRAY);
		easy.setSize(new Dimension(20,20));
		easy.addActionListener(this);
		normal = new JRadioButton("normal ");
		normal.setBackground(Color.LIGHT_GRAY);
		normal.addActionListener(this);
		hard = new JRadioButton("hard ");
		hard.addActionListener(this);
		hard.setBackground(Color.LIGHT_GRAY);
		custom = new JRadioButton("custom: ");
		custom.addActionListener(this);
		custom.setBackground(Color.LIGHT_GRAY);
		
		difficulty = new ButtonGroup();
		difficulty.add(easy);
		difficulty.add(normal);
		difficulty.add(hard);
		difficulty.add(custom);
		difficultyLabel = new JLabel("        difficulty selection:");
		widthLabel = new JLabel("width: ");
		heightLabel = new JLabel("height: ");
		minesLabel = new JLabel("mines: ");
		
		back = new JButton("Back");
		back.addActionListener(this);
		select = new JButton("Select");
		select.addActionListener(this);
		
		difficultySettings = new JPanel();
		difficultySettings.setLayout(new FlowLayout());
		difficultySettings.setPreferredSize(new Dimension(180,180));
		difficultySettings.setBackground(Color.LIGHT_GRAY);
		difficultySettings.add(difficultyLabel);
		difficultySettings.add(easy);
		difficultySettings.add(normal);
		difficultySettings.add(hard);
		difficultySettings.add(custom);
		difficultySettings.add(widthLabel);
		difficultySettings.add(heightLabel);
		difficultySettings.add(minesLabel);
		difficultySettings.add(height);
		difficultySettings.add(width);
		difficultySettings.add(mines);
		difficultySettings.add(back);
		difficultySettings.add(select);
		difficultySettings.setBorder(BorderFactory.createLineBorder(Color.black,1));
		
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(fieldHeight*singlefieldSize, fieldHeight*singlefieldSize));
		this.add(difficultySettings);
		this.setOpaque(true);
		this.setBackground(Color.LIGHT_GRAY);
	}
	
	public void enableDisableCustomSettings(boolean enableOrDisable) {
		width.setEnabled(enableOrDisable);
		height.setEnabled(enableOrDisable);
		mines.setEnabled(enableOrDisable);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			frame.returnToGame();
		}
		if (e.getSource() == custom) {
			enableDisableCustomSettings(true);
		}
		if (e.getSource() == easy || e.getSource() == normal || e.getSource() == hard) {
			enableDisableCustomSettings(false);
		}
		if (e.getSource() == select) {
			if (easy.isSelected()) {
				frame.setNewGame(9,9,10);
			}
			if (normal.isSelected()) {
				frame.setNewGame(16,16,40);
			}
			if (hard.isSelected()) {
				frame.setNewGame(32,16,99);
			}
			if (custom.isSelected()) {
				int selectedAmouuntOfmines = (int)mines.getValue();
				int selectedWidth = (int)width.getValue();
				int selectedHeight = (int)height.getValue();
				if (selectedAmouuntOfmines >= selectedWidth*selectedHeight)
					selectedAmouuntOfmines = selectedWidth*selectedHeight-10;
				frame.setNewGame(selectedWidth,selectedHeight,selectedAmouuntOfmines);
			}
		}
	}
}
