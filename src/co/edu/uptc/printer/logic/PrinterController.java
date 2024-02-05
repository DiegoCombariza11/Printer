package co.edu.uptc.printer.logic;

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

    public boolean checkMinSheets(int numSheets){
        if(numSheets<1){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkMaxSheets(int numSheets,int sheets){
        if(numSheets>sheets){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Integer> refillSheets(int lenght){
        ArrayList<Integer> filledPages= new ArrayList<>();
        for (int i=0; i<filledPages.size();i++){
            filledPages.add(i+1);
        }
        return filledPages;
    }

    public ArrayList<Integer> selectRangeSheets(ArrayList<Integer> sheets, int rangeSheetsMa, int rangeSheetsMi){
        for (int i=0; i<rangeSheetsMi;i++){
            if (sheets.get(i).equals(i+1)){
                sheets.remove(i);
            }
        }
        for (int i=rangeSheetsMa; i<sheets.size();i++){
            if (sheets.get(i).equals(i+1)){
                sheets.remove(i);
            }
        }
        return sheets;
    }
}
