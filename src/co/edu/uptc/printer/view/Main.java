package co.edu.uptc.printer.view;

import co.edu.uptc.printer.logic.PrintSpooler;
import co.edu.uptc.printer.presentation.MainWindow;
import co.edu.uptc.printer.presentation.MessageWarning;

public class Main {

    public static void main(String[] args) {
        /*
        MessageWarning ms=new MessageWarning(new PrintSpooler());
        ms.createdAlertReload("hojas");
        WarningMessages a=new WarningMessages();
        a.printingFile("imprimiendo la hoja 10");

 */
        MainWindow mainWindow = new MainWindow();
        mainWindow.setState(true);


        /*
      View myView = new View();
        myView.menu();

         */

    }

}