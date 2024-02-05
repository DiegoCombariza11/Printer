package co.edu.uptc.printer.logic;

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
        if(priority.equals("Rapido")){
            this.highPriority.add(0,fileToPrint);
        }else {
            this.normalPriority.add(fileToPrint);
        }
    }
    public String printing(){
        if(this.highPriority.isEmpty()&&this.normalPriority.isEmpty()) {
            return "";
        }
        if (this.highPriority.isEmpty()) {
            if (!this.printerController.validateSheetAvailability()) {
                if (!this.printerController.validateInkAvailability()) {
                    String result = "imprimiendo " + this.normalPriority.get(0).getArchive().getName() + " " + this.normalPriority.get(0).getNumberPages();
                    this.normalPriority.remove(0);
                    return result;
                }
                return this.printerController.showLowInk();
            }
            return this.printerController.showLowSheet();
        } else {
            if (!this.printerController.validateSheetAvailability()) {
                if (!this.printerController.validateInkAvailability()) {
                    String result = "imprimiendo " + this.highPriority.get(0).getArchive().getName() + " " + this.highPriority.get(0).getNumberPages();
                    this.highPriority.remove(0);
                    return result;
                }
                return this.printerController.showLowInk();
            }
            return this.printerController.showLowSheet();
        }
    }
}
