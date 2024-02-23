package co.edu.uptc.printer.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Print extends JFrame implements ActionListener {
    private JPanel main;
    private JLabel msg;
    private JProgressBar printing;

    public Print(String text){
        this.setSize(300,150);
        this.setLocation(550,100);
        this.setUndecorated(true);
        main=new JPanel(new GridLayout(2,1));
        main.setLocation(550,100);
        msg=new JLabel(text);
        msg.setLocation(150,20);
        msg.setForeground(Color.BLUE);
        JPanel textPanel=new JPanel();
        textPanel.add(msg, BorderLayout.CENTER);
        textPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        main.add(textPanel,BorderLayout.CENTER);
        main.add(settingsBar(),BorderLayout.CENTER);
        this.add(main);
        Timer timer = new Timer(30, this);
        timer.start();
        this.setVisible(true);
    }
    public JPanel settingsBar(){
        printing = new JProgressBar();
        printing.setStringPainted(true);
        printing.setMinimum(0);
        printing.setMaximum(100);
        JPanel panel=new JPanel();
        panel.add(printing,BorderLayout.CENTER);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int value = printing.getValue();
        if (value < 100) {
            printing.setValue(value + 1);
        }
    }
}
