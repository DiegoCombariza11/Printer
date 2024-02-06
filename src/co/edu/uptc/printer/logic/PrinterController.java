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
    public String consumeInk(String size, boolean isColor) {
        if (!isColor) {
            if (size.equalsIgnoreCase("carta")) {
                myPrinter.setBlkAmount(myPrinter.getBlkAmount()-0.3);
            } else if (size.equalsIgnoreCase("oficio")) {
                myPrinter.setBlkAmount(myPrinter.getBlkAmount()-0.4);
            } else {
                return "Tamaño de papel no válido para impresión en blanco y negro.";
            }
        } else {
            if (size.equalsIgnoreCase("carta")) {
                myPrinter.setBlkAmount(myPrinter.getBlkAmount()-0.3);
                myPrinter.setMgAmount(myPrinter.getMgAmount()-0.3);
                myPrinter.setCyanAmount(myPrinter.getCyanAmount()-0.2);
                myPrinter.setYellowAmount(myPrinter.getYellowAmount()-0.1);
            } else if (size.equalsIgnoreCase("oficio")) {
                myPrinter.setBlkAmount(myPrinter.getBlkAmount()-0.4);
                myPrinter.setMgAmount(myPrinter.getMgAmount()-0.3);
                myPrinter.setCyanAmount(myPrinter.getCyanAmount()-0.3);
                myPrinter.setYellowAmount(myPrinter.getYellowAmount()-0.2);
            } else {
                return  "Tamaño de papel no válido para impresión a color.";
            }
        }
        return "Todo funciona bien";
    }

    public StringBuilder showLowInk(boolean isColor, String size) {
        StringBuilder msg = new StringBuilder();
        if (myPrinter.getBlkAmount() <= 2.0) {
            msg.append(" - Nivel de tinta negra bajo (Carta)\n");
        }
        if (myPrinter.getMgAmount() <= 2.0) {
            msg.append(" - Nivel de tinta magenta bajo (Carta)\n");
        }
        if (myPrinter.getCyanAmount() <= 2.0) {
            msg.append(" - Nivel de tinta cian bajo (Carta)\n");
        }
        if (myPrinter.getYellowAmount() <= 2.0) {
            msg.append(" - Nivel de tinta amarilla bajo (Carta)\n");
        }
        return msg;
    }

    public boolean hasSufficientInk(boolean isColor, String size) {
        if (!isColor) {
            if (size.equalsIgnoreCase("carta")) {
                return myPrinter.getBlkAmount() >= 0.5;
            } else if (size.equalsIgnoreCase("oficio")) {
                return myPrinter.getBlkAmount() >= 0.6;
            } else {
                return false;
            }
        } else {
            if (size.equalsIgnoreCase("carta")) {
                return myPrinter.getBlkAmount() >= 0.5 &&
                        myPrinter.getMgAmount() >= 0.5 &&
                        myPrinter.getCyanAmount() >= 0.4 &&
                        myPrinter.getYellowAmount() >= 0.3;
            } else if (size.equalsIgnoreCase("oficio")) {
                return myPrinter.getBlkAmount() >= 0.6 &&
                        myPrinter.getMgAmount() >= 0.5 &&
                        myPrinter.getCyanAmount() >= 0.5 &&
                        myPrinter.getYellowAmount() >= 0.4;
            } else {
                return false;
            }
        }
    }


    /*
    llamar éste método en el controlador para ver el funcionamiento

    myPrinterController.print();

    print() es un método de prueba y no representa el resultado final
     */
    public void print(){
        String size = "carta";
        boolean isColor = false;
        String color = "";
        if (!isColor){
            color = "blanco y negro";
        }else {
            color = "color";
        }
        System.out.println(showInkPercentage());
        System.out.println(showLowInk(isColor,size));
        if (hasSufficientInk(isColor, size)){
            System.out.println(consumeInk(size, isColor));
            System.out.println(showInkPercentage());
        }else {
            System.out.println("Tinta insuficiente para imprimir a "+color + " en tamaño "+size);
            System.out.println("Recargando tinta...");
            reloadInk();
            System.out.println(showInkPercentage());
            System.out.println("Volviendo a imprimir");
            System.out.println(consumeInk(size, isColor));
            System.out.println(showInkPercentage());
        }
    }




    public void rechargeSheet(){

    }
    public String showLowSheet(String size){
        if (size.equals("carta")) {
            return "Quedan pocas hojas carta, recargue hojas";
        }else {
            return "Quedan pocas hojas oficio, recargue hojas";
        }
    }

    public int totalSheet(ArrayList<Archive> documents){
        int total = 0;
        for (Archive document : documents) {
            total = total + document.getPages();
        }
        return total;
    }

    public boolean checkSheets(int requestedSheets, String size){
        if(size.equals("carta")) {
            if (requestedSheets >= this.myPrinter.getLetterSheets()) {
                return false;
            } else {
                return true;
            }
        }else {
            if (requestedSheets >= this.myPrinter.getLegalSheets()) {
                return false;
            } else {
                return true;
            }
        }
    }

    public int stablishAmountOfSheets(int requestedSheets, int aviableSheets){
        return aviableSheets-requestedSheets;
    }

    public void addSheets(){
        myPrinter.setLegalSheets(250);
        myPrinter.setLetterSheets(250);
        myWarningMessages.reloadSheets();
    }


}