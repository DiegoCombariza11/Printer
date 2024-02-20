package co.edu.uptc.printer.presentation;

import javax.swing.*;
import java.awt.*;

public class MessageWarning extends JFrame {
    private JPanel mainPanel;
    private JLabel msg;
    private JButton confirm;
    private JButton cancel;
    public MessageWarning(){

    }
    public void created(String text){
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
    public JPanel settingsButtons(){
        JPanel buttons=new JPanel();
        confirm=new JButton("Confirmar");
        confirm.setPreferredSize(new Dimension(100,25));
        cancel=new JButton("Cancelar");
        cancel.setPreferredSize(new Dimension(100,25));
        buttons.add(confirm);
        buttons.add(cancel);
        return buttons;
    }
}
