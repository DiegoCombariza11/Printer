package co.edu.uptc.printer.presentation;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainWindow extends JFrame {

    private  JLabel tittle;
    private JButton addToSpooler;
    private JButton checkSpooler;

    public MainWindow(){
        setTitle("Impresiones capa");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(400,200);
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
        navBar.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        navBar.setBorder(new LineBorder(Color.black));

        navBar.add(image);
        navBar.add(tittle);
        navBar.setBackground(Color.YELLOW);
        return navBar;

    }
    public  JPanel addButtons(){
        JPanel buttons = new JPanel(new BorderLayout());
        buttons.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        addToSpooler = new JButton("       Imprimir      ");
        checkSpooler = new JButton("Cola de impresion");
        addToSpooler.setSize(50,50);
        checkSpooler.setSize(50,50);
        buttons.add(addToSpooler, BorderLayout.CENTER);
        buttons.add(checkSpooler, BorderLayout.CENTER);
        return buttons;
    }

    public void setState(boolean state){
                setVisible(state);
    }



}
