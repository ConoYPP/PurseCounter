package com.cono.pursecounter;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PurseCounterGUI extends JFrame {

	private static final long serialVersionUID = -5169772243881694537L;

	private String _path;
	private boolean _validPath = false;
	private Timer _timer;
	private BufferedInputStream _reader;
	String _currLine;
	private JButton SelectButton;
	int VarCurrentPurseValue;
	int VarJackpotPursesTotal;
	int VarJackpotPursesCount;
	int VarVeryHighPursesTotal;
	int VarVeryHighPursesCount;
	int VarHighPursesTotal;
	int VarHighPursesCount;
	int VarMidPursesTotal;
	int VarMidPursesCount;
	int VarLowPursesTotal;
	int VarLowPursesCount;
	int VarPurseTotal;
	int VarPurseCount;
	int VarPurseAverage;
	int VarTrinketAverage;
	int numberOnly;

	public PurseCounterGUI() {
		initComponents();
	}

	private void StartRun() throws FileNotFoundException {
		_timer = new Timer();
		_reader = new BufferedInputStream(new FileInputStream(this._path));
		_currLine = "";
		_timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				boolean running = true;
				int i = 0;
				try {
					while (running && i < 50000) {
						if (PurseCounterGUI.this._reader.available() > 0) {
							char read = (char) PurseCounterGUI.this._reader.read();
							if (read == '\n') {
                                                            if (PurseCounterGUI.this._currLine.contains("Ye slash")) {
                                                                if (PurseCounterGUI.this._currLine.contains("says,") || PurseCounterGUI.this._currLine.contains("chats") || PurseCounterGUI.this._currLine.contains("tells ye") || PurseCounterGUI.this._currLine.contains("Ye told")|| PurseCounterGUI.this._currLine.contains("thinks,"))
                                                                    return;
                                                                else {
                                                                    PurseCounterGUI.this._currLine = PurseCounterGUI.this._currLine.replaceAll("[^0-9]", "");
                                                                    _currLine = _currLine.substring(6); 
                                                                    System.out.println(PurseCounterGUI.this._currLine);
                                                                    PurseCounterGUI.this.HandlePurse(PurseCounterGUI.this._currLine);
                                                                }
                                                            }
                                                            PurseCounterGUI.this._currLine = "";
							} else {
                                                            PurseCounterGUI.this._currLine += read;
							}
							i++;
							continue;
						}
						running = false;
						System.out.println("Done");
					}
				} catch (IOException ex) {
					Logger.getLogger(PurseCounterGUI.class.getName()).log(Level.SEVERE, (String) null, ex);
				}
			}
		}, 1000, 1000);
	}

	private void UpdateTextbox() {
		VarPurseAverage = (VarPurseTotal / VarPurseCount);
		VarTrinketAverage = ((VarPurseTotal / VarPurseCount) / 3);
		this.JackpotPursesTotal2.setText(Integer.toString(VarJackpotPursesTotal)+ " PoE");
		this.JackpotPursesCount.setText(Integer.toString(VarJackpotPursesCount)+ " Purses");
		this.VeryHighPursesTotal2.setText(Integer.toString(VarVeryHighPursesTotal)+ " PoE");
		this.VeryHighPursesCount.setText(Integer.toString(VarVeryHighPursesCount)+ " Purses");
		this.HighPursesTotal2.setText(Integer.toString(VarHighPursesTotal)+ " PoE");
		this.HighPursesCount.setText(Integer.toString(VarHighPursesCount)+ " Purses");
		this.MidPursesTotal2.setText(Integer.toString(VarMidPursesTotal)+ " PoE");
		this.MidPursesCount.setText(Integer.toString(VarMidPursesCount)+ " Purses");
		this.LowPursesTotal2.setText(Integer.toString(VarLowPursesTotal)+ " PoE");
		this.LowPursesCount.setText(Integer.toString(VarLowPursesCount)+ " Purses");
		this.PurseCount.setText(Integer.toString(VarPurseCount)+ " Purses");
		this.PurseTotal2.setText(Integer.toString(VarPurseTotal)+ " PoE");
		this.TrinketAverage2.setText(Integer.toString(VarTrinketAverage)+ " PoE");
		this.PurseAverage2.setText(Integer.toString(VarPurseAverage)+ " PoE");
	}

	private void HandlePurse(String line) {
                VarCurrentPurseValue = Integer.parseInt(_currLine);  
				if (VarCurrentPurseValue < 10000) 
				{
					VarLowPursesTotal = VarLowPursesTotal + VarCurrentPurseValue;
					VarLowPursesCount = VarLowPursesCount + 1;
					VarPurseCount = VarPurseCount + 1;
					VarPurseTotal = VarPurseTotal + VarCurrentPurseValue;
					VarCurrentPurseValue = 0;
				}
				else if (VarCurrentPurseValue < 20000) 
				{
					VarMidPursesTotal = VarMidPursesTotal + VarCurrentPurseValue;
					VarMidPursesCount = VarMidPursesCount + 1;
					VarPurseCount = VarPurseCount + 1;
					VarPurseTotal = VarPurseTotal + VarCurrentPurseValue;
					VarCurrentPurseValue = 0;
				}
				else if (VarCurrentPurseValue < 40000) 
				{
					VarHighPursesTotal = VarHighPursesTotal + VarCurrentPurseValue;
					VarHighPursesCount = VarHighPursesCount + 1;
					VarPurseCount = VarPurseCount + 1;
					VarPurseTotal = VarPurseTotal + VarCurrentPurseValue;
					VarCurrentPurseValue = 0;
				}
				else if (VarCurrentPurseValue < 50000) 
				{
					VarVeryHighPursesTotal = VarVeryHighPursesTotal + VarCurrentPurseValue;
					VarVeryHighPursesCount = VarVeryHighPursesCount + 1;
					VarPurseCount = VarPurseCount + 1;
					VarPurseTotal = VarPurseTotal + VarCurrentPurseValue;
					VarCurrentPurseValue = 0;
				}
                                else if (VarCurrentPurseValue > 99999) 
				{
					VarJackpotPursesTotal = VarJackpotPursesTotal + VarCurrentPurseValue;
					VarJackpotPursesCount = VarJackpotPursesCount + 1;
					VarPurseCount = VarPurseCount + 1;
					VarPurseTotal = VarPurseTotal + VarCurrentPurseValue;
					VarCurrentPurseValue = 0;
				}
			UpdateTextbox();
	}

	public void initComponents() {

		JackpotPursesTotal = new javax.swing.JTextField();
		VeryHighPursesTotal = new javax.swing.JTextField();
		HighPursesTotal = new javax.swing.JTextField();
		MidPursesTotal = new javax.swing.JTextField();
		LowPursesTotal = new javax.swing.JTextField();
		PurseTotal = new javax.swing.JTextField();
		PurseAverage = new javax.swing.JTextField();
		TrinketAverage = new javax.swing.JTextField();
		JackpotPursesCount = new javax.swing.JTextField();
		VeryHighPursesCount = new javax.swing.JTextField();
		HighPursesCount = new javax.swing.JTextField();
		MidPursesCount = new javax.swing.JTextField();
		LowPursesCount = new javax.swing.JTextField();
		PurseCount = new javax.swing.JTextField();
		JackpotPursesTotal2 = new javax.swing.JTextField();
		VeryHighPursesTotal2 = new javax.swing.JTextField();
		HighPursesTotal2 = new javax.swing.JTextField();
		MidPursesTotal2 = new javax.swing.JTextField();
		LowPursesTotal2 = new javax.swing.JTextField();
		PurseTotal2 = new javax.swing.JTextField();
		PurseAverage2 = new javax.swing.JTextField();
		TrinketAverage2 = new javax.swing.JTextField();
		SelectButton = new javax.swing.JButton();
		JackpotPursesTotal.setText("Purses containing 100k:");
		VeryHighPursesTotal.setText("Purses containing 40-50k:");
		HighPursesTotal.setText("Purses Containing 20-40k");
		MidPursesTotal.setText("Purses containing 10-20k:");
		LowPursesTotal.setText("Purses containing 6-10k:");
		PurseTotal.setText("Total purses:");
		PurseAverage.setText("Average per purse:");
		TrinketAverage.setText("Average per trinket:");
		SelectButton.setText("Select file..");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(SelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(JackpotPursesTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 166,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(VeryHighPursesTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 166,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(HighPursesTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 166,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(MidPursesTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 166,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(LowPursesTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 166,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(PurseTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 166,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(PurseAverage, javax.swing.GroupLayout.PREFERRED_SIZE, 166,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(TrinketAverage, javax.swing.GroupLayout.PREFERRED_SIZE, 166,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(HighPursesCount).addComponent(VeryHighPursesCount)
								.addComponent(JackpotPursesCount).addComponent(PurseCount).addComponent(LowPursesCount)
								.addComponent(MidPursesCount))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addComponent(PurseAverage2, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
								.addComponent(PurseTotal2, javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(LowPursesTotal2, javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(MidPursesTotal2, javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(HighPursesTotal2, javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(VeryHighPursesTotal2, javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(JackpotPursesTotal2, javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(TrinketAverage2, javax.swing.GroupLayout.Alignment.LEADING))
						.addContainerGap()));

		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(87, Short.MAX_VALUE).addComponent(SelectButton)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(layout
								.createSequentialGroup()
								.addComponent(JackpotPursesTotal2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(VeryHighPursesTotal2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(HighPursesTotal2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(MidPursesTotal2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(LowPursesTotal2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(PurseTotal2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(PurseAverage2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(TrinketAverage2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(JackpotPursesCount,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(VeryHighPursesCount,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(HighPursesCount, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(MidPursesCount, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(LowPursesCount, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(PurseCount, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addComponent(JackpotPursesTotal,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(VeryHighPursesTotal,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(HighPursesTotal, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(MidPursesTotal, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(LowPursesTotal, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(PurseTotal, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(PurseAverage, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(TrinketAverage, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap()));
		setPreferredSize(new Dimension(650, 300));
                setResizable(false);

		this.SelectButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {

				PurseCounterGUI.this.SelectButtonActionPerformed(evt);
			}
		});
		pack();
	}

	private void SelectButtonActionPerformed(ActionEvent evt) {

		if (this._timer != null)
			this._timer.cancel();
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == 0) {
			this._path = chooser.getCurrentDirectory() + "\\" + chooser.getSelectedFile().getName();
			this._validPath = true;
			System.out.println("You chose to open this file: " + this._path);
			try {
				StartRun();
			} catch (FileNotFoundException ex) {
				Logger.getLogger(PurseCounterGUI.class.getName()).log(Level.SEVERE, (String) null, ex);
			}
			this.SelectButton.setEnabled(false);
		}
	}

	private javax.swing.JTextField HighPursesCount;
	private javax.swing.JTextField HighPursesTotal;
	private javax.swing.JTextField HighPursesTotal2;
	private javax.swing.JTextField JackpotPursesCount;
	private javax.swing.JTextField JackpotPursesTotal;
	private javax.swing.JTextField JackpotPursesTotal2;
	private javax.swing.JTextField LowPursesCount;
	private javax.swing.JTextField LowPursesTotal;
	private javax.swing.JTextField LowPursesTotal2;
	private javax.swing.JTextField MidPursesCount;
	private javax.swing.JTextField MidPursesTotal;
	private javax.swing.JTextField MidPursesTotal2;
	private javax.swing.JTextField PurseAverage;
	private javax.swing.JTextField PurseAverage2;
	private javax.swing.JTextField PurseCount;
	private javax.swing.JTextField PurseTotal;
	private javax.swing.JTextField PurseTotal2;
	private javax.swing.JTextField TrinketAverage;
	private javax.swing.JTextField TrinketAverage2;
	private javax.swing.JTextField VeryHighPursesCount;
	private javax.swing.JTextField VeryHighPursesTotal;
	private javax.swing.JTextField VeryHighPursesTotal2;

	public static void main(String[] args) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(PurseCounterGUI.class.getName()).log(Level.SEVERE, (String) null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(PurseCounterGUI.class.getName()).log(Level.SEVERE, (String) null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(PurseCounterGUI.class.getName()).log(Level.SEVERE, (String) null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(PurseCounterGUI.class.getName()).log(Level.SEVERE, (String) null, ex);
		}
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				(new PurseCounterGUI()).setVisible(true);
			}
		});
	}
}
