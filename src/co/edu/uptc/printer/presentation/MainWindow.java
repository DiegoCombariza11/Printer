package co.edu.uptc.printer.presentation;


import co.edu.uptc.printer.logic.PrintSpooler;
import co.edu.uptc.printer.view.WarningMessages;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private AddToSpooler addToSpoolerInterface;
    private WarningMessages myWarningMessages;
    private PrintSpooler printSpooler;
    private  JLabel tittle;
    private JButton print;
    private JButton addToSpooler;
    private JButton checkSpooler;

    public MainWindow(){
        printSpooler = new PrintSpooler();
        myWarningMessages = new WarningMessages();
        setTitle("Impresiones capa");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(520,200);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(addNavBar(), BorderLayout.NORTH);
        mainPanel.add(addButtons(), BorderLayout.CENTER);
        getContentPane().add(mainPanel);
        this.setBackground(Color.WHITE);
        this.setVisible(true);
    }


    public JPanel addNavBar(){
        JPanel navBar = new JPanel(new GridLayout(1,2));
        ImageIcon icon= new ImageIcon("A:\\Uptc\\CuartoSemestre\\Programación\\SegundoCorte\\Printer\\src\\co\\edu\\uptc\\printer\\utilities\\logo.png");
        Image img= icon.getImage();
        Image newImage=img.getScaledInstance(80,50, Image.SCALE_SMOOTH);
        Icon newIcon=new ImageIcon(newImage);
        JLabel image=new JLabel();
        image.setIcon(newIcon);
        tittle = new JLabel();
        tittle.setText("Impresora");
        tittle.setLocation(1,2);
        image.setLocation(1,1);
        image.setVisible(true);
        navBar.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        navBar.setBorder(new LineBorder(Color.black));

        navBar.add(image);
        navBar.add(tittle);
        navBar.setBackground(Color.YELLOW);
        return navBar;

    }
    public  JPanel addButtons(){
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttons.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        addToSpooler = new JButton("Añadir archivo");
        print = new JButton("Imprimir");
        checkSpooler = new JButton("Cola de impresion");
        addToSpooler.setPreferredSize(new Dimension(150, 50));
        print.setPreferredSize(new Dimension(150, 50));
        checkSpooler.setPreferredSize(new Dimension(150,50));
        buttons.add(addToSpooler, BorderLayout.CENTER);
        buttons.add(print,BorderLayout.CENTER);
        buttons.add(checkSpooler, BorderLayout.CENTER);

        addToSpoolerInterface = new AddToSpooler(this,printSpooler);
        addToSpooler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToSpoolerInterface.createWindow();
                setState(false);
                addToSpoolerInterface.setVisible(true);
            }
        });
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printSpooler=addToSpoolerInterface.getPrintSpooler();
                printSpooler.resetPosition();
                printSpooler.printing();
                //addToSpoolerInterface.setPrintSpooler(printSpooler);
            }
        });

        checkSpooler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myWarningMessages=new WarningMessages();
                printSpooler=addToSpoolerInterface.getPrintSpooler();
                myWarningMessages.inputWarning(printSpooler.showTails().toString());
                setState(true);
            }
        });
        return buttons;
    }





    public void setState(boolean state){
                setVisible(state);
    }



}
