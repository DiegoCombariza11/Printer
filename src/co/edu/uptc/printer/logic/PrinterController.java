package co.edu.uptc.printer.logic;

import co.edu.uptc.printer.model.Archive;
import co.edu.uptc.printer.model.Printer;
import co.edu.uptc.printer.view.WarningMessages;

import java.util.ArrayList;

public class PrinterController {
    Printer myPrinter = new Printer(500,0.0,100,100,100,20,20);
    WarningMessages myWarningMessages = new WarningMessages();

    public StringBuilder showInkPercentage(){
        StringBuilder msg = new StringBuilder();
        msg.append("Nivel de tinta negra: ").append(myPrinter.getBlkAmount()).append("\n");
        msg.append("Nivel de tinta magenta: ").append(myPrinter.getMgAmount()).append("\n");
        msg.append("Nivel de tinta amarilla: ").append(myPrinter.getYellowAmount()).append("\n");
        msg.append("Nivel de tinta cian: ").append(myPrinter.getCyanAmount()).append("\n");
        return msg;
    }

    public void reloadInk(){
        myPrinter.setYellowAmount(100.0);
        myPrinter.setCyanAmount(100.0);
        myPrinter.setMgAmount(100.0);
        myPrinter.setBlkAmount(100.0);
        myWarningMessages.reloadInk();
    }



    /*"NO es necesario imrpimir los mensajes, sólo sirven para validar que everything works" */
    public void consumeInk(String size, boolean isColor, int recuestedSheets) {
        if (!isColor) {
            if (size.equalsIgnoreCase("carta")) {
                myPrinter.setBlkAmount(myPrinter.getBlkAmount()-0.3*recuestedSheets);
            } else if (size.equalsIgnoreCase("oficio")) {
                myPrinter.setBlkAmount(myPrinter.getBlkAmount()-0.4*recuestedSheets);
            }
        } else {
            if (size.equalsIgnoreCase("carta")) {
                myPrinter.setBlkAmount(myPrinter.getBlkAmount()-0.3*recuestedSheets);
                myPrinter.setMgAmount(myPrinter.getMgAmount()-0.3*recuestedSheets);
                myPrinter.setCyanAmount(myPrinter.getCyanAmount()-0.2*recuestedSheets);
                myPrinter.setYellowAmount(myPrinter.getYellowAmount()-0.1*recuestedSheets);
            } else if (size.equalsIgnoreCase("oficio")) {
                myPrinter.setBlkAmount(myPrinter.getBlkAmount()-0.4*recuestedSheets);
                myPrinter.setMgAmount(myPrinter.getMgAmount()-0.3*recuestedSheets);
                myPrinter.setCyanAmount(myPrinter.getCyanAmount()-0.3*recuestedSheets);
                myPrinter.setYellowAmount(myPrinter.getYellowAmount()-0.2*recuestedSheets);
            }
        }
    }
    public void consumeSheets(String size, int recuestedSheets){
        if(size.equalsIgnoreCase("carta")){
            myPrinter.setLetterSheets(myPrinter.getLetterSheets()-recuestedSheets);
        }else {
            myPrinter.setLegalSheets(myPrinter.getLegalSheets()-recuestedSheets);
        }
    }
    public StringBuilder showSheetsAmount(){
        StringBuilder msg = new StringBuilder();
        return msg.append("Hojas tamaño carta: ").append(myPrinter.getLetterSheets()).append("\n").append("Hojas tamaño oficio: ").append(myPrinter.getLegalSheets());
    }

    public StringBuilder showLowInk(boolean isColor, String size) {
        StringBuilder msg = new StringBuilder();
        if (myPrinter.getBlkAmount() <= 2.0) {
            msg.append(" - Nivel de tinta negra bajo\n");
        }
        if (myPrinter.getMgAmount() <= 2.0) {
            msg.append(" - Nivel de tinta magenta bajo\n");
        }
        if (myPrinter.getCyanAmount() <= 2.0) {
            msg.append(" - Nivel de tinta cian bajo \n");
        }
        if (myPrinter.getYellowAmount() <= 2.0) {
            msg.append(" - Nivel de tinta amarilla bajo \n");
        }
        return msg;
    }

    public boolean hasSufficientInk(boolean isColor, String size, int recuestedSheets) {
        if (!isColor) {
            if (size.equalsIgnoreCase("carta")) {
                return myPrinter.getBlkAmount() >= 0.3*recuestedSheets;
            } else if (size.equalsIgnoreCase("oficio")) {
                return myPrinter.getBlkAmount() >= 0.4*recuestedSheets;
            } else {
                return false;
            }
        } else {
            if (size.equalsIgnoreCase("carta")) {
                return myPrinter.getBlkAmount() >= 0.3*recuestedSheets &&
                        myPrinter.getMgAmount() >= 0.3*recuestedSheets &&
                        myPrinter.getCyanAmount() >= 0.2*recuestedSheets &&
                        myPrinter.getYellowAmount() >= 0.1*recuestedSheets;
            } else if (size.equalsIgnoreCase("oficio")) {
                return myPrinter.getBlkAmount() >= 0.4*recuestedSheets&&
                        myPrinter.getMgAmount() >= 0.3*recuestedSheets &&
                        myPrinter.getCyanAmount() >= 0.3*recuestedSheets &&
                        myPrinter.getYellowAmount() >= 0.2*recuestedSheets;
            } else {
                return false;
            }
        }
    }


    public String showLowSheet(String size){
        if (size.equals("carta")) {
            return "Quedan pocas hojas carta, recargue hojas";
        }else {
            return "Quedan pocas hojas oficio, recargue hojas";
        }
    }


    public boolean checkSheets(int requestedSheets, String size){
        if(size.equals("carta")) {
            if (requestedSheets >= this.myPrinter.getLetterSheets()+1) {
                return false;
            } else {
                return true;
            }
        }else {
            if (requestedSheets >= this.myPrinter.getLegalSheets()+1) {
                return false;
            } else {
                return true;
            }
        }
    }


    public void addSheets(){
        myPrinter.setLegalSheets(250);
        myPrinter.setLetterSheets(250);
        myWarningMessages.reloadSheets();
    }


}