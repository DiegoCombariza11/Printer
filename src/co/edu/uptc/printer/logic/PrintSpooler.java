package co.edu.uptc.printer.logic;

import co.edu.uptc.printer.model.FileToPrint;
import co.edu.uptc.printer.view.WarningMessages;

import java.util.ArrayList;

public class PrintSpooler {
    private WarningMessages warningMessages;
    private ArrayList<FileToPrint> spooler;
    private PrinterController printerController;

    public PrintSpooler() {
        this.spooler =new ArrayList<>();
        this.printerController=new PrinterController();
        this.warningMessages = new WarningMessages();
    }


    public void addFileToTail(FileToPrint fileToPrint, String priority){
        if(priority.equals("Rapido")){
            this.spooler.add(0,fileToPrint);
        }else {
            this.spooler.add(fileToPrint);
        }
    }
    public void printing() {
        if (!this.spooler.isEmpty()) {
            if (this.printerController.checkSheets(numberPages(this.spooler.get(0).getNumberPages()), this.spooler.get(0).getSize())) {
                if (this.printerController.hasSufficientInk(this.spooler.get(0).isColor(), this.spooler.get(0).getSize())) {
                    StringBuilder msg = new StringBuilder();
                    msg.append("imprimiendo ").append(this.spooler.get(0).getArchive().getName()).append(" ").append(this.spooler.get(0).getNumberPages());
                    this.spooler.remove(0);
                    warningMessages.printingFile(String.valueOf(msg));
                } else {
                    warningMessages.lowInkWarning(String.valueOf(this.printerController.showLowInk(this.spooler.get(0).isColor(), this.spooler.get(0).getSize())));
                }
            }else {
                warningMessages.lowSheetWarning(this.printerController.showLowSheet(this.spooler.get(0).getSize()));
            }
        } else {
            warningMessages.emptySpool("El spooler está vacío. No hay archivos para imprimir.");
        }
    }//this.spooler.get(0).getSize()

    public int numberPages(String range){
        if(range.contains("-")){
            String withRange[]=range.split("-");
            return Math.abs(Math.abs(Integer.parseInt(withRange[1]))-Math.abs(Integer.parseInt(withRange[0])));
        } else if (range.contains(",")) {
            String pages[]=range.split(",");
            return pages.length;
        }
        return 0;
    }
    public String showTails(){
        String result="";
        if(this.spooler.isEmpty()){
            return "La cola esta vacia";
        }
        for(FileToPrint c:this.spooler){
            result+=c.getArchive().getName()+"\n";
        }
        return result;
    }

    public PrinterController getPrinterController(){
        return this.printerController;
    }
}