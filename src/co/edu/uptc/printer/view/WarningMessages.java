package co.edu.uptc.printer.view;

import co.edu.uptc.printer.logic.PrintSpooler;
import co.edu.uptc.printer.presentation.MessageWarning;
import co.edu.uptc.printer.presentation.Print;

import javax.swing.*;

public class WarningMessages {
    private MessageWarning mw;
    private MessageWarning myMessageWarning;

    public WarningMessages() {
        this.myMessageWarning=new MessageWarning();
    }

    public WarningMessages(PrintSpooler mw) {
        this.mw=new MessageWarning(mw);
    }



    public void showInkLevel(String msg){
        Runnable showInkLevelMsg = () -> {
            //JOptionPane.showMessageDialog(null, msg);
            myMessageWarning.cratedMessage(msg);
        };
        Thread thread8 = new Thread(showInkLevelMsg);
        thread8.start();
    }
    public void showSheetsAmount(String msg){
        Runnable showSheetsAmountMsg = () -> {
            //JOptionPane.showMessageDialog(null, msg);
            myMessageWarning.cratedMessage(msg);
        };
        Thread thread9 = new Thread(showSheetsAmountMsg);
        thread9.start();
    }

    public void lowInkWarning(String msg) {
        Runnable warningMsg = () -> {
            //JOptionPane.showMessageDialog(null, msg);
            myMessageWarning.createdAlertReload(msg);
        };
        Thread thread1 = new Thread(warningMsg);
        thread1.start();
    }

    public void printingFile(String msg) {
        Runnable printingMsg = () -> {
            /*JOptionPane optionPane = new JOptionPane(msg, JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Printing Status");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);*/
            Print print=new Print(msg);
            try {
                Thread.sleep(3000);
                //dialog.dispose();
                print.dispose();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread2 = new Thread(printingMsg);
        thread2.start();
    }

    public void lowSheetWarning(String msg) {
        Runnable lowSheetMsg = () -> {
            //JOptionPane.showMessageDialog(null, msg);
            mw.createdAlertReload(msg);
        };
        Thread thread3 = new Thread(lowSheetMsg);
        thread3.start();
    }

    public void emptySpool(String msg) {
        Runnable emptySpoolMsg = () -> {
            //JOptionPane.showMessageDialog(null, msg);
            mw.cratedMessage(msg);
        };
        Thread thread4 = new Thread(emptySpoolMsg);
        thread4.start();
    }

    public void reloadInk() {
        Runnable reloadInkMsg = () -> {
            //JOptionPane.showMessageDialog(null, "Tinta recargada");
            mw.cratedMessage("Tinta recargada");
        };
        Thread thread5 = new Thread(reloadInkMsg);
        thread5.start();
    }

    public void reloadSheets() {
        Runnable reloadSheetsMsg = () -> {
            //JOptionPane.showMessageDialog(null, "Hojas recargadas");
            myMessageWarning.cratedMessage("Hojas recargadas");
        };
        Thread thread6 = new Thread(reloadSheetsMsg);
        thread6.start();
    }

    public void inputWarning(String msg) {
        Runnable extensionWarningMsg = () -> {
            myMessageWarning.cratedMessage(msg);
        };
        Thread thread7 = new Thread(extensionWarningMsg);
        thread7.start();
    }


}
