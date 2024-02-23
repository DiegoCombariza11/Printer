package co.edu.uptc.printer.presentation;

import co.edu.uptc.printer.logic.PrintSpooler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageWarning extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JLabel msg;
    private JButton confirm;
    private JButton cancel;
    private PrintSpooler printSpooler;
    private String aux;
    public MessageWarning(PrintSpooler ps){
        this.printSpooler=ps;
    }

    public MessageWarning(){

    }
    public void createdAlertReload(String text){
        aux=text;
        this.setSize(300,150);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        mainPanel=new JPanel(new GridLayout(2,1));
        msg=new JLabel(text);
        msg.setLocation(150,20);
        msg.setForeground(Color.RED);
        JPanel textPanel=new JPanel();
        textPanel.add(msg, BorderLayout.CENTER);
        textPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(settingsButtons());
        this.add(mainPanel);
        this.setVisible(true);
    }
    public void cratedMessage(String text){
        this.setSize(300,150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        mainPanel=new JPanel(new GridLayout(2,1));
        msg=new JLabel(text);
        msg.setLocation(150,20);
        msg.setForeground(Color.RED);
        JPanel textPanel=new JPanel();
        textPanel.add(msg, BorderLayout.CENTER);
        textPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        mainPanel.add(textPanel, BorderLayout.CENTER);
        this.add(mainPanel);
        this.setVisible(true);
    }
    public JPanel settingsButtons(){
        JPanel buttons=new JPanel();
        confirm=new JButton("Confirmar");
        confirm.setPreferredSize(new Dimension(100,25));
        cancel=new JButton("Cancelar");
        cancel.setPreferredSize(new Dimension(100,25));
        buttons.add(confirm);
        buttons.add(cancel);
        confirm.addActionListener(this);
        cancel.addActionListener(this);
        return buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirm){
            if(aux.contains("hojas")){
                printSpooler.getPrinterController().addSheets();
            }
            if (aux.contains("tinta")){
                printSpooler.getPrinterController().reloadInk();
            }
        }else if (e.getSource() == cancel){
            this.dispose();
        }
    }
}
