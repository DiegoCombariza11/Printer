package co.edu.uptc.printer.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarningMessages {
    public  void lowInkWarning(String msg){
        Runnable warningMsg = () -> {
            JOptionPane.showMessageDialog(null, msg);
        };
        Thread thread1 = new Thread(warningMsg);
        thread1.start();
    }

    public void printingFile(String msg) {
        Runnable printingMsg = () -> {
            JOptionPane optionPane = new JOptionPane(msg, JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Printing Status");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

            try {
                Thread.sleep(3000);
                dialog.dispose();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread2 = new Thread(printingMsg);
        thread2.start();
    }
    public void lowSheetWarning(String msg){
        Runnable lowSheetMsg = () -> {
            JOptionPane.showMessageDialog(null, msg);
        };
        Thread thread3 = new Thread(lowSheetMsg);
        thread3.start();
    }

    public void emptySpool(String msg){
        Runnable emptySpoolMsg = () -> {
            JOptionPane.showMessageDialog(null, msg);
        };
        Thread thread4 = new Thread(emptySpoolMsg);
        thread4.start();
    }
    public void reloadInk(){
        Runnable reloadInkMsg = () -> {
            JOptionPane.showMessageDialog(null, "Tinta recargada");
        };
        Thread thread5 = new Thread(reloadInkMsg);
        thread5.start();
    }

    public void reloadSheets(){
        Runnable reloadSheetsMsg = () -> {
            JOptionPane.showMessageDialog(null, "Hojas recargadas");
        };
        Thread thread6 = new Thread(reloadSheetsMsg);
        thread6.start();
    }

}
