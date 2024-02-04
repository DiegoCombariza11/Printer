package co.edu.uptc.printer.logic;

import co.edu.uptc.printer.model.Archive;
import co.edu.uptc.printer.model.FileToPrint;

import java.util.ArrayList;

public class PrintSpooler {
    private ArrayList<FileToPrint> highPriority;
    private ArrayList<FileToPrint> normalPriority;
    private PrinterController printerController;

    public PrintSpooler() {
        this.highPriority=new ArrayList<>();
        this.normalPriority=new ArrayList<>();
        this.printerController=new PrinterController();
    }

    public void addFileToTail(FileToPrint fileToPrint, String priority){
        if(priority.equals("high")){
            this.highPriority.add(fileToPrint.getPriority(),fileToPrint);
        }else {
            this.normalPriority.add(fileToPrint);
        }
    }
    public String Printing(){
        if(this.highPriority.isEmpty()){
            if(!this.printerController.validateSheetAvailability()){
                if (!this.printerController.validateInkAvailability()){

                }
                return this.printerController.showLowInk();
            }
            return this.printerController.showLowSheet();
        }
        return "";
    }
}
