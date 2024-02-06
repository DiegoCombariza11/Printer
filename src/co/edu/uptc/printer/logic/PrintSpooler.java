package co.edu.uptc.printer.logic;

import co.edu.uptc.printer.model.FileToPrint;
import co.edu.uptc.printer.model.Printer;
import co.edu.uptc.printer.view.WarningMessages;

import javax.management.StringValueExp;
import java.util.ArrayList;

public class PrintSpooler {
    private ArrayList<FileToPrint> spooler;
    private PrinterController printerController;
    private WarningMessages warningMessages;

    public PrintSpooler() {
        this.spooler =new ArrayList<>();
        this.printerController=new PrinterController();
        this.warningMessages = new WarningMessages();
    }

    public PrinterController getPrinterController(){
        return this.printerController;
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
                warningMessages.lowSheetWarning(this.printerController.showLowSheet());
            }
        } else {
            warningMessages.emptySpool("El spooler está vacío. No hay archivos para imprimir.");
        }
    }
    public int numberPages(String range){
        if(range.contains("-")){
            String withRange[]=range.split("-");
            return Math.abs(Integer.parseInt(withRange[0])-Integer.parseInt(withRange[1]));
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
}