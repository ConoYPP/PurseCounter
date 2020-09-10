package com.cono.pursecounter;

import javax.swing.JFrame;

public class PurseCounter {
    public static void main(String[] args) {
    JFrame mainGUI;
        mainGUI = new PurseCounterGUI();
    mainGUI.setTitle("Greedy Purse Value Counter");
    mainGUI.setVisible(true);
  }
}
