package co.edu.uptc.printer.logic;

import co.edu.uptc.printer.model.FileToPrint;
import co.edu.uptc.printer.view.WarningMessages;

import java.util.ArrayList;

public class PrintSpooler {
    private WarningMessages warningMessages;
    private ArrayList<FileToPrint> spooler;
    private PrinterController printerController;
    private int index;
    private int i;
    public PrintSpooler() {
        this.spooler = new ArrayList<>();
        this.printerController = new PrinterController();
        this.warningMessages = new WarningMessages(this);
        index=0;
        i=0;
    }


    public void addFileToTail(FileToPrint fileToPrint, String priority) {
        if (priority.equals("Alta")) {
            this.spooler.add(0, fileToPrint);
        } else {
            this.spooler.add(fileToPrint);
        }
    }
    public void resetPosition() {
        if (!spooler.isEmpty()) {
            i = 0;
            index = numberPages(this.spooler.get(0).getNumberPages(), this.spooler.get(0).getArchive().getPages());
        }
    }
    public void printing() {
        if (!this.spooler.isEmpty()) {
                if (this.printerController.checkSheets(this.spooler.get(0).getSize())) {
                    if (this.printerController.hasSufficientInk(this.spooler.get(0).isColor(), this.spooler.get(0).getSize(),numberPages(this.spooler.get(0).getNumberPages(),this.spooler.get(0).getArchive().getPages()))) {
                        while (index>=0) {
                            StringBuilder msg = new StringBuilder();
                            msg.append("imprimiendo página: ").append(i).append(" de ").append(index).append(": ").append(this.spooler.get(0).getArchive().getName());
                            printerController.consumeInk(this.spooler.get(0).getSize(), this.spooler.get(0).isColor(), 1);
                            printerController.consumeSheets(this.spooler.get(0).getSize(), 1);
                            index--;
                            i++;
                            warningMessages.printingFile(String.valueOf(msg));
                            if (index == 0) {
                                this.spooler.remove(0);
                                break;
                            }

                        }
                    } else {
                        warningMessages.lowInkWarning(String.valueOf(this.printerController.showLowInk(this.spooler.get(0).isColor(), this.spooler.get(0).getSize())));

                    }
                } else {
                    warningMessages.lowSheetWarning(this.printerController.showLowSheet(this.spooler.get(0).getSize()));
                }
        } else {
            warningMessages.emptySpool("El spooler está vacío. No hay archivos para imprimir.");
        }
    }//this.spooler.get(0).getSize()

    public int numberPages(String range, int recuestedPages) {
        try {
            if (range.contains("-")) {
                String withRange[] = range.split("-");
                if (Integer.parseInt(withRange[1]) >= 0 && Integer.parseInt(withRange[1]) <= recuestedPages && Integer.parseInt(withRange[0]) >= 0 && Integer.parseInt(withRange[0]) <= recuestedPages) {
                    return Math.abs(Math.abs(Integer.parseInt(withRange[1])) - Math.abs(Integer.parseInt(withRange[0])));
                } else {
                    return 0;
                }
            } else if (range.contains(",")) {
                boolean flag = false;
                String pages[] = range.split(",");
                for (int i = 0; i < pages.length; i++) {
                    if (Integer.parseInt(pages[i]) < 0 || Integer.parseInt(pages[i]) > recuestedPages) {
                        flag = true;
                    }
                }
                if (flag || pages.length > recuestedPages) {
                    return 0;
                }
                return pages.length;
            } else if (Integer.parseInt(range)<recuestedPages){
                return 1;
            }
        }catch (NumberFormatException f){
            return 0;
        }
        return 0;
    }
    public StringBuilder showTails() {
        StringBuilder result =new StringBuilder();
        if (this.spooler.isEmpty()) {
            return result.append("La cola esta vacia");
        }
        for (FileToPrint c : this.spooler) {
            result.append(c.getArchive().getName()).append("\n");
        }
        return result;
    }

    public PrinterController getPrinterController() {
        return this.printerController;
    }
}