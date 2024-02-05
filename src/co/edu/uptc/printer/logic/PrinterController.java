package co.edu.uptc.printer.logic;

import co.edu.uptc.printer.model.Archive;
import co.edu.uptc.printer.model.Printer;

import java.util.ArrayList;

public class PrinterController {


    public StringBuilder showInkPercentage(){
        StringBuilder msg = new StringBuilder();


       return msg;
    }

    public boolean validateInkAvailability(){

        return false;
    }


    public void rechargeInk(){

    }
    public String showLowInk(){

        return "";
    }


    public boolean validateSheetAvailability(){

        return false;
    }


    public void rechargeSheet(){

    }
    public String showLowSheet(){

        return "";
    }

    public int totalSheet(ArrayList<Archive> documents){
        int total = 0;
        for (Archive document : documents) {
            total = total + document.getPages();
        }
        return total;
    }

    public boolean checkSheets(int requestedSheets, int aviableSheets){
        if(requestedSheets>=aviableSheets){
            return false;
        }else{
            return true;
        }
    }

    public int stablishAmountOfSheets(int requestedSheets, int aviableSheets){
        return aviableSheets-requestedSheets;
    }

    public int addSheets(int newSheets, int aviableSheets){
        return newSheets+aviableSheets;
    }


}
